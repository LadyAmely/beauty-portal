package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Schema(
        name = "DistributorIdentityMap",
        description = "Maps a distributor to an external identity provider subject (Cognito, Keycloak, etc.)"
)
@Entity
@Table(name = "distributor_identity_map")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistributorIdentityMap {

    @Schema(
            description = "Internal identifier (UUID)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(
            description = "Associated distributor entity",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "distributor_id", nullable = false)
    private Distributor distributor;

    @Schema(
            description = "Identity provider name (e.g. AWS_COGNITO, KEYCLOAK)",
            example = "AWS_COGNITO",
            maxLength = 64
    )
    @Column(length = 64, nullable = false)
    private String idpProvider;

    @Schema(
            description = "Subject identifier from identity provider",
            example = "eu-central-1:12345678-abcd-efgh-ijkl-9876543210",
            maxLength = 255
    )
    @Column(length = 255, nullable = false)
    private String idpSubject;

    @Schema(
            description = "Role assigned to this mapping",
            example = "ADMIN",
            maxLength = 64
    )
    @Column(length = 64, nullable = false)
    private String role;
}

