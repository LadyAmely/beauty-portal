package org.shop.beautyportal.media.domain.repositories;

import org.shop.beautyportal.media.domain.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CampaignRepository extends JpaRepository<Campaign, UUID> {
}
