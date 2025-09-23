package org.shop.beautyportal.saleschannels.ports.output.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(name = "DistributorResponse")
public record DistributorResponse(
        @Schema(description = "Distributor identifier (UUID)")
        UUID id,

        @Schema(description = "Unique distributor code", example = "DIST-PL-001")
        String code,

        @Schema(description = "Distributor's full name", example = "Acme Distribution Sp. z o.o.")
        String name
) {}

