package org.shop.beautyportal.saleschannels.ports.input.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Schema(name = "InventorySnapshotRequest")
public class InventorySnapshotRequest {
    @NotNull
    private UUID distributorId;

    @NotNull
    private LocalDateTime snapshotDate;

    @NotEmpty
    private List<@Valid Line> lines;

    @Getter
    @Setter
    @Schema(name = "InventorySnapshotLine")
    public static class Line {
        @NotBlank
        private String skuCode;

        @NotNull
        @DecimalMin("0.000")
        private BigDecimal quantity;

        @NotBlank
        private String location;
    }
}

