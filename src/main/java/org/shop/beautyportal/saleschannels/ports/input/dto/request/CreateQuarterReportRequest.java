package org.shop.beautyportal.saleschannels.ports.input.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Schema(
        name = "CreateQuarterReportRequest",
        description = "Request payload for creating a quarterly sales report with monthly channel breakdown"
)
public class CreateQuarterReportRequest {

    @NotNull
    @Schema(
            description = "Distributor identifier (UUID)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private UUID distributorId;

    @NotNull
    @Min(2000)
    @Max(2100)
    @Schema(
            description = "Calendar year of the report",
            example = "2025",
            minimum = "2000",
            maximum = "2100",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer year;

    @NotNull
    @Min(1)
    @Max(4)
    @Schema(
            description = "Quarter number (1-4)",
            example = "2",
            minimum = "1",
            maximum = "4",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer quarter;

    @NotBlank
    @Size(min = 3, max = 3)
    @Schema(
            description = "Input currency in ISO 4217 format",
            example = "PLN",
            maxLength = 3,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String inputCurrency;

    @Min(0)
    @Schema(
            description = "Number of newly acquired clients in this quarter",
            example = "7"
    )
    private Integer newClients;

    @NotEmpty
    @Schema(
            description = "List of monthly sales lines broken down by channel",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<@Valid QuarterLineDTO> lines;

    @Getter
    @Setter
    @Schema(
            name = "QuarterLine",
            description = "Monthly sales line for a specific channel"
    )
    public static class QuarterLineDTO {

        @NotNull
        @Min(1)
        @Max(12)
        @Schema(
                description = "Month number (1-12)",
                example = "4",
                minimum = "1",
                maximum = "12",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        private Integer month;

        @NotBlank
        @Schema(
                description = "Sales channel identifier (enum value)",
                example = "PHARMACY",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        private String channel;

        @NotNull
        @DecimalMin("0.00")
        @Schema(
                description = "Net sales amount in input currency",
                example = "12345.67",
                format = "decimal",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        private BigDecimal amountInputCcy;
    }
}
