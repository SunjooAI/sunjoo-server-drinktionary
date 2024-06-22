package com.sunjoo.drinktionary.service;

import com.sunjoo.drinktionary.dto.ReviewResponse;
import com.sunjoo.drinktionary.dto.ReviewResponses;
import com.sunjoo.drinktionary.dto.WriteReviewRequest;
import com.sunjoo.drinktionary.entity.Drink;
import com.sunjoo.drinktionary.entity.Review;
import com.sunjoo.drinktionary.repository.DrinkRepository;
import com.sunjoo.drinktionary.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final DrinkRepository drinkRepository;

    public ReviewService(ReviewRepository reviewRepository, DrinkRepository drinkRepository) {
        this.reviewRepository = reviewRepository;
        this.drinkRepository = drinkRepository;
    }

    // 리뷰 조회
    public ReviewResponses getReviews(Long drinkId) {
        final String name = drinkRepository.findById(drinkId).get().getName();

        return ReviewResponses.fromReviews(getReviewsByDrinkName(name));
    }

    public List<Review> getReviewsByDrinkName(final String name) {
        return reviewRepository.findByDrinkName(name).stream()
                .sorted(comparing(Review::getCreateAt).reversed())
                .collect(Collectors.toList());
    }

    public int getReviewSize(long drinkId) {
        return reviewRepository.countByDrinkId(drinkId);
    }

    public double getAverageScore(long drinkId) {
        final List<Review> reviews = reviewRepository.findByDrinkId(drinkId);
        double total = reviews.stream()
                .mapToDouble(Review::getStarRating)
                .sum();
        return Math.round(total / (double) reviews.size() * 100) / 100.0;
    }

    // 리뷰 등록
//    @Transactional
//    public ReviewResponse postReview(Drink drink, WriteReviewRequest reviewRequest) {
//
//    }



}
