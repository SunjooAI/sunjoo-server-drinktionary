package com.sunjoo.drinktionary.dto;

import com.sunjoo.drinktionary.entity.Drink;
import com.sunjoo.drinktionary.entity.DrinkType;
import com.sunjoo.drinktionary.entity.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class DrinkWithRatingResponse {
    private final Long id;
    private final String name;
    private final DrinkType type;
    private final double rating;
    private final int reviewCount;
    private final String image;

    public static DrinkWithRatingResponse createFromRating(final List<Review> reviews) {
        final Drink drink = reviews.get(0).getDrink();

        return new DrinkWithRatingResponse(drink.getId(),
                drink.getName(),
                drink.getType(),
                calculatingRating(reviews),
                reviews.size(),
                drink.getImage());
    }

    private static double calculatingRating(final List<Review> reviews) {
        final double sum = reviews.stream()
                .mapToDouble(Review::getStarRating)
                .sum();

        return Math.round(sum / (double) reviews.size() * 100) / 100.0;
    }



}
