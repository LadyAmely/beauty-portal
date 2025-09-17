package org.shop.beautyportal.media.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "sku_product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}

