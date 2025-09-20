package org.shop.beautyportal.media.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "SkuProduct", description = "Product identified by its SKU.")
@Entity
@Table(name = "sku_product")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SkuProduct {

    @Schema(description = "Product SKU (primary key)", example = "SKU-ABC-001")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Schema(description = "Creation timestamp (UTC)", example = "2025-09-01T08:00:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
