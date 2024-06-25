package com.sunjoo.drinktionary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserInfoResponse {
    private String resultCode;
    private UserDTO result;
}
