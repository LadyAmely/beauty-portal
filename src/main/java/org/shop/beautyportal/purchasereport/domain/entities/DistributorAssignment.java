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
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing the assignment of a distributor to an export manager")
public class DistributorAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Schema(description = "Unique identifier of the distributor assignment",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "distributor")
    @Schema(description = "Reference to the distributor assigned to the export manager")
    private DistributorPurchase distributor;

    @ManyToOne
    @JoinColumn(name = "export_manager")
    @Schema(description = "Reference to the export manager responsible for the distributor")
    private ExportManager exportManager;

    @Column(name = "assigned_date")
    @Schema(description = "Date and time when the distributor was assigned to the export manager",
            example = "2025-09-16T12:30:45")
    private LocalDateTime assignedDate;

    @Column(name = "assigned_to")
    @Schema(description = "Date and time until the distributor is assigned to the export manager",
            example = "2026-01-01T00:00:00")
    private LocalDateTime assignedTo;
}
