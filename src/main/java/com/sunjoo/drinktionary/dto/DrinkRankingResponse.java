package com.sunjoo.drinktionary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class DrinkRankingResponse {
    private final List<DrinkWithRatingResponse> ranking;
}
