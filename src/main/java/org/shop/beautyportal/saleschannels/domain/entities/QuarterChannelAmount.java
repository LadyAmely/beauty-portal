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
        description = "Sales amount for a specific month and sales channel within a quarterly report"
)
@Entity
@Table(
        name = "quarter_channel_amount",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_report_month_channel",
                columnNames = {"report_id", "year_no", "month_no", "channel"}
        ),
        indexes = {
                @Index(name = "idx_qca_report", columnList = "report_id"),
                @Index(name = "idx_qca_ym",     columnList = "year_no, month_no")
        }
)
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
            description = "Owning quarterly report header",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_id", nullable = false)
    private QuarterReport report;

    @Schema(
            description = "Calendar year",
            example = "2025",
            minimum = "2000",
            maximum = "2100"
    )
    @Column(name = "year_no",  nullable = false)
    private Integer year;

    @Schema(
            description = "Month number (1–12)",
            example = "9",
            minimum = "1",
            maximum = "12"
    )
    @Column(name = "month_no", nullable = false)
    private Integer month;

    @Schema(
            description = "Sales channel (enum)",
            example = "PHARMACY"
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "channel", length = 32, nullable = false)
    private SalesChannel channel;

    @Schema(
            description = "Net sales amount in input currency",
            example = "4321.00",
            minimum = "0.00",
            format = "decimal"
    )
    @Column(name = "amount_input_ccy", precision = 18, scale = 2, nullable = false)
    private BigDecimal amountInputCcy;

    @Schema(
            description = "Net sales amount converted to EUR (calculated)",
            example = "987.65",
            accessMode = Schema.AccessMode.READ_ONLY,
            format = "decimal"
    )
    @Column(name = "amount_eur", precision = 18, scale = 2)
    private BigDecimal amountEur;
}
