package org.shop.beautyportal.media.domain.repositories;

import org.shop.beautyportal.media.domain.entities.SkuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkuProductRepository extends JpaRepository<SkuProduct, String> {
    boolean existsBySku(String sku);
    List<SkuProduct> findTop20BySkuStartingWithIgnoreCase(String prefix);
}
