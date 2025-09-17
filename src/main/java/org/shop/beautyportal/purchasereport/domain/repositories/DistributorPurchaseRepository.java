package org.shop.beautyportal.purchasereport.domain.repositories;

import org.shop.beautyportal.purchasereport.domain.entities.DistributorPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistributorPurchaseRepository extends JpaRepository<DistributorPurchase, UUID> {
}
