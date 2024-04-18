package com.spring.mmm.domain.mukjuks.controller.response;

import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import lombok.*;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Badge {
    private Long id;
    private String name;
    private String condition;
    private Boolean isCleared;
    private String imageSrc;

    @Override
    public String toString() {
        return "Badge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", condition='" + condition + '\'' +
                ", isCleared=" + isCleared +
                ", imageSrc='" + imageSrc + '\'' +
                '}';
    }
}
