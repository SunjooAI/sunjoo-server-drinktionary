package com.sunjoo.drinktionary.dto;

import com.sunjoo.drinktionary.entity.Drink;
import com.sunjoo.drinktionary.entity.DrinkType;
import com.sunjoo.drinktionary.entity.Sentiment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
@NoArgsConstructor(force = true)
public class DrinkResponse {
    private final long id;
    private final String name;
    private final double dosu;
    private final DrinkType type;
    private final long price;
    private final long capacity;
    private final double sugar;
    private final Sentiment sentiment;
    private final String image;
    private final double rating;
    private final int reviewCount;

    public static DrinkResponse response(Drink drink, double rating, int reviewCount) {
        return DrinkResponse.builder()
                .id(drink.getId())
                .name(drink.getName())
                .dosu(drink.getDosu())
                .type(drink.getType())
                .price(drink.getPrice())
                .capacity(drink.getCapacity())
                .sugar(drink.getSugar())
                .sentiment(null) // 주류 추천 분석 결과
                .image(drink.getImage())
                .rating(rating)
                .reviewCount(reviewCount)
                .build();
    }

}
