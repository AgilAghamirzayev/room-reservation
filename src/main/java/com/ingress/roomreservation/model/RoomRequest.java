package com.ingress.roomreservation.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record RoomRequest(Long userId,
                          @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
                          LocalDateTime reservedAt) {

}
