package org.shop.beautyportal.mapper.saleschannel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.shop.beautyportal.saleschannels.domain.entities.Distributor;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.DistributorResponse;

@Mapper(componentModel = "spring")
public interface DistributorMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="code", target="code")
    @Mapping(source="name", target="name")
    DistributorResponse toResponse(Distributor d);
}
