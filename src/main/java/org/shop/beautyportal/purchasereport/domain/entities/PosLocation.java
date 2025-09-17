package org.shop.beautyportal.purchasereport.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing a point-of-sale (POS) location assigned to a distributor")
public class PosLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Schema(description = "Unique identifier of the POS location",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "distributor")
    @Schema(description = "Reference to the distributor that owns the POS location")
    private DistributorPurchase distributor;

    @Column(name = "name")
    @Schema(description = "Name of the POS location", example = "Warsaw Beauty Store")
    private String name;

    @Column(name = "opened_at")
    @Schema(description = "Date and time when the POS location was opened",
            example = "2022-05-10T09:00:00")
    private LocalDateTime openedAt;

    @Column(name = "closed_at")
    @Schema(description = "Date and time when the POS location was closed (if applicable)",
            example = "2024-12-31T18:00:00")
    private LocalDateTime closedAt;

    @Column(name = "created_at")
    @Schema(description = "Timestamp when the POS location record was created",
            example = "2025-09-16T12:30:45")
    private LocalDateTime createdAt;
}
