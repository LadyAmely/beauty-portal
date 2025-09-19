package org.shop.beautyportal.purchasereport.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.shop.beautyportal.saleschannels.domain.entities.Distributor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(
        name = "PurchaseReport",
        description = "Entity representing a quarterly purchase report for a distributor"
)
@Entity
@Table(name = "purchase_reports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReport {

    @Schema(
            description = "Unique identifier of the purchase report (UUID)",
            example = "b4a8f2a2-d7a0-4c2b-bc7a-5de9d9f54e2a",
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
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @Schema(
            description = "Year of the purchase report",
            example = "2025"
    )
    @Column(name = "year_no")
    private Integer year;

    @Schema(
            description = "Quarter of the purchase report (1–4)",
            example = "2"
    )
    @Column(name = "quarter")
    private Short quarter;

    @Schema(
            description = "Sales value from the last year",
            example = "150000.50"
    )
    @Column(name = "last_year_sales", precision = 18, scale = 2)
    private BigDecimal lastYearSales;

    @Schema(
            description = "Total purchases value in this quarter",
            example = "82000.00"
    )
    @Column(name = "purchases", precision = 18, scale = 2)
    private BigDecimal purchases;

    @Schema(
            description = "Planned budget for this quarter",
            example = "100000.00"
    )
    @Column(name = "budget", precision = 18, scale = 2)
    private BigDecimal budget;

    @Schema(
            description = "User ID who created the report",
            example = "123"
    )
    @Column(name = "created_by")
    private Long createdBy;

    @Schema(
            description = "Timestamp when the report was created",
            example = "2025-09-19T12:34:56Z",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @Schema(
            description = "Timestamp when the report was last updated",
            example = "2025-09-20T09:15:30Z",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}
