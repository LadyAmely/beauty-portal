package org.shop.beautyportal.media.ports.out.response;

import java.time.OffsetDateTime;

public record SkuProductResponse(
        String sku,
        OffsetDateTime createdAt
) {}

