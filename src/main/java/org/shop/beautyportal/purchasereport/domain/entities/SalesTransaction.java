package org.shop.beautyportal.purchasereport.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "sales_transactions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing a sales transaction")
public class SalesTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Schema(description = "Unique identifier of the transaction", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="distributor")
    @Schema(description = "Distributor associated with the transaction")
    private Distributor distributor;

    @ManyToOne
    @JoinColumn(name="pos_location")
    @Schema(description = "Point of Sale (POS) location where the transaction occurred")
    private PosLocation posLocation;

    @Column(name = "occurred_at")
    @Schema(description = "Date and time when the transaction occurred", example = "2025-09-16T14:30:00")
    private LocalDateTime occurredAt;

    @Column(name = "net_amount", precision = 18, scale = 2)
    @Schema(description = "Net amount of the transaction", example = "199.99")
    private BigDecimal netAmount;

    @Column(name = "currency_code")
    @Schema(description = "Currency code in ISO 4217 format", example = "USD")
    private String currencyCode;

    @Column(name = "created_at")
    @Schema(description = "Date and time when the record was created", example = "2025-09-16T14:35:00Z")
    private OffsetDateTime createdAt;
}
