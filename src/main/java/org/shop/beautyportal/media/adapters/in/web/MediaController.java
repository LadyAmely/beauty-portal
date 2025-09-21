package org.shop.beautyportal.media.adapters.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.media.application.usecases.MediaService;
import org.shop.beautyportal.media.ports.input.dto.request.MediaFileItem;
import org.shop.beautyportal.media.ports.output.dto.response.MediaSearchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
@Tag(name = "media")
public class MediaController {

    private final MediaService mediaService;

    /**
     * Uploads a single media file.
     */
    @Operation(summary = "Upload a single media file")
    @PostMapping("/upload-file")
    public ResponseEntity<MediaFileItem> uploadMediaFile(
            @Parameter(description = "File to upload") @RequestParam("file") MultipartFile file) {
        mediaService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Uploads multiple media files.
     */
    @Operation(summary = "Upload multiple media files")
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadMediaFiles(
            @Parameter(description = "List of files to upload") @RequestParam("files") List<MultipartFile> files) {
        mediaService.uploadFiles(files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Returns media files sorted by file type.
     */
    @Operation(summary = "Get media files sorted by file type")
    @GetMapping("/sorted-by-type")
    public ResponseEntity<MediaSearchResponse> getSortedByType() {
        MediaSearchResponse mediaSearchResponse = mediaService.sortByTypeFile();
        return new ResponseEntity<>(mediaSearchResponse, HttpStatus.OK);
    }

    /**
     * Returns media files sorted by size ascending.
     */
    @Operation(summary = "Get media files sorted by size ascending")
    @GetMapping("/sorted-by-size-asc")
    public ResponseEntity<MediaSearchResponse> getSortedBySizeAsc() {
        MediaSearchResponse mediaSearchResponse = mediaService.sortBySizeAsc();
        return new ResponseEntity<>(mediaSearchResponse, HttpStatus.OK);
    }

    /**
     * Returns media files sorted by size descending.
     */
    @Operation(summary = "Get media files sorted by size descending")
    @GetMapping("/sorted-by-size-desc")
    public ResponseEntity<MediaSearchResponse> getSortedBySizeDesc() {
        MediaSearchResponse mediaSearchResponse = mediaService.sortBySizeDesc();
        return new ResponseEntity<>(mediaSearchResponse, HttpStatus.OK);
    }

    /**
     * Returns media files sorted by creation date ascending.
     */
    @Operation(summary = "Get media files sorted by creation date ascending")
    @GetMapping("/sorted-by-date-asc")
    public ResponseEntity<MediaSearchResponse> getSortedFilesByCreationDateAsc() {
        MediaSearchResponse mediaSearchResponse = mediaService.sortByCreationDateAcs();
        return new ResponseEntity<>(mediaSearchResponse, HttpStatus.OK);
    }

    /**
     * Returns media files sorted by creation date descending.
     */
    @Operation(summary = "Get media files sorted by creation date descending")
    @GetMapping("/sorted-by-date-desc")
    public ResponseEntity<MediaSearchResponse> getSortedFilesByCreationDateDesc() {
        MediaSearchResponse mediaSearchResponse = mediaService.sortByCreationDateDesc();
        return new ResponseEntity<>(mediaSearchResponse, HttpStatus.OK);
    }

    /**
     * Returns media files associated with a specific product SKU.
     */
    @Operation(summary = "Search media files by product SKU")
    @GetMapping("/search")
    public ResponseEntity<MediaSearchResponse> searchMediaFile(
            @Parameter(description = "Product SKU") @RequestParam String sku) {
        MediaSearchResponse mediaSearchResponse = mediaService.search(sku);
        return new ResponseEntity<>(mediaSearchResponse, HttpStatus.OK);
    }

    /**
     * Returns a media file by its ID.
     */
    @Operation(summary = "Get a media file by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MediaFileItem> getMediaFile(
            @Parameter(description = "Media file ID") @PathVariable UUID id) {
        MediaFileItem mediaFile = mediaService.getMediaFile(id);
        return new ResponseEntity<>(mediaFile, HttpStatus.OK);
    }

    /**
     * Returns all stored media files.
     */
    @Operation(summary = "Get all media files")
    @GetMapping
    public ResponseEntity<MediaSearchResponse> getAllMediaFiles() {
        MediaSearchResponse mediaSearchResponse = mediaService.getMediaAllFiles();
        return new ResponseEntity<>(mediaSearchResponse, HttpStatus.OK);
    }
}
