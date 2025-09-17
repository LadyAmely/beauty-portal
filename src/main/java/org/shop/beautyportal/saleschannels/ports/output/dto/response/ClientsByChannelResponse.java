package org.shop.beautyportal.saleschannels.ports.output.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.shop.beautyportal.saleschannels.domain.entities.SalesChannel;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Schema(name = "ClientsByChannelResponse")
public class ClientsByChannelResponse {
    UUID distributorId;
    Map<SalesChannel, List<ClientDTO>> clientsByChannel;

    @Builder
    public static class ClientDTO {
        UUID id;
        String name;
        String taxId;
    }
}

