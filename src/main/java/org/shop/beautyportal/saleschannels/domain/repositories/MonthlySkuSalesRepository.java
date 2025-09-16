package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.MonthlySkuSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MonthlySkuSalesRepository extends JpaRepository<MonthlySkuSales, UUID> {
}
