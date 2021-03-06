package com.habin.MovieAPP.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 예매 티켓 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 19
* @Time : 11:24:58
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_TICKET")
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY) // N : 1 외래키
	@JoinColumn(nullable = false, name = "purchaser", referencedColumnName = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "movie", referencedColumnName = "scrId")
    private Screening screening;

	@Column(nullable = false, length = 20)
    private String ticketCode;

    @PrePersist
    public void setTicketCode() {

        String userId = user.getUserId();
        LocalDateTime scrStartTime = screening.getScrStartTime();
        LocalDateTime scrEndTime = screening.getScrEndTime();
        String movieNm = screening.getMovie().getMovieNm();
        String hall = screening.getHall().toString();

        ticketCode = new StringBuilder()
        .append(userId).append("_")
        .append(movieNm).append("_")
        .append(hall).append("_")
        .append(String.valueOf(scrStartTime.getYear())).append("_")
        .append(String.valueOf(scrStartTime.getMonthValue())).append("_")
        .append(scrStartTime.getDayOfMonth() != scrEndTime.getDayOfMonth() ? 
                String.valueOf(scrStartTime.getDayOfMonth() + "/" + scrEndTime.getDayOfMonth()) : String.valueOf(scrStartTime.getDayOfMonth()))
        .toString();
                                 
    }
    
}