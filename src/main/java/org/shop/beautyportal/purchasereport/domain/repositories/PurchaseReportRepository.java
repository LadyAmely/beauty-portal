package org.shop.beautyportal.purchasereport.domain.repositories;

import org.shop.beautyportal.purchasereport.domain.entities.PurchaseReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseReportRepository extends JpaRepository<PurchaseReport, Long> {
}
