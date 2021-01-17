package com.habin.MovieAPP.entity.enums;

public enum ScrType {

    EARLY_MORNING(1), NORMAL(2), LATE_NIGHT(3);

    ScrType(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
    
}
