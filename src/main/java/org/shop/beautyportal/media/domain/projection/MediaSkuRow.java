package org.shop.beautyportal.media.domain.projection;

import org.shop.beautyportal.media.domain.entities.MediaFolderKind;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface MediaSkuRow {
    UUID getMedia_id();
    MediaFolderKind getFolder_kind();
    String getSubfolder_key();
    String getFilename();
    String getExtension();
    String getContent_type();
    Long getSize_bytes();
    String getStorage_path();
    OffsetDateTime getCreated_at();

    String getProduct_sku();
    UUID getCampaign_id();
}

