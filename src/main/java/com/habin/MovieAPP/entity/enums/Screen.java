package com.habin.MovieAPP.entity.enums;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 상영관 Enum 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 14
* @Time : 17:11:05
*/
public enum Screen {

    ONE(1), TWO(2), THREE(3);

    Screen(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
    }
    
}
