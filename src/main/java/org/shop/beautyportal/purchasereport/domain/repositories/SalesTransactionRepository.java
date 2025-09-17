package org.shop.beautyportal.purchasereport.domain.repositories;

import org.shop.beautyportal.purchasereport.domain.entities.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface SalesTransactionRepository extends JpaRepository<SalesTransaction, UUID> {
    BigDecimal sumNetAmountByDistributorAndPeriod(UUID distributorId, LocalDateTime from, LocalDateTime to);
}

