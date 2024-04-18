package com.spring.mmm.domain.recommends.controller.response;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ModifiedRecommendInfo {
    private MBTI mbti;
    private String mbtiString;
    private List<LunchRecommendFoodInformation> lunchList;

    public static String mbtiToString(MBTI mbti) {
        StringBuilder sb = new StringBuilder();
        if (mbti.getEi() > 15) {
            sb.append("I");
        } else sb.append("E");
        if (mbti.getNs() > 15) {
            sb.append("S");
        } else sb.append("N");
        if (mbti.getTf() > 15) {
            sb.append("F");
        } else sb.append("T");
        if (mbti.getJp() > 15) {
            sb.append("P");
        } else sb.append("J");

        return sb.toString();
    }

    public static ModifiedRecommendInfo create(MBTI mbti, List<LunchRecommendFoodInformation> lunchList){
        return ModifiedRecommendInfo.builder()
                .mbti(mbti)
                .mbtiString(mbtiToString(mbti))
                .lunchList(lunchList)
                .build();
    }
}
