package org.shop.beautyportal.media.ports.out.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CampaignResponse(
        UUID id,
        String name,
        String yearMonth,
        OffsetDateTime createdAt
) {}
