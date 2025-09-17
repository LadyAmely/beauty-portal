package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.MonthlySkuSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MonthlySkuSalesRepository extends JpaRepository<MonthlySkuSales, UUID> {
    List<MonthlySkuSales> findByDistributorIdAndYearAndMonth(UUID distributorId, Integer year, Integer month);
}
