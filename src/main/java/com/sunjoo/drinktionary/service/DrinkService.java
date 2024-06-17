package com.sunjoo.drinktionary.service;

import com.sunjoo.drinktionary.repository.DrinkRepository;
import com.sunjoo.drinktionary.dto.DrinkResponse;
import com.sunjoo.drinktionary.entity.Drink;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
//    public List<DrinkResponse> getAllDrinks() {
//        List<Drink> drinks = drinkRepository.findAll();
//
//
//    }


}
