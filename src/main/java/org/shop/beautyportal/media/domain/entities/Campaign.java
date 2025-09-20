package org.shop.beautyportal.media.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "Campaign", description = "Marketing campaign container for media files.")
@Entity
@Table(name = "campaign")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Campaign {

    @Schema(description = "Campaign ID (UUID)", example = "8c2b8c6b-1f53-4e9a-a9b5-3a0d1e2a9f11", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(description = "Campaign display name", example = "Autumn Skincare 2025")
    @Column(name = "name")
    private String name;

    @Schema(description = "Campaign year-month in YYYY-MM format", example = "2025-09")
    @Column(name = "year_month", length = 7)
    private String yearMonth;

    @Schema(description = "Creation timestamp (UTC)", example = "2025-09-01T08:00:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
