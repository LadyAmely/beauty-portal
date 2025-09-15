package org.shop.beautyportal.saleschannels.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistributorIdentityMap {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID Id;

    @ManyToOne
    @Column(name="distributor_id")
    private Distributor distributor;

    @Column(length=64)
    private String idpProvider;

    @Column(length=255)
    private String idpSubject;

    @Column(length=64)
    private String role;
}
