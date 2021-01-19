package com.habin.MovieAPP.entity.enums;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 국가 Enum 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 11
* @Time : 20:19:59
*/

public enum Nationality {

    USA(1), UK(2), CHINA(3), JAPAN(4), KOREA(5), FRANCE(6), DENMARK(7), RUSSIA(8), AUSTRALIA(9), INDIA(10);

    Nationality(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
	}
    
}
