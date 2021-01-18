package com.habin.MovieAPP.entity.enums;

public enum AtdType {

    WORK(1), LATE(2), ABSENECE(3), DAY_OFF_BEFORE(4), DAY_OFF_AFTER(5), ANNUAL_VACATION(6);

    AtdType(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
    
}
