package com.ingress.roomreservation.model;

import com.ingress.roomreservation.entity.RoomEntity;
import com.ingress.roomreservation.entity.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RoomResponse(Long id, Long userId, Status status, LocalDateTime reservedAt) {
    public static RoomResponse from(RoomEntity roomEntity) {
        return RoomResponse.builder()
                .id(roomEntity.getId())
                .userId(roomEntity.getUserId())
                .status(roomEntity.getStatus())
                .reservedAt(roomEntity.getReservedAt())
                .build();
    }
}
