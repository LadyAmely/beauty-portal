package org.shop.beautyportal.saleschannels.ports.output.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Schema(name = "InventorySnapshotResponse")
public class InventorySnapshotResponse {
    private UUID id;
    private UUID distributorId;
    private LocalDateTime snapshotDate;
    private int reportedLines;
}

