package org.shop.beautyportal.saleschannels.ports.input.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "CreateDistributorRequest")
public record CreateDistributorRequest(
        @Schema(description = "Unique distributor code", example = "DIST-PL-001")
        @NotBlank @Size(max = 64)
        String code,

        @Schema(description = "Distributor's full name", example = "Acme Distribution Sp. z o.o.")
        @NotBlank @Size(max = 255)
        String name
) {}
