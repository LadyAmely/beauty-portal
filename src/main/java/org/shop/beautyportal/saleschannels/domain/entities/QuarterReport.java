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

@Schema(
        name = "QuarterReport",
        description = "Quarterly report header for a distributor"
)
@Entity
@Table(name = "quarter_report")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class QuarterReport {

    @Schema(
            description = "Report identifier (UUID)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(
            description = "Associated distributor (owner of the report)",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @Schema(
            description = "Calendar year of the report",
            example = "2025",
            minimum = "2000",
            maximum = "2100"
    )
    @Column(name = "year_no", nullable = false)
    private Integer year;

    @Schema(
            description = "Quarter number (1..4)",
            example = "3",
            minimum = "1",
            maximum = "4"
    )
    @Column(nullable = false)
    private Integer quarter;

    @Schema(
            description = "Input currency (ISO 4217)",
            example = "PLN",
            maxLength = 3
            // można dodać pattern = "^[A-Z]{3}$" jeśli chcesz wymuszać format
    )
    @Column(name = "input_currency", length = 3)
    private String inputCurrency;

    @Schema(
            description = "Number of newly acquired clients in this quarter",
            example = "5",
            minimum = "0"
    )
    @Column(name = "new_clients")
    private Integer newClients;

    @Schema(
            description = "Sum of all channel amounts in input currency (materialized)",
            example = "15432.78",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "total_input_ccy", precision = 18, scale = 2)
    private BigDecimal totalInputCcy;

    @Schema(
            description = "Sum of all channel amounts converted to EUR (materialized)",
            example = "3510.44",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "total_eur", precision = 18, scale = 2)
    private BigDecimal totalEur;

    @Schema(
            description = "Creation timestamp (UTC)",
            example = "2025-09-15T14:30:00Z",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Schema(
            description = "Last update timestamp (UTC)",
            example = "2025-09-15T15:45:00Z",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @Schema(
            description = "Optimistic lock version",
            accessMode = Schema.AccessMode.READ_ONLY,
            example = "1"
    )
    @Version
    private Integer version;

    @Schema(
            description = "Quarter lines broken down by month and channel",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<QuarterChannelAmount> lines = new HashSet<>();
}
