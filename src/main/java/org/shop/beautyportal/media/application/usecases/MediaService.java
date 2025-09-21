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
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaFileRepository mediaFileRepository;
    private final DownloadUrlProvider downloadUrlProvider;

    /**  Saves multiple uploaded media files to the database. **/
    @Transactional
    public void uploadFiles(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            MediaFile mediaFile = new MediaFile();
            mediaFile.setFilename(file.getOriginalFilename());
            mediaFile.setSizeBytes(file.getSize());
            mediaFile.setContentType(file.getContentType());
            mediaFile.setCreatedAt(OffsetDateTime.now());
            mediaFileRepository.save(mediaFile);
        }
    }

    /** Saves a single uploaded media file to the database. **/
    @Transactional
    public void uploadFile(MultipartFile file) {
        uploadFiles(List.of(file));
    }

    /**  Returns media files sorted by file extension in ascending order. **/
    public MediaSearchResponse sortByTypeFile(){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        List<MediaFile> sortedByType = mediaFiles.stream()
                .sorted(Comparator.comparing(MediaFile::getExtension))
                .collect(Collectors.toList());
        return toResponse(sortedByType);
    }

    /** Returns media files sorted by file size in ascending order. **/
    public MediaSearchResponse sortBySizeAsc(){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        List<MediaFile> sortedBySizeDesc = mediaFiles.stream()
                .sorted(Comparator.comparing(MediaFile::getSizeBytes))
                .collect(Collectors.toList());
        return toResponse(sortedBySizeDesc);
    }

    /** Returns media files sorted by file size in descending order. **/
    public MediaSearchResponse sortBySizeDesc(){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        List<MediaFile> sortedBySizeDesc = mediaFiles.stream()
                .sorted(Comparator.comparing(MediaFile::getSizeBytes).reversed())
                .collect(Collectors.toList());
        return toResponse(sortedBySizeDesc);
    }

    /** Returns media files sorted by creation date in ascending order. **/
    public MediaSearchResponse sortByCreationDateAcs(){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        List<MediaFile> sortedByDate = mediaFiles.stream()
                .sorted(Comparator.comparing(MediaFile::getCreatedAt))
                .collect(Collectors.toList());
        return toResponse(sortedByDate);
    }

    /** Returns media files sorted by creation date in descending order. **/
    public MediaSearchResponse sortByCreationDateDesc(){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        List<MediaFile> sortedByDate = mediaFiles.stream()
                .sorted(Comparator.comparing(MediaFile::getCreatedAt).reversed())
                .collect(Collectors.toList());
        return toResponse(sortedByDate);
    }

    /** Returns media files associated with a specific product SKU. **/
    public MediaSearchResponse search(String sku){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        List<MediaFile> filteredFiles = mediaFiles.stream()
                .filter(file -> sku.equals(file.getProduct().getSku()))
                .collect(Collectors.toList());
        return toResponse(filteredFiles);
    }

    /** Returns all stored media files. **/
    public MediaSearchResponse getMediaAllFiles(){
        List<MediaFile> mediaFiles = mediaFileRepository.findAll();
        return toResponse(mediaFiles);
    }

    /** Returns a single media file by its unique ID. **/
    public MediaFileItem getMediaFile(UUID id){
        var mediaFile = mediaFileRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Media file not found."));
        return toItem(mediaFile);
    }

    /** Converts a MediaFile entity to a MediaFileItem DTO. **/
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

    /** Converts a list of MediaFile entities to a MediaSearchResponse DTO. **/
    private MediaSearchResponse toResponse(List<MediaFile> files) {
        var items = files.stream().map(m -> toItem(m)).toList();
        return new MediaSearchResponse(items.size(), 0, items.size(), items);
    }
}
