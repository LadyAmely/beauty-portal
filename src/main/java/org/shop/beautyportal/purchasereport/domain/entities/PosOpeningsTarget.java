package org.shop.beautyportal.purchasereport.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Entity
@Table(name = "pos_openings_target")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing yearly POS openings target for a distributor")
public class PosOpeningsTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Schema(
            description = "Unique identifier of the POS openings target",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "distributor")
    @Schema(description = "Distributor associated with the POS openings target")
    private DistributorPurchase distributor;

    @Column(name="year")
    @Schema(description = "Year for which the target is defined", example = "2025")
    private Integer year;

    @Column(name="new_openings_target")
    @Schema(description = "Target number of new POS openings for the given year", example = "50")
    private Integer newOpeningsTarget;
}
