package org.shop.beautyportal.purchasereport.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PosOpeningsTargetRepository extends JpaRepository<PosOpeningsTargetRepository, UUID> {
    Optional<Integer> findTargetFor(UUID distributorId, Integer year);
}
