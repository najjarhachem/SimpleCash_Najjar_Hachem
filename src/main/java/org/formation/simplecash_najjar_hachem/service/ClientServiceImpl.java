package org.formation.simplecash_najjar_hachem.service;

import org.formation.simplecash_najjar_hachem.dto.ClientCreateDto;
import org.formation.simplecash_najjar_hachem.dto.ClientDto;
import org.formation.simplecash_najjar_hachem.dto.ClientUpdateDto;
import lombok.RequiredArgsConstructor;
import org.formation.simplecash_najjar_hachem.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.formation.simplecash_najjar_hachem.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @Override
    public ClientDto save(ClientCreateDto client) {
        return mapper.toDto(clientRepository.save(mapper.toEntity(client)));
    }


    @Override
    public List<ClientDto> findAll(){
        return clientRepository.findAll().stream().map(mapper::toDto).toList();
    }


    @Transactional
    @Override
    public Optional<ClientDto> updateClient(Long id, ClientUpdateDto dto) {
        clientRepository.findById(id).map(
                client -> {
                    mapper.updateEntity(client, dto);
                    return mapper.toDto(clientRepository.save(client));
                });
        return Optional.empty();
    }

    @Override
    public Optional<ClientDto> findById(Long id){
        return clientRepository.findById(id).map(mapper::toDto);
    }


    @Override
    public void deleteClient(Long id) {
        Optional<ClientDto> existing = findById(id);
        clientRepository.deleteById(id);
    }
}
