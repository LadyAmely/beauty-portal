package org.shop.beautyportal.purchasereport.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.shop.beautyportal.saleschannels.domain.entities.Distributor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "SalesTransaction", description = "Single POS sales transaction")
@Entity
@Table(name = "sales_transactions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SalesTransaction {

    @Schema(description = "Auto-increment identifier", example = "102345", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(description = "Distributor", accessMode = Schema.AccessMode.WRITE_ONLY)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_id", nullable = false)
    private Distributor distributor;

    @Schema(description = "POS identifier", example = "50021")
    @Column(name = "pos_id", nullable = false)
    private Long posId;

    @Schema(description = "Transaction date", example = "2025-03-31")
    @Column(name = "occurred_at", nullable = false)
    private LocalDate occurredAt;

    @Schema(description = "Calendar year of the transaction (used to join targets)", example = "2025")
    @Column(name = "year_no", nullable = false)
    private Integer year;

    @Schema(description = "Net amount", example = "149.99")
    @Column(name = "net_amount", precision = 18, scale = 2, nullable = false)
    private BigDecimal netAmount;

    @Schema(description = "Currency (ISO 4217)", example = "PLN")
    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Schema(description = "Creation timestamp", example = "2025-03-31T10:15:30Z", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private OffsetDateTime createdAt;

    @Schema(description = "Annual new POS openings target for the distributor/year", accessMode = Schema.AccessMode.READ_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "distributor_id", referencedColumnName = "distributor_id",
                    insertable = false, updatable = false, nullable = false),
            @JoinColumn(name = "year_no", referencedColumnName = "year_no",
                    insertable = false, updatable = false, nullable = false)
    })
    private PosOpeningsTarget openingsTarget;
}
