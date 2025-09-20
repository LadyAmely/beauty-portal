package org.shop.beautyportal.purchasereport.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.shop.beautyportal.saleschannels.domain.entities.Distributor;
import java.util.UUID;

@Entity
@Table(
        name = "distributor_assignments",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "distributor_id","export_manager_id","assigned_from","assigned_to"
        })
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DistributorAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private Distributor distributor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "export_manager_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private ExportManager exportManager;

    @Column(name = "assigned_from", nullable = false)
    private java.time.LocalDate assignedFrom;

    @Column(name = "assigned_to", nullable = false)
    private java.time.LocalDate assignedTo;
}
