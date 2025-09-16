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
@Schema(description = "Entity representing a distributor in the purchase reporting system")
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Schema(description = "Unique identifier of the distributor", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Column(name = "name")
    @Schema(description = "Name of the distributor", example = "Beauty International Ltd.")
    private String name;

    @Column(name = "country_code", length = 64, unique = true)
    @Schema(description = "Unique country code where the distributor operates", example = "PL")
    private String countryCode;

    @Column(name = "active")
    @Schema(description = "Indicates whether the distributor is active", example = "true")
    private Boolean active;

    @Column(name = "created_at")
    @Schema(description = "Timestamp when the distributor record was created", example = "2025-09-16T12:30:45")
    private LocalDateTime createdAt;
}
