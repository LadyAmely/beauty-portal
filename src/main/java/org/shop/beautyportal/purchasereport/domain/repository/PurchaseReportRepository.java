package org.shop.beautyportal.purchasereport.domain.repository;

import org.shop.beautyportal.purchasereport.domain.entities.PurchaseReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseReportRepository extends JpaRepository<PurchaseReport, UUID> {
    Optional<PurchaseReport> findByDistributorIdAndYearAndQuarter(UUID distributorId, Integer year, Integer quarter);
}
