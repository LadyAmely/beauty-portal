package org.shop.beautyportal.saleschannels.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateMonthly {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID Id;

    @Column(name="year")
    private Integer year;

    @Column(name="month")
    private Integer month;

    @Column(name="currency")
    private String currency;

    @Column(name = "avg_to_eur", nullable = false, precision = 18, scale = 6)
    private BigDecimal avgToEur;
}
