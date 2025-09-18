package org.shop.beautyportal.media.application.usecases;

import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.media.ports.out.response.MediaFileResponse;
import org.shop.beautyportal.media.domain.entities.MediaFile;
import org.shop.beautyportal.media.domain.entities.MediaFolderKind;
import org.shop.beautyportal.media.domain.repositories.MediaFileRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MediaSkuSearchService {

    private final MediaFileRepository mediaRepo;

    @Transactional(readOnly = true)
    public Page<MediaFileResponse> searchBySku(String sku, Pageable pageable) {

        List<MediaFile> products  = mediaRepo.findProductsBySku(sku);
        List<MediaFile> marketing = mediaRepo.findMarketingBySkuToken(sku);

        List<MediaFile> merged = Stream.concat(products.stream(), marketing.stream())
                .collect(Collectors.toMap(MediaFile::getId, Function.identity(), (a,b) -> a, LinkedHashMap::new))
                .values().stream()
                .toList();

        Comparator<MediaFile> cmp = comparatorFrom(pageable.getSort());
        if (cmp != null) {
            merged = merged.stream().sorted(cmp).toList();
        } else {
            merged = merged.stream()
                    .sorted(Comparator.comparing(MediaFile::getCreatedAt,
                            Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                    .toList();
        }

        int page = Math.max(pageable.getPageNumber(), 0);
        int size = Math.max(pageable.getPageSize(), 1);
        int from = Math.min(page * size, merged.size());
        int to   = Math.min(from + size, merged.size());

        List<MediaFileResponse> content = merged.subList(from, to).stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(content, PageRequest.of(page, size, pageable.getSort()), merged.size());
    }

    private Comparator<MediaFile> comparatorFrom(Sort sort) {
        if (sort == null || sort.isUnsorted()) return null;

        Comparator<MediaFile> cmp = null;
        for (Sort.Order o : sort) {
            Comparator<MediaFile> c = switch (o.getProperty()) {
                case "createdAt", "created_at" ->
                        Comparator.comparing(MediaFile::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder()));
                case "sizeBytes", "size_bytes" ->
                        Comparator.comparing(MediaFile::getSizeBytes, Comparator.nullsLast(Comparator.naturalOrder()));
                case "extension" ->
                        Comparator.comparing(m -> Optional.ofNullable(m.getExtension()).orElse(""),
                                String.CASE_INSENSITIVE_ORDER);
                case "filename" ->
                        Comparator.comparing(m -> Optional.ofNullable(m.getFilename()).orElse(""),
                                String.CASE_INSENSITIVE_ORDER);
                default -> null;
            };
            if (c == null) continue;
            if (o.isDescending()) c = c.reversed();
            cmp = (cmp == null) ? c : cmp.thenComparing(c);
        }
        return cmp;
    }

    private MediaFileResponse toResponse(MediaFile m) {
        String yearMonth = (m.getFolderKind() == MediaFolderKind.MARKETING) ? m.getSubfolderKey() : null;
        String primarySku = (m.getFolderKind() == MediaFolderKind.PRODUCTS) ? m.getSubfolderKey() : null;
        var allSkus = primarySku != null ? List.of(primarySku) : List.of();

        return new MediaFileResponse(
                m.getId(),
                m.getFolderKind(),
                m.getSubfolderKey(),
                m.getFilename(),
                m.getExtension(),
                m.getContentType(),
                m.getSizeBytes(),
                m.getChecksumSha256(),
                m.getStoragePath(),
                m.getCreatedAt(),
                m.getProduct() != null ? m.getProduct().getSku() : null,
                m.getCampaign() != null ? m.getCampaign().getId() : null,
                yearMonth,
                primarySku,
                (List<String>) allSkus
        );
    }
}

