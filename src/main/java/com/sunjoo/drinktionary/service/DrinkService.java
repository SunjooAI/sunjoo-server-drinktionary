package com.sunjoo.drinktionary.service;

import com.sunjoo.drinktionary.entity.DrinkType;
import com.sunjoo.drinktionary.repository.DrinkRepository;
import com.sunjoo.drinktionary.dto.DrinkResponse;
import com.sunjoo.drinktionary.entity.Drink;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

}
