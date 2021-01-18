package com.habin.MovieAPP.entity.enums;

public enum EventType {

    DISCOUNT(1), FREE(2), CAFE(3), TICKET(4), REGULAR(5), IRREGULAR(6);

    EventType(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
    
}
