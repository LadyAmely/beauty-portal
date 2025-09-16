package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.ExchangeRateMonthly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExchangeRateMonthlyRepository extends JpaRepository<ExchangeRateMonthly, UUID> {
}
