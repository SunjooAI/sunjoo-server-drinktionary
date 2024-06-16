package com.sunjoo.drinktionary.service;

import com.sunjoo.drinktionary.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
