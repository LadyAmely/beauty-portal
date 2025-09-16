package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.QuarterChannelAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuarterChannelAmountRepository  extends JpaRepository<QuarterChannelAmount, UUID> {
}
