package org.shop.beautyportal.purchasereport.domain.repositories;

import org.shop.beautyportal.purchasereport.domain.entities.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistributorRepository extends JpaRepository<Distributor, UUID> {
}
