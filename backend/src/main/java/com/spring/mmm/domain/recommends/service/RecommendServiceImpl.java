package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.MukgroupService;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.recommends.controller.request.LunchRecommendRequest;
import com.spring.mmm.domain.recommends.controller.request.NowRequest;
import com.spring.mmm.domain.recommends.controller.response.FoodInformation;
import com.spring.mmm.domain.recommends.controller.response.LunchRecommendFoodInformation;
import com.spring.mmm.domain.recommends.controller.response.ModifiedRecommendInfo;
import com.spring.mmm.domain.recommends.controller.response.NewRecommendedFoodInformation;
import com.spring.mmm.domain.recommends.domain.*;
import com.spring.mmm.domain.recommends.service.port.EatenMukboRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import com.spring.mmm.domain.weathers.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.spring.mmm.domain.recommends.domain.RecommendCategory.NORMAL;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendServiceImpl implements RecommendService{

    private final FoodRecommendRepository foodRecommendRepository;
    private final MukgroupRepository mukgroupRepository;
    private final RecommendedFoodRepository recommendedFoodRepository;
    private final FoodRepository foodRepository;
    private final MukboRepository mukboRepository;
    private final EatenMukboRepository eatenMukboRepository;
    private final WeatherService weatherService;
    private final MukgroupService mukgroupService;

    @Override
    public List<FoodInformation> recommendRandomFood() {
        int randFirst = (int) (Math.random() * 9) + 1;
        int randSecond = (int) (Math.random() * randFirst);
        return foodRepository.findAll().stream()
                .map(FoodInformation::createByFoodEntity)
                .filter(item -> item.getFoodId() % randFirst == randSecond)
                .limit(20)
                .collect(Collectors.toList());
    }

    @Override
    public List<LunchRecommendFoodInformation> lunchRecommendFood(LunchRecommendRequest lunchRecommendRequest) {
        return foodRepository.findAll().stream()
                .sorted((o1, o2) -> getScoreByFoodMukBTI(lunchRecommendRequest, o1, o2))
                .map(LunchRecommendFoodInformation::create)
                .limit(5)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveRecommend(Long mukgroupId, List<LunchRecommendFoodInformation> lunchList) {

        LocalDate date = LocalDate.now();

        MukgroupEntity mukgroup = mukgroupRepository.findByMukgroupId(mukgroupId)
                .orElseThrow();

        FoodRecommendEntity foodRecommendEntity = foodRecommendRepository.findByDateAndGroupId(date, mukgroupId)
                .orElseGet(() -> FoodRecommendEntity.create(mukgroup));

        foodRecommendRepository.saveFoodRecommend(foodRecommendEntity);

        List<MukboEntity> mukboEntities = mukboRepository.findAllMukboByGroupId(mukgroupId);

        // 하나도 없으면 => 먹먹보 새로 다 만들기
        if (!eatenMukboRepository.existsByDateAndGroupId(date, mukgroupId)){
            mukboEntities.forEach(mukbo -> {
                EatenMukboEntity eatenMukboEntity = EatenMukboEntity.create(mukbo, foodRecommendEntity);
                eatenMukboRepository.save(eatenMukboEntity);
            });
        }

        if (recommendedFoodRepository.existsByDateAndGroupId(date, mukgroupId)) {
            recommendedFoodRepository.deleteAllNormalByDateAndGroupId(date, mukgroupId, NORMAL);
        }
        lunchList
                .forEach(lunch -> {
                    FoodEntity food = foodRepository.findByName(lunch.getName()).orElseThrow();
                    RecommendedFoodEntity recommendedFoodEntity =
                            RecommendedFoodEntity.create(food, NORMAL, foodRecommendEntity);
                    recommendedFoodRepository.save(recommendedFoodEntity);
                });
    }

    @Transactional
    @Override
    public ModifiedRecommendInfo modifyNowMukbos(Long mukgroupId, NowRequest nowRequest) {

        LocalDate date = LocalDate.now();
        eatenMukboRepository.deleteAllByDateAndGroupId(date, mukgroupId);

        FoodRecommendEntity foodRecommendEntity =
                foodRecommendRepository.findByDateAndGroupId(date, mukgroupId).orElseThrow();

        for (Long mukboId : nowRequest.getNowMukbos()) {
            MukboEntity mukboEntity = mukboRepository.findByMukboId(mukboId)
                            .orElseThrow();
            eatenMukboRepository.save(EatenMukboEntity.create(mukboEntity, foodRecommendEntity));
        }

        MBTI newMBTI =
                mukgroupService.calcGroupMukBTI(mukgroupId, MukgroupMBTICalcRequest.create(nowRequest.getNowMukbos()));

        LunchRecommendRequest newLunchRecommend = LunchRecommendRequest.builder()
                .EI(newMBTI.getEi())
                .NS(newMBTI.getNs())
                .TF(newMBTI.getTf())
                .JP(newMBTI.getJp())
                .build();

        List<LunchRecommendFoodInformation> newLunchList = lunchRecommendFood(newLunchRecommend);

        saveRecommend(mukgroupId, newLunchList);

        return ModifiedRecommendInfo.create(newMBTI, newLunchList);
    }

    @Override
    public NewRecommendedFoodInformation newRecommendFood(Long mukgroupId) {

        RecommendedFoodEntity recommendedFoodEntity = recommendedFoodRepository.findByDateAndGroupIdAndCategory(LocalDate.now(), mukgroupId, RecommendCategory.NEW)
                .orElseGet(()-> newRecommendCreate(mukgroupId));

        FoodEntity newFood = recommendedFoodEntity.getFoodEntity();

        return NewRecommendedFoodInformation.create(newFood);

    }

    private RecommendedFoodEntity newRecommendCreate(Long mukgroupId) {
        List<Integer> eatenFoodIds = recommendedFoodRepository.findAllFoodIdByMukgroupId(mukgroupId);
        List<FoodEntity> foods =
                foodRepository.findAll()
                        .stream()
                        .filter(item -> !eatenFoodIds.contains(item.getFoodId()))
                        .collect(Collectors.toList());

        Collections.shuffle(foods);

        RecommendedFoodEntity recommendedFoodEntity = RecommendedFoodEntity.create(foods.getFirst(),
                RecommendCategory.NEW,
                foodRecommendRepository.findByDateAndGroupId(LocalDate.now(), mukgroupId)
                        .orElseThrow());

        recommendedFoodRepository.save(recommendedFoodEntity);

        return recommendedFoodEntity;
    }

    private int getScoreByFoodMukBTI(LunchRecommendRequest lunchRecommendRequest, FoodEntity foodOne, FoodEntity foodTwo){
        int sumOne = 0, sumTwo = 0;
        List<FoodMBTIEntity> foodOneMukBTI = foodOne.getFoodMBTIEntities();
        List<FoodMBTIEntity> foodTwoMukBTI = foodTwo.getFoodMBTIEntities();

        for(FoodMBTIEntity foodMBTIEntity : foodOneMukBTI){
            switch (foodMBTIEntity.getMukBTIEntity().getType()){
                case EI -> sumOne += Math.abs(lunchRecommendRequest.getEI() - foodMBTIEntity.getScore());
                case NS -> sumOne += Math.abs(lunchRecommendRequest.getNS() - foodMBTIEntity.getScore());
                case TF -> sumOne += Math.abs(lunchRecommendRequest.getTF() - foodMBTIEntity.getScore());
                case JP -> sumOne += Math.abs(lunchRecommendRequest.getJP() - foodMBTIEntity.getScore());
            }
        }

        for(FoodMBTIEntity foodMBTIEntity : foodTwoMukBTI){
            switch (foodMBTIEntity.getMukBTIEntity().getType()){
                case EI -> sumTwo += Math.abs(lunchRecommendRequest.getEI() - foodMBTIEntity.getScore());
                case NS -> sumTwo += Math.abs(lunchRecommendRequest.getNS() - foodMBTIEntity.getScore());
                case TF -> sumTwo += Math.abs(lunchRecommendRequest.getTF() - foodMBTIEntity.getScore());
                case JP -> sumTwo += Math.abs(lunchRecommendRequest.getJP() - foodMBTIEntity.getScore());
            }
        }

        return Integer.compare(sumOne, sumTwo);
    }

}
