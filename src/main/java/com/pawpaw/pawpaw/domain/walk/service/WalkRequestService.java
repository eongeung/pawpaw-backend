package com.pawpaw.pawpaw.domain.walk.service;

import com.pawpaw.pawpaw.domain.pet.entity.Pet;
import com.pawpaw.pawpaw.domain.pet.repository.PetRepository;
import com.pawpaw.pawpaw.domain.walk.dto.WalkRequestDto;
import com.pawpaw.pawpaw.domain.walk.dto.WalkRequestResponseDto;
import com.pawpaw.pawpaw.domain.walk.entity.WalkRequest;
import com.pawpaw.pawpaw.domain.walk.entity.WalkRequestStatus;
import com.pawpaw.pawpaw.domain.user.entity.User;
import com.pawpaw.pawpaw.domain.walk.repository.WalkRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalkRequestService {

    private final WalkRequestRepository walkRequestRepository;
    private final PetRepository petRepository;

    @Transactional
    public WalkRequestResponseDto createWalkRequest(WalkRequestDto dto, User user) {
        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new IllegalArgumentException("펫을 찾을 수 없습니다."));

        WalkRequest walkRequest = WalkRequest.builder()
                .user(user)
                .pet(pet)
                .title(dto.getTitle())
                .content(dto.getContent())
                .walkDate(dto.getWalkDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .reward(dto.getReward())
                .location(dto.getLocation())
                .status(WalkRequestStatus.OPEN)
                .build();

        return new WalkRequestResponseDto(walkRequestRepository.save(walkRequest));
    }

    @Transactional(readOnly = true)
    public List<WalkRequestResponseDto> getAllWalkRequests() {
        return walkRequestRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(WalkRequestResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public WalkRequestResponseDto getWalkRequest(Long walkRequestId) {
        WalkRequest walkRequest = walkRequestRepository.findById(walkRequestId)
                .orElseThrow(() -> new IllegalArgumentException("산책 요청을 찾을 수 없습니다."));
        return new WalkRequestResponseDto(walkRequest);
    }

    @Transactional
    public void deleteWalkRequest(Long walkRequestId, User user) {
        WalkRequest walkRequest = walkRequestRepository.findById(walkRequestId)
                .orElseThrow(() -> new IllegalArgumentException("산책 요청을 찾을 수 없습니다."));

        if (!walkRequest.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        walkRequestRepository.delete(walkRequest);
    }
}
