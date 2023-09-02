package com.ingress.roomreservation.controller;

import com.ingress.roomreservation.model.RoomRequest;
import com.ingress.roomreservation.model.RoomResponse;
import com.ingress.roomreservation.service.RoomReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/times")
public class RoomReservationController {

    private final RoomReservationService roomReservationService;

    @GetMapping
    public List<RoomResponse> getAllRooms() {
        return roomReservationService.getAllRooms();
    }


    @GetMapping("reserved")
    public List<RoomResponse> getAllReservedRooms() {
        return roomReservationService.getAllReserved();
    }

    @GetMapping("/{id}")
    public List<RoomResponse> getAllRoomsByUserId(@PathVariable Long id) {
        return roomReservationService.getAllRoomsByUserId(id);
    }

    @PostMapping
    public void reserve(@RequestBody RoomRequest request) {
        roomReservationService.reserve(request);
    }
}
