package org.shop.beautyportal.media.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "campaign",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_campaign_year_month", columnNames = "year_month")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "campaign_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year_month", nullable = false, length = 7)
    private String yearMonth;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}

