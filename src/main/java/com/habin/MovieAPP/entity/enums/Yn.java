package com.habin.MovieAPP.entity.enums;

public enum Yn {

    YES(1), NO(2);

    Yn(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
    
}
