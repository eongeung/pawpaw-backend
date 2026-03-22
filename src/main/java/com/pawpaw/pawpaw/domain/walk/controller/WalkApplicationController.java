package com.pawpaw.pawpaw.domain.walk.controller;

import com.pawpaw.pawpaw.domain.walk.dto.WalkApplicationResponseDto;
import com.pawpaw.pawpaw.domain.walk.service.WalkApplicationService;
import com.pawpaw.pawpaw.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/walk-requests")
@RequiredArgsConstructor
public class WalkApplicationController {

    private final WalkApplicationService walkApplicationService;

    @PostMapping("/{walkRequestId}/applications")
    public ResponseEntity<WalkApplicationResponseDto> apply(
            @PathVariable Long walkRequestId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(walkApplicationService.apply(walkRequestId, user));
    }

    @GetMapping("/{walkRequestId}/applications")
    public ResponseEntity<List<WalkApplicationResponseDto>> getApplications(
            @PathVariable Long walkRequestId) {
        return ResponseEntity.ok(walkApplicationService.getApplications(walkRequestId));
    }

    @PatchMapping("/{walkRequestId}/applications/{applicationId}/accept")
    public ResponseEntity<WalkApplicationResponseDto> acceptApplication(
            @PathVariable Long walkRequestId,
            @PathVariable Long applicationId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(walkApplicationService.acceptApplication(walkRequestId, applicationId, user));
    }
}
