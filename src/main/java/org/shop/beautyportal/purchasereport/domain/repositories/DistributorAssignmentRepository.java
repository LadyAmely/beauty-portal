package org.shop.beautyportal.purchasereport.domain.repositories;

import org.shop.beautyportal.purchasereport.domain.entities.DistributorAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistributorAssignmentRepository extends JpaRepository<DistributorAssignment, UUID> {
}
