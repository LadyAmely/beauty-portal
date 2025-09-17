package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.QuarterReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuarterReportRepository extends JpaRepository<QuarterReport, UUID> {
    boolean existsByDistributorIdAndYearAndQuarter(UUID distributorId, Integer year, Integer quarter);
}
