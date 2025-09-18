package org.shop.beautyportal.media.domain.repositories;

import org.shop.beautyportal.media.domain.entities.MediaFile;
import org.shop.beautyportal.media.domain.entities.MediaFolderKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MediaFileRepository extends JpaRepository<MediaFile, UUID>, JpaSpecificationExecutor<MediaFile> {
    Optional<MediaFile> findByStoragePath(String storagePath);

    List<MediaFile> findAllByIdIn(Collection<UUID> ids);

    Page<MediaFile> findAllByFolderKindAndSubfolderKey(
            MediaFolderKind folderKind, String subfolderKey, Pageable pageable);
}
