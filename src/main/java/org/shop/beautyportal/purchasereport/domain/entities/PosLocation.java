package org.shop.beautyportal.purchasereport.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.shop.beautyportal.saleschannels.domain.entities.Distributor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(
        name = "PosLocation",
        description = "Entity representing a POS (point-of-sale) location belonging to a distributor"
)
@Entity
@Table(name = "pos_locations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PosLocation {

    @Schema(
            description = "Unique identifier of the POS location (auto-increment)",
            example = "1001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(
            description = "Distributor associated with this POS location",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @ManyToOne
    @JoinColumn(name = "distributor_id", nullable = false)
    private Distributor distributor;

    @Schema(
            description = "Name of the POS location",
            example = "Warsaw Central Shop"
    )
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(
            description = "Date when the POS location was opened",
            example = "2020-05-15"
    )
    @Column(name = "opened_at")
    private LocalDate openedAt;

    @Schema(
            description = "Date when the POS location was closed (if applicable)",
            example = "2023-12-31"
    )
    @Column(name = "closed_at")
    private LocalDate closedAt;

    @Schema(
            description = "Timestamp when the record was created",
            example = "2025-09-20T10:45:30Z",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    private void prePersist() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
    }
}
