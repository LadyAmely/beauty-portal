package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(
        name = "QuarterChannelAmount",
        description = "Sales amount for a specific month and channel"
)
@Entity
@Table(
        name = "quarter_channel_amount",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_report_month_channel",
                        columnNames = {"report_id", "year", "month", "channel"}
                )
        },
        indexes = {
                @Index(name = "idx_qca_report", columnList = "report_id"),
                @Index(name = "idx_qca_ym", columnList = "year, month")
        }
)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuarterChannelAmount {

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
            description = "Quarterly report to which this line belongs",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_id", nullable = false)
    private QuarterReport report;

    @Schema(
            description = "Year of this sales amount",
            example = "2025"
    )
    @Column(name = "year", nullable = false)
    private Integer year;

    @Schema(
            description = "Month number (1-12)",
            example = "9",
            minimum = "1",
            maximum = "12"
    )
    @Column(name = "month", nullable = false)
    private Integer month;

    @Schema(
            description = "Sales channel",
            example = "PHARMACY"
    )
    @Enumerated(EnumType.STRING)
    @Column(length = 32, nullable = false)
    private SalesChannel channel;

    @Schema(
            description = "Sales amount in input currency",
            example = "9999.99"
    )
    @Column(name = "amount_input_ccy", precision = 18, scale = 2, nullable = false)
    private BigDecimal amountInputCcy;

    @Schema(
            description = "Sales amount converted to EUR (calculated)",
            example = "2321.12",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "amount_eur", precision = 18, scale = 2)
    private BigDecimal amountEur;
}

