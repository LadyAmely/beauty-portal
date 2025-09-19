package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "monthly_sku_sales",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_monthly_sku",
                        columnNames = {"distributor_id", "year_no", "month_no", "sku_code", "channel"}
                )
        },
        indexes = {
                @Index(name = "idx_mss_ym", columnList = "distributor_id, year_no, month_no")
        }
)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySkuSales {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "distributor_id", nullable = false)
    private Distributor distributor;

    @Column(name = "year_no", nullable = false)
    private Integer year;

    @Column(name = "month_no", nullable = false)
    private Integer month;

    @Column(name = "sku_code", length = 64, nullable = false)
    private String skuCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", length = 32, nullable = false)
    private SalesChannel channel;

    @Column(name = "quantity", precision = 18, scale = 3, nullable = false)
    private BigDecimal quantity;

    @Column(name = "net_value_input_ccy", precision = 18, scale = 2, nullable = false)
    private BigDecimal netValueInputCcy;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    @Column(name = "net_value_eur", precision = 18, scale = 2)
    private BigDecimal netValueEur;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
