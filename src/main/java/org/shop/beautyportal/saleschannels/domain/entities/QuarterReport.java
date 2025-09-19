package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "quarter_report")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class QuarterReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @Column(name = "year_no", nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer quarter;

    @Column(name = "input_currency", length = 3)
    private String inputCurrency;

    @Column(name = "new_clients")
    private Integer newClients;

    @Column(name = "total_input_ccy", precision = 18, scale = 2)
    private BigDecimal totalInputCcy;

    @Column(name = "total_eur", precision = 18, scale = 2)
    private BigDecimal totalEur;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @Version
    private Integer version;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<QuarterChannelAmount> lines = new HashSet<>();
}
