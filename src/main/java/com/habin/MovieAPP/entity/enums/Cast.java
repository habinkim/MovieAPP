package com.habin.MovieAPP.entity.enums;

public enum Cast {

    STAR(1), SUPPORT(2), MINOR(3);

    Cast(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
    
}
