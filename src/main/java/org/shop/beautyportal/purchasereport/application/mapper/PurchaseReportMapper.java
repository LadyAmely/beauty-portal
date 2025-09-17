package org.shop.beautyportal.purchasereport.application.mapper;

import org.mapstruct.*;
import org.shop.beautyportal.purchasereport.ports.input.dto.request.PurchaseReportUpsertRequest;
import org.shop.beautyportal.purchasereport.ports.output.dto.response.PurchaseReportResponse;
import org.shop.beautyportal.purchasereport.domain.entities.PurchaseReport;

@Mapper(componentModel = "spring")
public interface PurchaseReportMapper {

    /**
     * Maps entity basic fields to response. Computed fields (actualSales, yoyPct, vsBudgetPct,
     * POS metrics) are filled in service.
     */
    @Mapping(target = "distributorId", source = "distributor.id")
    @Mapping(target = "distributorName", source = "distributor.name")
    @Mapping(target = "actualSales", ignore = true)
    @Mapping(target = "yoyPct", ignore = true)
    @Mapping(target = "vsBudgetPct", ignore = true)
    @Mapping(target = "totalPos", ignore = true)
    @Mapping(target = "newOpenings", ignore = true)
    @Mapping(target = "newOpeningsTarget", ignore = true)
    PurchaseReportResponse toResponseBase(PurchaseReport entity);

    /**
     * Updates mutable fields of an entity from request.
     * Use in upsert paths after loading/creating the entity.
     */
    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "distributor", ignore = true),
            @Mapping(target = "year",       source = "year"),
            @Mapping(target = "quarter",    source = "quarter"),
            @Mapping(target = "lastYearSales", source = "lastYearSales"),
            @Mapping(target = "purchases",     source = "purchases"),
            @Mapping(target = "budget",        source = "budget")
    })
    void updateEntityFromRequest(PurchaseReportUpsertRequest req,
                                 @MappingTarget PurchaseReport entity);
}
