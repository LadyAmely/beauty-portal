package org.shop.beautyportal.purchasereport.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Schema(name = "PosOpeningsTarget",
        description = "Annual POS openings target for a distributor")
@Entity
@Table(name = "pos_openings_targets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PosOpeningsTarget {

    @Schema(description = "Composite identifier (year + distributor)",
            accessMode = Schema.AccessMode.READ_ONLY)
    @EmbeddedId
    private PosOpeningsTargetKey id;

    @Schema(description = "Target number of new POS openings", example = "25")
    @Column(name = "new_openings_target", nullable = false)
    private Integer newOpeningsTarget;
}
