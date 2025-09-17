package org.shop.beautyportal.saleschannels.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(
        name = "Client",
        description = "Distributor's client entity")
@Entity
@Table
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Schema(
            description = "Unique client identifier (UUID)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(
            description = "Associated distributor",
            accessMode = Schema.AccessMode.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @Schema(
            description = "Client name",
            example = "Health Pharmacy Ltd.")
    @Column(length = 255)
    private String name;

    @Schema(
            description = "Client tax identifier (e.g., VAT number)",
            example = "1234567890")
    @Column(length = 64)
    private String taxId;

    @Schema(
            description = "Creation timestamp",
            example = "2025-09-15T14:30:00Z",
            accessMode = Schema.AccessMode.READ_ONLY)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Sales channel of the client", example = "PHARMACY")
    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false, length = 32)
    private SalesChannel channel;
}
