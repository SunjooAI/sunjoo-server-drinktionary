package com.sunjoo.drinktionary.controller;

import com.sunjoo.drinktionary.dto.DrinkResponse;
import com.sunjoo.drinktionary.dto.DrinkResponses;
import com.sunjoo.drinktionary.dto.ReviewResponse;
import com.sunjoo.drinktionary.dto.ReviewResponses;
import com.sunjoo.drinktionary.entity.DrinkType;
import com.sunjoo.drinktionary.service.DrinkService;
import com.sunjoo.drinktionary.service.ReviewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final ReviewService reviewService;

    // 전체
    @GetMapping
    public ResponseEntity<DrinkResponses> findAll() {
        final List<DrinkResponse> drinks = drinkService.getAllDrinks();

        return ResponseEntity.ok(new DrinkResponses(drinks));
    }

    // 개별
    @GetMapping("/{id}")
    public ResponseEntity<DrinkResponse> findById(@PathVariable Long drinkId) {
        final DrinkResponse drink = drinkService.getDrinkById(drinkId);

        return ResponseEntity.ok(drink);
    }

    // 이름
    @GetMapping("/name/{name}")
    public ResponseEntity<DrinkResponses> findByName(@PathVariable String name) {
        final List<DrinkResponse> drinks = drinkService.getDrinksByName(name);

        return ResponseEntity.ok(new DrinkResponses(drinks));
    }

    // 종류
    @GetMapping("/type/{type}")
    public ResponseEntity<DrinkResponses> findByType(@PathVariable DrinkType type) {
        final List<DrinkResponse> drinks = drinkService.getDrinksByType(type);

        return ResponseEntity.ok(new DrinkResponses(drinks));
    }

    // 평점순


    // 리뷰 많은 순

    // 주류 리뷰 등록
//    @PostMapping("/{drink_id}/reviews")
//    public ResponseEntity<ReviewResponse> createReview(@PathVariable(value = "drink_id") Long drinkId,
//                                                       @RequestBody ReviewRequest reviewRequest) {
//
//    }

    // 주류 리뷰 리스트 조회
    @GetMapping("/{drink_id}/reviews")
    public ResponseEntity<ReviewResponses> findReviewsById(@PathVariable(value = "drink_id") Long drinkId) {
        final ReviewResponses reviews = reviewService.getReviews(drinkId);

        return ResponseEntity.ok(reviews);
    }


}
