package com.pawpaw.pawpaw.domain.user.controller;

import com.pawpaw.pawpaw.domain.user.dto.UserResponseDto;
import com.pawpaw.pawpaw.domain.user.dto.UserUpdateRequestDto;
import com.pawpaw.pawpaw.domain.user.entity.User;
import com.pawpaw.pawpaw.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMe(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getMe(user));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateMe(
            @Valid @RequestBody UserUpdateRequestDto dto,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.updateMe(dto, user));
    }
}
