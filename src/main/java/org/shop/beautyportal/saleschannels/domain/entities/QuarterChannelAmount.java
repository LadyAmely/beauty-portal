package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_id", nullable = false)
    private QuarterReport report;

    @Column(name = "year_no",  nullable = false)
    private Integer year;

    @Column(name = "month_no", nullable = false)
    private Integer month;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", length = 32, nullable = false)
    private SalesChannel channel;

    @Column(name = "amount_input_ccy", precision = 18, scale = 2, nullable = false)
    private BigDecimal amountInputCcy;

    @Column(name = "amount_eur", precision = 18, scale = 2)
    private BigDecimal amountEur;
}
