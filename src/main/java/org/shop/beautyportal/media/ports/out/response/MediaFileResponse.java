package org.shop.beautyportal.media.ports.out.response;

import org.shop.beautyportal.media.domain.entities.MediaFolderKind;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record MediaFileResponse(
        UUID id,
        MediaFolderKind folderKind,
        String subfolderKey,
        String filename,
        String extension,
        String contentType,
        Long sizeBytes,
        String checksumSha256,
        String storagePath,
        OffsetDateTime createdAt,

        String productSku,
        UUID campaignId,
        String yearMonth,

        String primarySku,
        List<String> allSkus
) {}

