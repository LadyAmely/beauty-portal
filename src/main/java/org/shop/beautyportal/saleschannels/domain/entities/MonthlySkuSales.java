package org.shop.beautyportal.saleschannels.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySkuSales {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID Id;

    @ManyToOne
    @Column(name = "distributor_id")
    private Distributor distributor;

    @Column(name="year")
    private Integer year;

    @Column(name="month")
    private Integer month;

    @Column(name = "sku_code", length = 64)
    private String skuCode;

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private SalesChannel channel;

    @Column(precision = 18, scale = 3)
    private BigDecimal quantity;

    @Column(name = "net_value_input_ccy", precision = 18, scale = 2)
    private BigDecimal netValueInputCcy;

    @Column(name = "currency", length = 3)
    private String currency;

    @Column(name = "net_value_eur", precision = 18, scale = 2)
    private BigDecimal netValueEur;

    @Column(name = "source_import_id", length = 128)
    private String sourceImportId;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
