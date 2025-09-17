package org.shop.beautyportal.media.ports.in.request;

import jakarta.validation.constraints.*;
import org.shop.beautyportal.media.domain.entities.MediaFolderKind;

public record MediaFileCreateRequest(

        @NotNull
        MediaFolderKind folderKind,

        @NotBlank @Size(max = 128)
        String subfolderKey,

        @NotBlank @Size(max = 255)
        String filename,

        @Size(max = 120)
        String contentType,

        @NotNull @PositiveOrZero
        Long sizeBytes,

        @Size(max = 128)
        String checksumSha256,

        @NotBlank @Size(max = 1024)
        String storagePath,

        @Pattern(regexp = "^SKU[A-Z0-9]+$", message = "productSku must match ^SKU[A-Z0-9]+$")
        String productSku,

        java.util.UUID campaignId,
        @Pattern(regexp = "^\\d{4}_\\d{2}$", message = "campaignYearMonth must match YYYY_MM")
        String campaignYearMonth
) {}

