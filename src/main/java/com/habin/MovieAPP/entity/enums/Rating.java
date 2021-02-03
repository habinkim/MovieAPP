package com.habin.MovieAPP.entity.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 영화 평점 Enum 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 02. 03
* @Time : 11:04:51
*/

@AllArgsConstructor
@NoArgsConstructor
public enum Rating {

    ZERO_P_FIVE(0.5, "0.5"), 
    ONE(1.0, "1.0"), ONE_P_FIVE(1.5, "1.5"), 
    TWO(2.0, "2.0"), TWO_P_FIVE(2.5, "2.5"), 
    THREE(3.0, "3.0"), THREE_P_FIVE(3.5, "3.5"), 
    FOUR(4.0, "4.0"), FOUR_P_FIVE(4.5, "4.5"), 
    FIVE(5.0, "5.0");

    private Double dou;
    private String str;

    private Double toDou() {
        return dou;
    }

    private String toStr() {
        return str;
    }
    
}
