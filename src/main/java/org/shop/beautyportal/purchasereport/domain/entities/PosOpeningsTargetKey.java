package org.shop.beautyportal.purchasereport.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@Schema(name = "PosOpeningsTargetKey", description = "Composite key (year + distributor)")
@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode
public class PosOpeningsTargetKey implements Serializable {

    @Schema(description = "Target year", example = "2025")
    @Column(name = "year_no", nullable = false)
    private Integer year;

    @Schema(description = "Distributor ID (UUID)",
            example = "9f3c2d9e-7b7c-4f65-93a4-19b2f0415e4a")
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "distributor_id", nullable = false)
    private UUID distributorId;
}
