package org.shop.beautyportal.media.ports.input.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(name = "DownloadRequest")
public record DownloadRequest(
        @Schema(description = "IDs of media files to include in the archive")
        List<UUID> mediaIds,

        @Schema(description = "Optional archive name", example = "marketing_2025_03.zip")
        String archiveName
) {
}
