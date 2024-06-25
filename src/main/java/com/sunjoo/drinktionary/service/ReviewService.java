package com.sunjoo.drinktionary.service;

import com.sunjoo.drinktionary.client.UserClient;
import com.sunjoo.drinktionary.dto.*;
import com.sunjoo.drinktionary.entity.Drink;
import com.sunjoo.drinktionary.entity.Review;
import com.sunjoo.drinktionary.repository.DrinkRepository;
import com.sunjoo.drinktionary.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final DrinkRepository drinkRepository;
    private final UserClient userClient;

    public ReviewService(ReviewRepository reviewRepository, DrinkRepository drinkRepository, UserClient userClient) {
        this.reviewRepository = reviewRepository;
        this.drinkRepository = drinkRepository;
        this.userClient = userClient;
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
    @Transactional
    public ReviewResponse postReview(Drink drink, WriteReviewRequest reviewRequest, String token) {
        // token을 사용하여 UserInfoResponse조회
        UserInfoResponse userInfoResponse = userClient.getUserInfo("Bearer " + token);

        if (userInfoResponse == null || !"SUCCESS".equals(userInfoResponse.getResultCode())) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }

        UserDTO user = userInfoResponse.getResult();


        Review review = Review.builder()
                .content(reviewRequest.getComment())
                .starRating(reviewRequest.getScore())
                .userId(user.getId())
                .drink(drink)
                .build();

        Review postedReview = reviewRepository.save(review);
        return ReviewResponse.createFromReview(postedReview);

    }



}
