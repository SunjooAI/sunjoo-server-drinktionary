package com.sunjoo.drinktionary.service;

import com.sunjoo.drinktionary.dto.DrinkWithRatingResponse;
import com.sunjoo.drinktionary.entity.DrinkType;
import com.sunjoo.drinktionary.repository.DrinkRepository;
import com.sunjoo.drinktionary.dto.DrinkResponse;
import com.sunjoo.drinktionary.entity.Drink;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

@Service
@Transactional(readOnly = true)
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final ReviewService reviewService;

    public DrinkService(DrinkRepository drinkRepository, ReviewService reviewService) {
        this.drinkRepository = drinkRepository;
        this.reviewService = reviewService;
    }

    // 전체 조회
    public List<DrinkResponse> getAllDrinks() {
        List<Drink> drinks = drinkRepository.findAll();

        return drinks.stream()
                .map(drink -> DrinkResponse.response(drink,
                        reviewService.getAverageScore(drink.getId()),
                        reviewService.getReviewSize(drink.getId())))
                .collect(Collectors.toList());

    }

    // 개별 조회
    public DrinkResponse getDrinkById(final Long id) {
        final Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주류입니다."));

        return DrinkResponse.response(drink, reviewService.getAverageScore(id), reviewService.getReviewSize(id));
    }

    // 타입 조회
    public List<DrinkResponse> getDrinksByType(final DrinkType type) {
        final List<Drink> drinks = drinkRepository.findByType(type);

        return drinks.stream()
                .map(drink -> DrinkResponse.response(drink,
                        reviewService.getAverageScore(drink.getId()),
                        reviewService.getReviewSize(drink.getId())))
                .toList();
    }

    // 이름 조회
    public List<DrinkResponse> getDrinksByName(String name) {
        final List<Drink> drinks = drinkRepository.findAll().stream()
                .filter(drink -> drink.getName().contains(name))
                .toList();

        return drinks.stream()
                .map(drink -> DrinkResponse.response(drink,
                        reviewService.getAverageScore(drink.getId()),
                        reviewService.getReviewSize(drink.getId())))
                .collect(Collectors.toList());
    }

    // 평점 조회
    public List<DrinkWithRatingResponse> getDrinksWithRating() {
        return drinkRepository.findAll().stream() // 모든 음료를 조회하고 스트림으로 변환
                .map(Drink::getName) // 각 음료 객체를 음료 이름으로 변환
                .map(reviewService::getReviewsByDrinkName) // 음료 이름으로 리뷰 목록을 조회
                .filter(list -> !list.isEmpty()) // 리뷰 목록이 비어 있지 않은 경우만 필터링
                .map(DrinkWithRatingResponse::createFromRating) // 리뷰 목록을 DrinkWithRatingResponse 객체로 변환
                .toList(); // 최종 결과를 리스트로 변환하여 반환

    }

    public List<DrinkWithRatingResponse> findTop5ByReviews() {
        final List<DrinkWithRatingResponse> drinks = getDrinksWithRating(); // 모든 음료와 리뷰 정보를 가져옴

        return drinks.stream()
                .filter(drink -> drink.getReviewCount() != 0) // 리뷰 수가 0이 아닌 경우만 필터링
                .sorted(comparingInt(DrinkWithRatingResponse::getReviewCount).reversed()) // 리뷰 수를 기준으로 내림차순 정렬
                .limit(5) // 상위 5개만 선택
                .toList(); // 최종 결과를 리스트로 변환하여 반환
    }

}
