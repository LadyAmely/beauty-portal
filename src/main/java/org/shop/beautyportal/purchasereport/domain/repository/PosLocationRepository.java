package org.shop.beautyportal.purchasereport.domain.repository;

import org.shop.beautyportal.purchasereport.domain.entities.PosLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

public interface PosLocationRepository extends JpaRepository<PosLocation, UUID> {

    @Query("""
        select count(p)
        from PosLocation p
        where p.distributor.id = :distributorId
          and p.openedAt <= :asOf
          and (p.closedAt is null or p.closedAt > :asOf)
    """)
    long countActiveAsOf(@Param("distributorId") UUID distributorId,
                         @Param("asOf") LocalDate asOf);

    @Query("""
        select count(p)
        from PosLocation p
        where p.distributor.id = :distributorId
          and p.openedAt >= :from
          and p.openedAt  < :to
    """)
    long countOpenedBetween(@Param("distributorId") UUID distributorId,
                            @Param("from") LocalDate from,
                            @Param("to") LocalDate to);
}
