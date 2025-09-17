package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistributorRepository extends JpaRepository<Distributor, UUID> {
}
