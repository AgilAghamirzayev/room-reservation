package com.ingress.roomreservation.scheduler;

import com.ingress.roomreservation.service.RoomReservationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {

    private final RoomReservationService reservationService;

    @PostConstruct
    public void createVacantRooms() {
        reservationService.createVacantRooms();
    }

    @Scheduled(cron = "* 15 * * * *")
    public void createEvery15min() {
        reservationService.createEvery15min();
    }
}
