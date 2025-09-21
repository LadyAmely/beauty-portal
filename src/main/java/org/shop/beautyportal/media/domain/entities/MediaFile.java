package org.shop.beautyportal.media.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "MediaFile", description = "Stored media file with links to campaign and product.")
@Entity
@Table(name = "media_file")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MediaFile {

    @Schema(description = "Media file ID (UUID)", example = "b7c0b2e5-4a86-4b53-b6f2-0a7c2e9d5d33", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING) private MediaFolderKind folderKind;

    @Schema(description = "Optional subfolder discriminator within folder kind", example = "banners/landing")
    @Column(name = "subfolder_key")
    private String subfolderKey;

    @Schema(description = "Original file name without path", example = "hero_banner")
    @Column(name = "filename")
    private String filename;

    @Schema(description = "File extension (lowercase, without dot)", example = "png")
    @Column(name = "extension", length = 20)
    private String extension;

    @Schema(description = "MIME content type", example = "image/png")
    @Column(name = "content_type", length = 100)
    private String contentType;

    @Schema(description = "File size in bytes", example = "482133")
    @Column(name = "size_bytes")
    private Long sizeBytes;

    @Schema(description = "SHA-256 checksum hex", example = "e3b0c44298fc1c149afbf4c8996fb924...")
    @Column(name = "checksum_sha256", length = 128)
    private String checksumSha256;

    @Schema(description = "Remote/local storage path or key", example = "s3://bucket/campaigns/2025-09/hero_banner.png")
    @Column(name = "storage_path")
    private String storagePath;

    @Schema(description = "Creation timestamp (UTC)", example = "2025-09-01T08:00:00Z", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Schema(description = "Linked product (by SKU)", accessMode = Schema.AccessMode.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "product_sku")
    private SkuProduct product;

    @Schema(description = "Linked campaign", accessMode = Schema.AccessMode.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}

