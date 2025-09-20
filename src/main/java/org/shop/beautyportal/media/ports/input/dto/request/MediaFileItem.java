package org.shop.beautyportal.media.ports.input.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "MediaFileItem")
public record MediaFileItem(

        @Schema(description = "Media file ID (UUID)",
                example = "b7c0b2e5-4a86-4b53-b6f2-0a7c2e9d5d33")
        UUID id,

        @Schema(description = "Subfolder key (e.g., SKU or YYYY-MM)", example = "SKU123")
        String subfolderKey,

        @Schema(description = "Filename without path", example = "SKU123_main_1")
        String filename,

        @Schema(description = "File extension (no dot)", example = "jpg")
        String extension,

        @Schema(description = "MIME type", example = "image/jpeg")
        String contentType,

        @Schema(description = "Size in bytes", example = "482133")
        Long sizeBytes,

        @Schema(description = "SHA-256 checksum (hex)", example = "e3b0c44298fc1c149a...")
        String checksumSha256,

        @Schema(description = "Product SKU (if applicable)", example = "SKU123")
        String productSku,

        @Schema(description = "Campaign ID (if applicable)",
                example = "8c2b8c6b-1f53-4e9a-a9b5-3a0d1e2a9f11")
        UUID campaignId,

        @Schema(description = "Created at (UTC)", example = "2025-03-15T09:10:00Z")
        OffsetDateTime createdAt,

        @Schema(description = "Direct/signed download URL (if provided by API)",
                example = "https://cdn.example.com/media/b7c0b2e5-....jpg")
        String downloadUrl
) {}

