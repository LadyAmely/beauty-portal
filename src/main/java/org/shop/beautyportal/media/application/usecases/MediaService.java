package org.shop.beautyportal.media.application.usecases;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.media.domain.entities.MediaFile;
import org.shop.beautyportal.media.domain.repositories.MediaFileRepository;
import org.shop.beautyportal.media.ports.input.dto.request.MediaFileItem;
import org.shop.beautyportal.media.ports.output.dto.response.MediaSearchResponse;
import org.shop.beautyportal.media.domain.ports.DownloadUrlProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaFileRepository mediaFileRepository;
    private final DownloadUrlProvider downloadUrlProvider;

    @Transactional
    public MediaSearchResponse search(String sku){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        List<MediaFile> filteredFiles = mediaFiles.stream()
                .filter(file -> sku.equals(file.getProduct().getSku()))
                .collect(Collectors.toList());
        return toResponse(filteredFiles);
    }

    @Transactional
    public MediaSearchResponse getMediaAllFiles(){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        return toResponse(mediaFiles);
    }

    @Transactional
    public MediaFileItem getMediaFile(UUID id){
        var mediaFile = mediaFileRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Media file not found."));
        return toItem(mediaFile);
    }

    private MediaFileItem toItem(MediaFile m) {
        return new MediaFileItem(
                m.getId(),
                m.getSubfolderKey(),
                m.getFilename(),
                m.getExtension(),
                m.getContentType(),
                m.getSizeBytes(),
                m.getChecksumSha256(),
                m.getProduct()  != null ? m.getProduct().getSku()  : null,
                m.getCampaign() != null ? m.getCampaign().getId() : null,
                m.getCreatedAt(),
                downloadUrlProvider.getDownloadUrl()
        );
    }
    private MediaSearchResponse toResponse(List<MediaFile> files) {
        var items = files.stream().map(m -> toItem(m)).toList();
        return new MediaSearchResponse(items.size(), 0, items.size(), items);
    }
}
