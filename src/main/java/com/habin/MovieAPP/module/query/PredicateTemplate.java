package com.habin.MovieAPP.module.query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

import com.habin.MovieAPP.entity.enums.SearchCond;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PredicateTemplate {

	private List<Predicate> predicateBuilders = new ArrayList<>();

	public static PredicateTemplate builder() {
		return new PredicateTemplate();
	}

	public <P extends Predicate> PredicateTemplate and(P pr) {
		predicateBuilders.add(pr);
		return this;
	}

	public Predicate build() {
		return ExpressionUtils.allOf(predicateBuilders);
	}

	public PredicateTemplate eqString(StringPath column, String value) {

		if(StringUtils.hasText(value)) {
			predicateBuilders.add(column.eq(value));
		}

		return this;
	}

	public PredicateTemplate containsString(StringPath column, String value) {

		if(StringUtils.hasText(value)) {
			predicateBuilders.add(column.contains(value));
		}

		return this;
	}

	public PredicateTemplate containsStrings(List<StringPath> columns, String value) {

		if(StringUtils.hasText(value)) {
			predicateBuilders.add(new BooleanBuilder()
				.andAnyOf(columns.stream()
					.map(c -> c.contains(value))
					.toArray(BooleanExpression[]::new)
				)
			);
		}

		return this;
	}

	public PredicateTemplate eqLong(NumberPath<Long> column, Long value) {

		if(value != null) {
			predicateBuilders.add(column.eq(value));
		}

		return this;
	}

	public <E extends Enum<E>> PredicateTemplate eqEnum(EnumPath<E> column, E value) {

		if(value != null) {
			predicateBuilders.add(column.eq(value));
		}

		return this;
	}

	public <E extends Enum<E>> PredicateTemplate inEnum(EnumPath<E> column, List<E> value) {

		if(value != null) {
			predicateBuilders.add(column.in(value));
		}

		return this;
	}

	public PredicateTemplate betweenDate(DateTimePath<LocalDateTime> column, LocalDateTime start_date, LocalDateTime end_date) {

		if(start_date != null && end_date != null) {
			predicateBuilders.add(column.between(start_date, end_date));
		}

		return this;
	}

	public PredicateTemplate checkDate(DateTimePath<LocalDateTime> column, Map<SearchCond, LocalDate> search_cond, Long week_count) {

		Predicate check_pr = null;

		if(!search_cond.isEmpty()) {

			if(search_cond.containsKey(SearchCond.DAY)) {
				LocalDate date = search_cond.get(SearchCond.DAY);
				check_pr = ExpressionUtils.allOf(
						column.year().eq(date.getYear()),
						column.month().eq(date.getMonthValue()),
						column.dayOfMonth().eq(date.getDayOfMonth())
				);
			}

			if(search_cond.containsKey(SearchCond.WEEK) && week_count != null) {
				LocalDate date = search_cond.get(SearchCond.WEEK);

				LocalDate last_date_of_month = date.with(TemporalAdjusters.lastDayOfMonth());
				TemporalField wom = WeekFields.of(Locale.KOREA).weekOfMonth();

				Map<Integer, List<LocalDate>> week_of_month_periods = new HashMap<>();

				IntStream
				.rangeClosed(1, last_date_of_month.getDayOfMonth())
				.forEach(n -> {
					LocalDate check = LocalDate.of(date.getYear(), date.getMonthValue(), n);
					int week_num = check.get(wom);

					if (!week_of_month_periods.containsKey(week_num)) {
						week_of_month_periods.put(week_num, new ArrayList<>());
					}
					week_of_month_periods.get(week_num).add(check);
				});

				List<LocalDate> period = week_of_month_periods.get(week_count.intValue());

				LocalDateTime start_date = LocalDateTime.of(period.get(0), LocalTime.of(0, 0));
				LocalDateTime end_date = LocalDateTime.of(period.get(period.size() - 1), LocalTime.of(23, 59));

				check_pr = ExpressionUtils.allOf(
						column.between(start_date, end_date)
				);
			}

			if(search_cond.containsKey(SearchCond.MONTH)) {
				LocalDate date = search_cond.get(SearchCond.MONTH);

				check_pr = ExpressionUtils.allOf(
						column.year().eq(date.getYear()),
						column.month().eq(date.getMonthValue())
				);
			}

			if(search_cond.containsKey(SearchCond.YEAR)) {
				LocalDate date = search_cond.get(SearchCond.YEAR);

				check_pr = ExpressionUtils.allOf(
					column.year().eq(date.getYear())
				);
			}

			predicateBuilders.add(check_pr);
		}

		return this;
	}

}
