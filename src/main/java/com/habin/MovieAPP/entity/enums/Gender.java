package com.habin.MovieAPP.entity.enums;

public enum Gender {

    MAN(1), WOMAN(2);

    Gender(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
    
}
