package com.habin.MovieAPP.entity.enums;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 장르 Enum 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 11
* @Time : 16:47:35
*/

public enum Genre {

    ACTION(1), SF(2), DRAMA(3), HORROR(4), THRILLER(5), ROMANCE(6);

    Genre(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
	}
    
}
