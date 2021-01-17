package com.habin.MovieAPP.entity.enums;

public enum SearchCond {
    DAY(1), WEEK(2), MONTH(3), YEAR(4);
    
    SearchCond(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
}
