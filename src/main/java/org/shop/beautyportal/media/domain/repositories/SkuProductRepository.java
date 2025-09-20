package org.shop.beautyportal.media.domain.repositories;

import org.shop.beautyportal.media.domain.entities.SkuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkuProductRepository extends JpaRepository<SkuProduct, UUID> {
}
