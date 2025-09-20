package org.shop.beautyportal.purchasereport.domain.repository;

import org.shop.beautyportal.purchasereport.domain.entities.PurchaseReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface PurchaseReportRepository extends JpaRepository<PurchaseReport, UUID> {
    BigDecimal findBudget(UUID distributorId, int year, int quarter);
}
