package org.shop.beautyportal.saleschannels.ports.output.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
@Schema(name = "MonthlySkuSalesResponse")
public class MonthlySkuSalesResponse {
    private UUID distributorId;
    private Integer year;
    private Integer month;
    private int reportedLines;
}

