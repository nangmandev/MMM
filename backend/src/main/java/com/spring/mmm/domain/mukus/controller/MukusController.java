package com.spring.mmm.domain.mukus.controller;

import com.spring.mmm.domain.mukus.controller.request.RecommendFoodChoiceRequest;
import com.spring.mmm.domain.mukus.controller.response.*;
import com.spring.mmm.domain.mukus.service.MukusService;
import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodCategoryRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("mukus")
@RequiredArgsConstructor
public class MukusController {

    private final MukusService mukusService;

    private final FoodRepository foodRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final FoodRecommendRepository foodRecommendRepository;
    private final RecommendedFoodRepository recommendedFoodRepository;

    @GetMapping("/groups/{groupId}/recent")
    public ResponseEntity<MukusRecentResponse> getRecentMukus(@PathVariable Long groupId){

        return ResponseEntity.ok(mukusService.getRecentMukus(groupId));
    }

    @PostMapping("/groups/{groupId}/recent")
    public ResponseEntity<Void> selectRecentMukus(@PathVariable Long groupId, @RequestBody RecommendFoodChoiceRequest recommendFoodChoiceRequest) {

        mukusService.selectRecentMukus(recommendFoodChoiceRequest.getRecommendFoodId());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<MukusCalendarResponse> getAllMukus(@PathVariable Long groupId,
                                                             @RequestParam Integer year,
                                                             @RequestParam Integer month
    ){
        return ResponseEntity.ok(mukusService.getMukusMonth(groupId, year, month));
    }

    @GetMapping("/groups/{groupId}/date")
    public ResponseEntity<MukusDetailResponse> getMukusDetail(@PathVariable Long groupId,
                                           @RequestParam Integer year,
                                           @RequestParam Integer month,
                                           @RequestParam Integer day
    ) {
        return ResponseEntity.ok(mukusService.getMukusDetail(groupId, year, month, day));
    }

}
