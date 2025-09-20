package org.shop.beautyportal.media.ports.input.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.shop.beautyportal.media.domain.entities.MediaFolderKind;

import java.time.OffsetDateTime;
import java.util.List;

@Schema(name = "MediaSearchRequest")
public record MediaSearchRequest(

        @Schema(description = "List of SKUs to filter (PRODUCTS)", example = "[\"SKU123\",\"SKU222\"]")
        List<String> skus,

        @Schema(description = "Campaign year-month (MARKETING) in YYYY-MM format", example = "2025-03")
        String yearMonth,

        @Schema(description = "Folder kind (PRODUCT or MARKETING)")
        MediaFolderKind folderKind,

        @Schema(description = "Filename contains query", example = "main")
        String q,

        @Schema(description = "Allowed file extensions (no dot)", example = "[\"jpg\",\"pdf\"]")
        List<String> extensions,

        @Schema(description = "Allowed MIME types", example = "[\"image/jpeg\",\"application/pdf\"]")
        List<String> contentTypes,

        @Schema(description = "Created from (UTC)", example = "2025-03-01T00:00:00Z")
        OffsetDateTime createdFrom,

        @Schema(description = "Created to (UTC, exclusive)", example = "2025-04-01T00:00:00Z")
        OffsetDateTime createdTo,

        @Schema(description = "Minimum size in bytes", example = "0")
        Long minSize,

        @Schema(description = "Maximum size in bytes", example = "5242880")
        Long maxSize,

        @Schema(description = "Page number (0-based)", example = "0")
        Integer page,

        @Schema(description = "Page size", example = "20")
        Integer size
) {}
