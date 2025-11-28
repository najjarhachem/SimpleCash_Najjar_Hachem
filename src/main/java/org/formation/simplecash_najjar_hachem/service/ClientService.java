package org.formation.simplecash_najjar_hachem.service;

import org.formation.simplecash_najjar_hachem.dto.ClientCreateDto;
import org.formation.simplecash_najjar_hachem.dto.ClientDto;
import org.formation.simplecash_najjar_hachem.dto.ClientUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientDto save(ClientCreateDto client);

    Optional<ClientDto> findById(Long id);
    List<ClientDto> findAll();

    Optional<ClientDto> updateClient(Long id, ClientUpdateDto dto);

    void deleteClient(Long id);
}
