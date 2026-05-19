package com.pawpaw.pawpaw.domain.user.service;

import com.pawpaw.pawpaw.domain.user.dto.UpdateProfileRequestDto;
import com.pawpaw.pawpaw.domain.user.dto.UserProfileResponseDto;
import com.pawpaw.pawpaw.domain.user.dto.UserResponseDto;
import com.pawpaw.pawpaw.domain.user.entity.User;
import com.pawpaw.pawpaw.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDto getMe(User user) {
        return new UserResponseDto(user);
    }

    @Transactional
    public UserProfileResponseDto updateMyProfile(User user, UpdateProfileRequestDto dto) {
        User managed = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        managed.updateProfile(dto.getNickname(), dto.getAddress());
        return new UserProfileResponseDto(managed);
    }
}
