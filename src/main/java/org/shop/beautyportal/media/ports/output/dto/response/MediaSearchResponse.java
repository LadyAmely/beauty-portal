package org.shop.beautyportal.media.ports.output.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.shop.beautyportal.media.ports.input.dto.request.MediaFileItem;

import java.util.List;

@Schema(name = "MediaSearchResponse")
@Builder
public record MediaSearchResponse(

        @Schema(description = "Total results count", example = "123")
        long total,

        @Schema(description = "Current page (0-based)", example = "0")
        int page,

        @Schema(description = "Page size", example = "20")
        int size,

        @Schema(description = "Returned items")
        List<MediaFileItem> items
) {}
