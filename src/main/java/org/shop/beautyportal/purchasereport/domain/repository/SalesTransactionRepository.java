package org.shop.beautyportal.purchasereport.domain.repository;

import org.shop.beautyportal.purchasereport.domain.entities.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalesTransactionRepository extends JpaRepository<SalesTransaction, UUID> {
}
