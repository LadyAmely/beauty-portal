package org.shop.beautyportal.purchasereport.domain.repositories;

import org.shop.beautyportal.purchasereport.domain.entities.PosLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PosLocationRepository extends JpaRepository<PosLocation, UUID> {
    Integer countActiveAsOf(UUID distributorId, LocalDateTime asOf);
    Integer countOpenedBetween(UUID distributorId, LocalDateTime from, LocalDateTime to);
}
