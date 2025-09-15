package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(
        name = "InventorySnapshot",
        description = "Inventory snapshot for a distributor and date, broken down by SKU and location"
)
@Entity
@Table(
        name = "inventory_snapshot",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_inv_snapshot",
                        columnNames = {"distributor_id", "snapshot_date", "sku_code", "location"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventorySnapshot {

    @Schema(
            description = "Inventory snapshot identifier (UUID)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(
            description = "Distributor associated with this inventory snapshot",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "distributor_id", nullable = false)
    private Distributor distributor;

    @Schema(
            description = "Date of snapshot (end of day)",
            example = "2025-09-15T00:00:00"
    )
    @Column(name = "snapshot_date", nullable = false)
    private LocalDateTime snapshotDate;

    @Schema(
            description = "SKU (Stock Keeping Unit) code",
            example = "SKU-123-ABC",
            maxLength = 64
    )
    @Column(name = "sku_code", nullable = false, length = 64)
    private String skuCode;

    @Schema(
            description = "Available quantity of SKU",
            example = "1250.500",
            format = "decimal"
    )
    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal quantity;

    @Schema(
            description = "Warehouse or storage location identifier",
            example = "WH-01-A",
            maxLength = 128
    )
    @Column(length = 128)
    private String location;

    @Schema(
            description = "Creation timestamp of this record",
            example = "2025-09-15T14:30:00Z",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
