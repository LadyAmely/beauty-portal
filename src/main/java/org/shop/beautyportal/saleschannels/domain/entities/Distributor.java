package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Schema(
        name = "Distributor",
        description = "Distributor entity – represents an organization selling through defined channels"
)
@Entity
@Table(name = "distributor")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Distributor {

    @Schema(
            description = "Distributor identifier (UUID)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(
            description = "Unique distributor code",
            example = "DIST-PL-001",
            maxLength = 64
    )
    @Column(length = 64, unique = true)
    private String code;

    @Schema(
            description = "Distributor's full name",
            example = "Acme Distribution Sp. z o.o.",
            maxLength = 255
    )
    @Column(name="name", length = 255)
    private String name;
}
