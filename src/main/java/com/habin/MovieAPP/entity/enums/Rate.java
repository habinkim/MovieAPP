package com.habin.MovieAPP.entity.enums;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 관람등급 Enum 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 11
* @Time : 16:47:13
*/

public enum Rate {

    R_ALL(1), R_12(2), R_15(3), R_18(4);

    Rate(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
    
}
