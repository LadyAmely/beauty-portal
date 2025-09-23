package org.shop.beautyportal.mapper.saleschannel;

import org.mapstruct.Mapper;
import org.shop.beautyportal.saleschannels.domain.entities.QuarterReport;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.QuarterReportCreatedResponse;

@Mapper(componentModel = "spring")
public interface QuarterReportMapper {
    QuarterReportCreatedResponse toResponse(QuarterReport quarterReport);
}
