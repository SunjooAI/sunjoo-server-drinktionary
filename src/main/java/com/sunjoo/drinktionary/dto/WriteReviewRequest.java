package com.sunjoo.drinktionary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WriteReviewRequest {

    private long userId;
    private String comment;
    private double score;
    private String date;
}
