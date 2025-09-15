package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(
        name = "ExchangeRateMonthly",
        description = "Monthly average FX rate to EUR from NBP or another provider"
)
@Entity
@Table(name = "exchange_rate_monthly")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateMonthly {

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
            description = "Calendar year of the rate",
            example = "2025"
    )
    @Column(name = "year", nullable = false)
    private Integer year;

    @Schema(
            description = "Calendar month of the rate (1-12)",
            example = "7",
            minimum = "1",
            maximum = "12"
    )
    @Column(name = "month", nullable = false)
    private Integer month;

    @Schema(
            description = "Currency code in ISO 4217 (e.g., PLN, USD)",
            example = "PLN",
            maxLength = 3
    )
    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    @Schema(
            description = "Average exchange rate to EUR",
            example = "0.232156",
            format = "decimal",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "avg_to_eur", nullable = false, precision = 18, scale = 6)
    private BigDecimal avgToEur;
}
