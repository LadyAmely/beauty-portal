package org.shop.beautyportal.purchasereport.domain.repository;

import org.shop.beautyportal.purchasereport.domain.entities.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface SalesTransactionRepository extends JpaRepository<SalesTransaction, UUID> {

    @Query("""
    select sum(t.netAmount)
    from SalesTransaction t
    where t.distributor.id = :distributorId
      and t.occurredAt >= :from
      and t.occurredAt  < :to
""")
   BigDecimal sumNetAmount(UUID distributorId, LocalDate from, LocalDate to);
}
