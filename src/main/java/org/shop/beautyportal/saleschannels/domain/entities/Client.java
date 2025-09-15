package org.shop.beautyportal.saleschannels.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID Id;

    @ManyToOne
    @JoinColumn(name="distributor_id")
    private Distributor distributor;

    @Column(length=255)
    private String name;

    @Column(length=64)
    private String taxId;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
