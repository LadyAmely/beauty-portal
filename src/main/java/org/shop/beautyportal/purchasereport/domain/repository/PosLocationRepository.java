package org.shop.beautyportal.purchasereport.domain.repository;

import org.shop.beautyportal.purchasereport.domain.entities.PosLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface PosLocationRepository extends JpaRepository<PosLocation, UUID> {
    long countActiveAsOf(UUID distributorId, LocalDate asOf);
    long countOpenedBetween(UUID distributorId, LocalDate from, LocalDate to);
}
