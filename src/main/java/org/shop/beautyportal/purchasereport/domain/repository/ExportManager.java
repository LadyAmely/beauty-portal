package org.shop.beautyportal.purchasereport.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExportManager extends JpaRepository<ExportManager, UUID> {
}
