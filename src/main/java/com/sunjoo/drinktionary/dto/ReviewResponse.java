package com.sunjoo.drinktionary.dto;

import com.sunjoo.drinktionary.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Builder
@NoArgsConstructor(force = true)
public class ReviewResponse {
    private final long id;
    private final String userId;
    private final double rating;
    private final String comment;
    private final Date date;
    private final long drinkId;



}
