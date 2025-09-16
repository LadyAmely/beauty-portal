package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.DistributorIdentityMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistributorIdentityMapRepository extends JpaRepository<DistributorIdentityMap, UUID> {
}
