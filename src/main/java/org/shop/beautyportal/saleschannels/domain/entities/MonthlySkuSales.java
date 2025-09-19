package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(
        name = "MonthlySkuSales",
        description = "Monthly sales per distributor, SKU and sales channel"
)
@Entity
@Table(
        name = "monthly_sku_sales",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_monthly_sku",
                columnNames = {"distributor_id", "year_no", "month_no", "sku_code", "channel"}
        ),
        indexes = @Index(name = "idx_mss_ym", columnList = "distributor_id, year_no, month_no")
)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySkuSales {

    @Schema(
            description = "Unique identifier (UUID)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(
            description = "Associated distributor",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "distributor_id", nullable = false)
    private Distributor distributor;

    @Schema(
            description = "Calendar year of the sales record",
            example = "2025",
            minimum = "2000",
            maximum = "2100"
    )
    @Column(name = "year_no", nullable = false)
    private Integer year;

    @Schema(
            description = "Calendar month of the sales record (1–12)",
            example = "9",
            minimum = "1",
            maximum = "12"
    )
    @Column(name = "month_no", nullable = false)
    private Integer month;

    @Schema(
            description = "SKU (Stock Keeping Unit) code",
            example = "SKU-123-ABC",
            maxLength = 64
    )
    @Column(name = "sku_code", length = 64, nullable = false)
    private String skuCode;

    @Schema(
            description = "Sales channel (enum)",
            example = "PHARMACY"
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "channel", length = 32, nullable = false)
    private SalesChannel channel;

    @Schema(
            description = "Quantity sold",
            example = "42.000",
            minimum = "0.000",
            format = "decimal"
    )
    @Column(name = "quantity", precision = 18, scale = 3, nullable = false)
    private BigDecimal quantity;

    @Schema(
            description = "Net sales value in input currency",
            example = "1234.56",
            minimum = "0.00",
            format = "decimal"
    )
    @Column(name = "net_value_input_ccy", precision = 18, scale = 2, nullable = false)
    private BigDecimal netValueInputCcy;

    @Schema(
            description = "Currency of the net value (ISO 4217)",
            example = "PLN",
            maxLength = 3
    )
    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    @Schema(
            description = "Net sales value converted to EUR (calculated)",
            example = "2837.45",
            accessMode = Schema.AccessMode.READ_ONLY,
            format = "decimal"
    )
    @Column(name = "net_value_eur", precision = 18, scale = 2)
    private BigDecimal netValueEur;

    @Schema(
            description = "Creation timestamp",
            example = "2025-09-15T14:30:00Z",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
