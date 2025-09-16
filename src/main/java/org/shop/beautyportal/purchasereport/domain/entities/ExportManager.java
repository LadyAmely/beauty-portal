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
@Schema(description = "Entity representing an export manager responsible for distributors")
public class ExportManager {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Schema(description = "Unique identifier of the export manager",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Column(name = "full_name")
    @Schema(description = "Full name of the export manager", example = "John Smith")
    private String fullName;

    @Column(name = "email")
    @Schema(description = "Email address of the export manager", example = "john.smith@example.com")
    private String email;

    @Column(name = "created_at")
    @Schema(description = "Timestamp when the export manager record was created",
            example = "2025-09-16T12:30:45")
    private LocalDateTime createdAt;
}
