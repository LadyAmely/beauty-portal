package org.shop.beautyportal.purchasereport.domain.repository;

import org.shop.beautyportal.purchasereport.domain.entities.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface SalesTransactionRepository extends JpaRepository<SalesTransaction, UUID> {
    BigDecimal sumNetAmount(UUID distributorId, LocalDate from, LocalDate to);
}
