package com.ingress.roomreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RoomReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomReservationApplication.class, args);
    }

}
