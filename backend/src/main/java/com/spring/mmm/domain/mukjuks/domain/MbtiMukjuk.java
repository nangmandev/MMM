package com.spring.mmm.domain.mukjuks.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MbtiMukjuk {
    IE("맵찔이", "맵당당"),
    SN("면 애호가", "밥 애호가"),
    FT("건강맨","쳐묵맨"),
    JP("응애","으른"),
    MINT("치약을 왜 먹죠?", "민초단"),
    PINE("이탈리안","하와이안"),
    DIE("거를 타선이 없는", "난 죽음을 택하겠다")

    ;


    private final String zeroTitle;
    private final String hundredTitle;

    public String getTitle(int value) {
        if( value == 0)
            return zeroTitle;
        else if(value == 100)
            return hundredTitle;
        throw new IllegalArgumentException("100이나 0만 들어올 수 있읍니다.");
    }


}
