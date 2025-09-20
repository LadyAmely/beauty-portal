package org.shop.beautyportal.media.domain.repositories;

import org.shop.beautyportal.media.domain.entities.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaFileRepository extends JpaRepository<MediaFile, UUID> {
}
