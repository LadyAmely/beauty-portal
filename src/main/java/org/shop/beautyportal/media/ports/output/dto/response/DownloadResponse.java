package org.shop.beautyportal.media.ports.output.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "DownloadResponse", description = "Direct download link and basic metadata for a media file.")
public record DownloadResponse(

        @Schema(description = "Media file ID (UUID)", example = "b7c0b2e5-4a86-4b53-b6f2-0a7c2e9d5d33")
        UUID id,

        @Schema(description = "Filename (with extension)", example = "SKU123_main_1.jpg")
        String filename,

        @Schema(description = "MIME type", example = "image/jpeg")
        String contentType,

        @Schema(description = "File size in bytes", example = "482133")
        Long sizeBytes,

        @Schema(description = "Direct/signed download URL", example = "https://cdn.example.com/media/b7c0b2e5-....jpg")
        String url,

        @Schema(description = "URL expiration time (UTC)", example = "2025-09-21T12:00:00Z")
        OffsetDateTime expiresAt
) {}
