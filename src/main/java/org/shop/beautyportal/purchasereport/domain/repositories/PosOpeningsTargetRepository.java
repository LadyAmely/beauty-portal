package org.shop.beautyportal.purchasereport.domain.repositories;

import org.shop.beautyportal.purchasereport.domain.entities.PosOpeningsTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PosOpeningsTargetRepository extends JpaRepository<PosOpeningsTarget, UUID> {
    Optional<PosOpeningsTarget> findByDistributorIdAndYear(UUID distributorId, Integer year);
}
