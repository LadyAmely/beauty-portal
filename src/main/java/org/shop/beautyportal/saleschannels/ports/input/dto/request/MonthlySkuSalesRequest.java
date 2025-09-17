package org.shop.beautyportal.saleschannels.ports.input.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.shop.beautyportal.saleschannels.domain.entities.SalesChannel;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Schema(name = "MonthlySkuSalesRequest")
public class MonthlySkuSalesRequest {

    @NotNull
    private UUID distributorId;

    @NotNull @Min(2000) @Max(2100)
    private Integer year;

    @NotNull @Min(1) @Max(12)
    private Integer month;

    @NotEmpty
    private List<@Valid Line> lines;

    @Getter
    @Setter
    @Schema(name = "MonthlySkuSalesLine")
    public static class Line {
        @NotBlank
        private String skuCode;

        @NotNull
        private SalesChannel channel;

        @NotNull @DecimalMin("0.000")
        private BigDecimal quantity;

        @NotNull @DecimalMin("0.00")
        private BigDecimal netValueInputCcy;

        @NotBlank @Size(min = 3, max = 3)
        private String currency;
    }
}

