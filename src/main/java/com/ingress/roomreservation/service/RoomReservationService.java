package com.ingress.roomreservation.service;

import com.ingress.roomreservation.entity.RoomEntity;
import com.ingress.roomreservation.entity.Status;
import com.ingress.roomreservation.model.RoomRequest;
import com.ingress.roomreservation.model.RoomResponse;
import com.ingress.roomreservation.repository.RoomReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;

    public List<RoomResponse> getAllRooms() {
        return roomReservationRepository.findAll()
                .stream()
                .map(RoomResponse::from)
                .toList();
    }

    public List<RoomResponse> getAllReserved() {
        return roomReservationRepository.findAllByStatus(Status.RESERVED)
                .stream()
                .map(RoomResponse::from)
                .toList();
    }

    public List<RoomResponse> getAllRoomsByUserId(Long userId) {
        return roomReservationRepository.findAllByUserId(userId)
                .stream()
                .map(RoomResponse::from)
                .toList();
    }

    @Transactional
    public void reserve(RoomRequest request) {
        log.info("reserve.START REQUEST: {}",request);

        RoomEntity roomEntity = roomReservationRepository.findByReservedAt(request.reservedAt())
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (Status.RESERVED.equals(roomEntity.getStatus())) {
            throw new RuntimeException("This time already reserved");
        }

        roomEntity.setUserId(request.userId());
        roomEntity.setStatus(Status.RESERVED);

        roomReservationRepository.save(roomEntity);

        log.info("reserve.END");
    }

    public void createVacantRooms() {
        log.info("createVacantRooms.START");
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(LocalTime.now().getHour(), 0));
        int hour = now.getHour();
        while (hour >= now.getHour() - 12) {
            RoomEntity room = RoomEntity.builder()
                    .reservedAt(now)
                    .status(Status.VACANT)
                    .build();
            roomReservationRepository.save(room);
            now = now.plusMinutes(15);
        }

        log.info("createVacantRooms.END");
    }

    public void createEvery15min() {
        log.info("createEvery15min.START");

        RoomEntity latestReservedRoom = roomReservationRepository.getLatestReservedRoom();

        RoomEntity room = RoomEntity.builder()
                .reservedAt(latestReservedRoom.getReservedAt().plusMinutes(15))
                .status(Status.VACANT)
                .build();

        roomReservationRepository.save(room);

        log.info("createEvery15min.END");

    }
}
