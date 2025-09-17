package org.shop.beautyportal.media.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "media_file",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_media_storage_path", columnNames = "storage_path")
        },
        indexes = {
                @Index(name = "ix_media_folder", columnList = "folder_kind, subfolder_key"),
                @Index(name = "ix_media_created_at", columnList = "created_at DESC"),
                @Index(name = "ix_media_size", columnList = "size_bytes DESC"),
                @Index(name = "ix_media_extension", columnList = "extension")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "media_id", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "folder_kind", nullable = false)
    private MediaFolderKind folderKind;

    @Column(name = "subfolder_key", nullable = false)
    private String subfolderKey;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "extension", insertable = false, updatable = false)
    private String extension;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size_bytes", nullable = false)
    private Long sizeBytes;

    @Column(name = "checksum_sha256")
    private String checksumSha256;

    @Column(name = "storage_path", nullable = false, unique = true)
    private String storagePath;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_sku", referencedColumnName = "sku")
    private SkuProduct product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private Campaign campaign;
}

