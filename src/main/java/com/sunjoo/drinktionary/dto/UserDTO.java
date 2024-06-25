package com.sunjoo.drinktionary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserDTO {
    private Long userNo;
    private String id;
    private String name;
    private String type;
}
