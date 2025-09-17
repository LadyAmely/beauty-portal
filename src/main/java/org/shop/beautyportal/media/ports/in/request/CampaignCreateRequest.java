package org.shop.beautyportal.media.ports.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CampaignCreateRequest(

        @NotBlank
        @Size(max = 160)
        String name,

        @NotBlank
        @Pattern(regexp = "^\\d{4}_\\d{2}$", message = "yearMonth must match YYYY_MM")
        String yearMonth
) {}

