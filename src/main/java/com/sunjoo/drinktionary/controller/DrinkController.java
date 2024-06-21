package com.sunjoo.drinktionary.controller;

import com.sunjoo.drinktionary.dto.*;
import com.sunjoo.drinktionary.entity.DrinkType;
import com.sunjoo.drinktionary.service.DrinkService;
import com.sunjoo.drinktionary.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;
    private final ReviewService reviewService;

    // 전체
    @GetMapping("/all")
    public ResponseEntity<DrinkResponses> findAll(@RequestHeader("userNo") String userNo) {
        final List<DrinkResponse> drinks = drinkService.getAllDrinks();

        return ResponseEntity.ok(new DrinkResponses(drinks));
    }

    // 개별
    @GetMapping("/{id}")
    public ResponseEntity<DrinkResponse> findById(@RequestHeader("userNo") String userNo, @PathVariable Long id) {
        final DrinkResponse drink = drinkService.getDrinkById(id);

        return ResponseEntity.ok(drink);
    }

    // 이름
    @GetMapping("/name/{name}")
    public ResponseEntity<DrinkResponses> findByName(@RequestHeader("userNo") String userNo, @PathVariable String name) {
        final List<DrinkResponse> drinks = drinkService.getDrinksByName(name);

        return ResponseEntity.ok(new DrinkResponses(drinks));
    }

    // 종류
    @GetMapping("/type/{type}")
    public ResponseEntity<DrinkResponses> findByType(@RequestHeader("userNo") String userNo, @PathVariable DrinkType type) {
        final List<DrinkResponse> drinks = drinkService.getDrinksByType(type);

        return ResponseEntity.ok(new DrinkResponses(drinks));
    }

    // 평점순
    @GetMapping("/rankings/rating")
    public ResponseEntity<DrinkRankingResponse> findTop5Rating() {
        final List<DrinkWithRatingResponse> drinks = drinkService.findTop5ByReviews();

        return ResponseEntity.ok(new DrinkRankingResponse(drinks));
    }



    // 리뷰 많은 순

    // 주류 리뷰 등록
//    @PostMapping("/{drink_id}/reviews")
//    public ResponseEntity<ReviewResponse> createReview(@PathVariable(value = "drink_id") Long drinkId,
//                                                       @RequestBody ReviewRequest reviewRequest) {
//
//    }

    // 주류 리뷰 리스트 조회
    @GetMapping("/{drink_id}/reviews")
    public ResponseEntity<ReviewResponses> findReviewsById(@RequestHeader("userNo") String userNo, @PathVariable(value = "drink_id") Long drinkId) {
        final ReviewResponses reviews = reviewService.getReviews(drinkId);

        return ResponseEntity.ok(reviews);
    }


}
