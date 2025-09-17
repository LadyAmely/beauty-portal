package org.shop.beautyportal.media.ports.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SkuProductCreateRequest(
        @NotBlank
        @Size(max = 64)
        @Pattern(regexp = "^SKU[A-Z0-9]+$", message = "SKU must start with 'SKU' and be alphanumeric uppercase")
        String sku
) {}

