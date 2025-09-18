package org.shop.beautyportal.media.domain.repositories;

import org.shop.beautyportal.media.domain.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CampaignRepository extends JpaRepository<Campaign, UUID> {

    Optional<Campaign> findByYearMonth(String yearMonth);
    boolean existsByYearMonth(String yearMonth);
    List<Campaign> findAllByYearMonthIn(Collection<String> yearMonths);
}

