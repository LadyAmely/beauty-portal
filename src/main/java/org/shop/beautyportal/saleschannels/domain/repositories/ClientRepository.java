package org.shop.beautyportal.saleschannels.domain.repositories;

import org.shop.beautyportal.saleschannels.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
