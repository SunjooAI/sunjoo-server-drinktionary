package com.sunjoo.drinktionary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class DrinkResponses {
    private final List<DrinkResponse> drinks;
}
