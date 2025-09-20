package org.shop.beautyportal.purchasereport.domain.repository;

import org.shop.beautyportal.purchasereport.domain.entities.PosOpeningsTarget;
import org.shop.beautyportal.purchasereport.domain.entities.PosOpeningsTargetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PosOpeningsTargetRepository
        extends JpaRepository<PosOpeningsTarget, PosOpeningsTargetKey> {

    @Query("""
        select t.newOpeningsTarget
        from PosOpeningsTarget t
        where t.id.distributorId = :distributorId
          and t.id.year = :year
    """)
    Optional<Integer> findTargetFor(@Param("distributorId") UUID distributorId,
                                    @Param("year") Integer year);
}

