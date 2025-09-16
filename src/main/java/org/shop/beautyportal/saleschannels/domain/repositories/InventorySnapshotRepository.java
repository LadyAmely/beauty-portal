package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.InventorySnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventorySnapshotRepository extends JpaRepository<InventorySnapshot, UUID> {
}
