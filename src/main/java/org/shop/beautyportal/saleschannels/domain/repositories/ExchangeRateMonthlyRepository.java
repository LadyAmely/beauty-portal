package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.ExchangeRateMonthly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface ExchangeRateMonthlyRepository extends JpaRepository<ExchangeRateMonthly, UUID> {
    Optional<ExchangeRateMonthly> findByYearAndMonthAndCurrency(int year, int month, String currency);
}
