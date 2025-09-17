package org.shop.beautyportal.purchasereport.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase_reports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing a distributor's purchase report for a specific year and quarter")
public class PurchaseReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "purchase_report_id")
    @Schema(description = "Unique identifier of the purchase report", example = "1001")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    @Schema(description = "Distributor associated with the purchase report")
    private DistributorPurchase distributor;

    @Column(name = "year")
    @Schema(description = "Year of the purchase report", example = "2025")
    private Integer year;

    @Column(name = "quarter")
    @Schema(description = "Quarter of the purchase report (1-4)", example = "2")
    private Short quarter;

    @Column(name = "last_year_sales", precision = 18, scale = 2)
    @Schema(description = "Sales amount from the previous year", example = "150000.50")
    private BigDecimal lastYearSales;

    @Column(name = "purchases", precision = 18, scale = 2)
    @Schema(description = "Total purchases for the given quarter", example = "32000.75")
    private BigDecimal purchases;

    @Column(name = "budget", precision = 18, scale = 2)
    @Schema(description = "Budget assigned for the given quarter", example = "50000.00")
    private BigDecimal budget;

    @Column(name = "created_by")
    @Schema(description = "Identifier of the user who created the report", example = "42")
    private Long createdBy;

    @Column(name = "created_at")
    @Schema(description = "Date and time when the report was created", example = "2025-09-16T12:00:00Z")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Date and time when the report was last updated", example = "2025-09-16T14:00:00Z")
    private OffsetDateTime updatedAt;
}
