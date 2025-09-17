package org.shop.beautyportal.saleschannels.ports.output.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
@Schema(
        name = "QuarterReportCreatedResponse",
        description = "Response payload returned after successfully creating a quarterly report"
)
public class QuarterReportCreatedResponse {

    @Schema(
            description = "Unique identifier of the created report",
            example = "550e8400-e29b-41d4-a716-446655440000"
    )
    UUID id;

    @Schema(
            description = "Year of the report",
            example = "2025"
    )
    Integer year;

    @Schema(
            description = "Quarter number (1-4)",
            example = "2"
    )
    Integer quarter;

    @Schema(
            description = "Input currency (ISO 4217)",
            example = "PLN"
    )
    String inputCurrency;

    @Schema(
            description = "Number of newly acquired clients in this quarter",
            example = "7"
    )
    Integer newClients;

    @Schema(
            description = "Sum of all channel amounts in input currency",
            example = "45678.90"
    )
    BigDecimal totalInputCcy;

    @Schema(
            description = "Sum of all channel amounts converted to EUR",
            example = "10567.34"
    )
    BigDecimal totalEur;
}
