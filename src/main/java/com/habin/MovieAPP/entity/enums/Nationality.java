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

    USA(1), CHINA(2), JAPAN(3), KOREA(4), FRANCE(5), DENMARK(6), RUSSIA(7), AUSTRALIA(8), INDIA(9);

    Nationality(int value) {
        this.value = value;
    }

    private int value;

    @Override
	public String toString() {
        return Integer.toString(value);
	}
    
}
