package org.shop.beautyportal.purchasereport.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(
        name = "ExportManager",
        description = "Entity representing a manager responsible for export of purchase reports"
)
@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportManager {

    @Schema(
            description = "Unique manager identifier (UUID)",
            example = "9f3c2d9e-7b7c-4f65-93a4-19b2f0415e4a",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(
            description = "Full name of the manager",
            example = "John Doe"
    )
    @Column(length = 255)
    private String fullName;

    @Schema(
            description = "Email address of the manager",
            example = "john.doe@example.com"
    )
    @Column
    private String email;

    @Schema(
            description = "Timestamp when the record was created",
            example = "2025-09-19T12:34:56"
    )
    @Column
    private LocalDateTime createdAt;
}
