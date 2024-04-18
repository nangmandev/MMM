package com.spring.mmm.domain;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import com.spring.mmm.domain.mukjuks.domain.FoodMukjukLevel;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import com.spring.mmm.domain.recommends.domain.*;
import com.spring.mmm.domain.users.infra.UserEntity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestFixture {
    public static UserEntity user;
    public static UserEntity userWithMukbo;
    public static UserEntity userWithMukboSoloGroup;
    public static MukgroupEntity soloMukgroupEntity;
    public static MukgroupEntity mukgroupEntity;
    public static MukboEntity mukboEntitySologroup;
    public static MukboEntity mukboEntityGroup;
    public static MukboEntity mukbotEntity;
    public static MukBTIResultEntity mukBTIResult;
    public static List<Badge> badges;

    public static List<MukjukEntity> mukjuks;

    public static RecommendedFoodEntity recommendedFood;


    static {
        List<Badge> tempBadges = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tempBadges.add(Badge.builder()
                    .id((long) i)
                    .name("먹적"+i)
                    .isCleared(i<5)
                    .build());
        }

        badges = Collections.unmodifiableList(tempBadges);
        // 먹적
        createMukjuks();


        user = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .build();
        // 유저장전
        mukgroupEntity = MukgroupEntity.builder()
                .isSolo(Boolean.FALSE)
                .mukgroupId(1L)
                .name("ssafygroup")
                .mukjukEntity(mukjuks.getFirst())
                .build();
        // 먹그룹장전

        soloMukgroupEntity = MukgroupEntity.builder()
                .mukgroupId(2L)
                .isSolo(Boolean.TRUE)
                .name("ssafysologroup")
                .build();
        // 솔로먹그룹장전

        mukboEntitySologroup = MukboEntity.builder()
                .mukboId(1L)
                .type(MukboType.HUMAN)
                .name("mukssafy")
                .userEntity(user)
                .mukgroupEntity(soloMukgroupEntity)
                .build();
        // 솔로그룹먹보장전

        mukboEntityGroup = MukboEntity.builder()
                .mukboId(1L)
                .type(MukboType.HUMAN)
                .name("mukssafy")
                .userEntity(user)
                .mukgroupEntity(mukgroupEntity)
                .build();
        // 다인그룹먹보장전

        mukbotEntity = MukboEntity.builder()
                .mukboId(3L)
                .type(MukboType.MUKBOT)
                .name("mukbot")
                .mukgroupEntity(mukgroupEntity)
                .build();
        // 먹봇장전

        userWithMukbo = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .mukboEntity(mukboEntityGroup)
                .build();
        // 먹보와함께유저

        userWithMukboSoloGroup = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .mukboEntity(mukboEntitySologroup)
                .build();
        // 솔로먹그룹유저

        mukBTIResult = MukBTIResultEntity.builder()
                .score(30)
                .build();
        // 먹비티아이장전


        FoodEntity food = FoodEntity.builder()
                .name("평양 냉면")
                .foodId(1)
                .foodCategoryEntity(FoodCategoryEntity.builder()
                        .foodCategoryId(1)
                        .name(FoodCategory.KOREA)
                        .build())
                .build();


        FoodRecommendEntity foodRecommend = FoodRecommendEntity.builder()
                .foodRecommendId(1)
                .mukgroupEntity(mukgroupEntity)
                .build();


        recommendedFood = RecommendedFoodEntity.builder()
                .category(RecommendCategory.NORMAL)
                .eaten(true)
                .foodEntity(food)
                .foodRecommendEntity(foodRecommend)
                .build();


    }

    private static void createMukjuks(){
        List<MukjukEntity> tempMukjuks = new ArrayList<>();
        int id = 1;
        for (FoodCategory category : FoodCategory.values()) {
            for (FoodMukjukLevel level : FoodMukjukLevel.values()) {
                tempMukjuks.add(MukjukEntity.builder()
                                .mukjukId((long) id++)
                        .name(category.getKoreanName() + " " +level.getTitle())
                        .context(category.getKoreanName() + " " + level.getCondition()+"번 먹기")
                        .build());
            }
        }
        mukjuks = Collections.unmodifiableList(tempMukjuks);
    }
}
