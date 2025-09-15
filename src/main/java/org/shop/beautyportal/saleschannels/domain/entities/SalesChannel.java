package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "SalesChannel",
        description = "Distribution channel through which sales are made"
)
public enum SalesChannel {

    @Schema(description = "Sales in the professional channel (e.g., B2B professionals, clinics, salons)")
    PROFESSIONAL,
    @Schema(description = "Sales in the pharmacy channel")
    PHARMACY,
    @Schema(description = "Sales in the B2C e-commerce channel (direct online store)")
    ECOMMERCE_B2C,
    @Schema(description = "Sales in the B2B e-commerce channel (online business clients)")
    ECOMMERCE_B2B,
    @Schema(description = "Sales through third-party platforms (marketplaces, aggregators)")
    THIRD_PARTY,
    @Schema(description = "Sales in other channels (events, fairs, direct retail)")
    OTHER
}
