package org.shop.beautyportal.mapper.saleschannel;

import org.mapstruct.Mapper;
import org.shop.beautyportal.saleschannels.domain.entities.Distributor;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.DistributorResponse;

@Mapper(componentModel = "spring")
public interface DistributorMapper {
    DistributorResponse toResponse(Distributor d);
}
