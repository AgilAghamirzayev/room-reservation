package com.ingress.roomreservation.repository;

import com.ingress.roomreservation.entity.Status;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ingress.roomreservation.entity.RoomEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoomReservationRepository extends JpaRepository<RoomEntity, Long> {
    List<RoomEntity> findAllByUserId(Long userId);

    @Query("SELECT r FROM RoomEntity r  ORDER BY r.reservedAt DESC LIMIT 1")
    RoomEntity getLatestReservedRoom();

//    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<RoomEntity> findByReservedAt(LocalDateTime reservedAt);

    List<RoomEntity> findAllByStatus(Status status);
}
