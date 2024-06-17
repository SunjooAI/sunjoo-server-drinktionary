package com.sunjoo.drinktionary.service;

import com.sunjoo.drinktionary.repository.DrinkRepository;
import com.sunjoo.drinktionary.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final DrinkRepository drinkRepository;

    public ReviewService(ReviewRepository reviewRepository, DrinkRepository drinkRepository) {
        this.reviewRepository = reviewRepository;
        this.drinkRepository = drinkRepository;
    }


}
