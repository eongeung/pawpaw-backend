package com.pawpaw.pawpaw.domain.user.service;

import com.pawpaw.pawpaw.domain.user.dto.UserResponseDto;
import com.pawpaw.pawpaw.domain.user.dto.UserUpdateRequestDto;
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
    public UserResponseDto updateMe(UserUpdateRequestDto dto, User user) {
        if (!user.getNickname().equals(dto.getNickname()) && userRepository.existsByNickname(dto.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }
        User managed = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        managed.update(dto.getNickname(), dto.getAddress());
        return new UserResponseDto(managed);
    }
}
