package com.pawpaw.pawpaw.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    @NotBlank
    private String nickname;

    private String address;
}
