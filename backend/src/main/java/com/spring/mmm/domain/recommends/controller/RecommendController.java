package com.spring.mmm.domain.recommends.controller;

import com.spring.mmm.domain.recommends.controller.request.LunchRecommendRequest;
import com.spring.mmm.domain.recommends.controller.request.NowRequest;
import com.spring.mmm.domain.recommends.controller.request.XYRequest;
import com.spring.mmm.domain.recommends.controller.response.*;
import com.spring.mmm.domain.recommends.exception.RecommendErrorCode;
import com.spring.mmm.domain.recommends.exception.RecommendException;
import com.spring.mmm.domain.recommends.service.RecommendService;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.weathers.service.WeatherService;
import com.spring.mmm.domain.weathers.service.port.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;
    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;

    @GetMapping
    public ResponseEntity<RecommandRandomFood> recommendRandomFood(){
        return ResponseEntity.ok(RecommandRandomFood.builder()
                .foods(recommendService.recommendRandomFood())
                .build());
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<LunchRecommendResponse> recommendLunch(
            Integer ei, Integer ns, Integer tf, Integer jp,
            @PathVariable Long groupId
    ){
        LunchRecommendResponse lunch = LunchRecommendResponse.builder()
                .foods(recommendService.lunchRecommendFood(LunchRecommendRequest.builder()
                        .EI(ei)
                        .NS(ns)
                        .TF(tf)
                        .JP(jp)
                        .build()))
                .build();

        recommendService.saveRecommend(groupId, lunch.getFoods());

        return ResponseEntity.ok(lunch);
    }

    @GetMapping("/groups/{groupId}/new")
    public ResponseEntity<NewRecommendedFoodInformation> recommendNewFood(
            @PathVariable Long groupId
    ){
        return ResponseEntity.ok(recommendService.newRecommendFood(groupId));
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherFoodInfo> recommendWeatherFood(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            Double latitude, Double longitude
    ) {
        WeatherDTO weatherDTO = weatherService.getWeather(latitude, longitude);

        WeatherFoodInfo weatherFoodInfo = weatherService.getWeatherFood(userDetails ,weatherDTO);

        return ResponseEntity.ok(weatherFoodInfo);
    }

    @PostMapping("/groups/{groupId}/mukbos-now")
    public ResponseEntity<ModifiedRecommendInfo> modifyEatingMukbos(@PathVariable Long groupId, @RequestBody NowRequest nowRequest) {
        recommendService.modifyNowMukbos(groupId, nowRequest);

        return ResponseEntity.ok(recommendService.modifyNowMukbos(groupId, nowRequest));
    }
}
