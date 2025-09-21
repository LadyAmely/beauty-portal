package org.shop.beautyportal.media.adapters.out.web;

import org.shop.beautyportal.media.domain.ports.DownloadUrlProvider;
import org.springframework.stereotype.Component;

@Component
public class DownloadUrlProviderImpl implements DownloadUrlProvider {
    @Override
    public String getDownloadUrl() {
        return "https://example.com/download";
    }
}
