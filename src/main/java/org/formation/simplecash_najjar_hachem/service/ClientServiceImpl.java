package org.formation.simplecash_najjar_hachem.service;

import org.formation.simplecash_najjar_hachem.dto.*;
import lombok.RequiredArgsConstructor;
import org.formation.simplecash_najjar_hachem.entity.Client;
import org.formation.simplecash_najjar_hachem.entity.Courant;
import org.formation.simplecash_najjar_hachem.entity.Epargne;
import org.formation.simplecash_najjar_hachem.mapper.ClientMapper;
import org.formation.simplecash_najjar_hachem.mapper.CompteMapper;
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
    private final CompteMapper compteMapper;

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

        var client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable avec id " + id));

        boolean hasNonEmptyCourant = client.getMonCourant() != null
                && client.getMonCourant().getSolde() != 0.0f;

        boolean hasNonEmptyEpargne = client.getMonEpargne() != null
                && client.getMonEpargne().getSolde() != 0.0f;

        if (hasNonEmptyCourant || hasNonEmptyEpargne) {
            throw new IllegalStateException("Impossible de supprimer un client avec des comptes non soldés");
        }


        clientRepository.delete(client);}

    @Override
    public CourantDto createCourantForClient(Long clientId, CourantDto dto) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable avec id " + clientId));

        if (client.getMonCourant() != null) {
            throw new IllegalStateException("Le client a déjà un compte courant");
        }

        Courant courant = compteMapper.toEntity(dto); // crée l'entité à partir du DTO
        client.setMonCourant(courant);

        // grâce au cascade ALL, save(client) va aussi persister le compte
        Client saved = clientRepository.save(client);

        return compteMapper.toDto(saved.getMonCourant());
    }

    @Override
    public EpargneDto createEpargneForClient(Long clientId, EpargneDto dto) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable avec id " + clientId));

        if (client.getMonEpargne() != null) {
            throw new IllegalStateException("Le client a déjà un compte épargne");
        }

        Epargne epargne = compteMapper.toEntity(dto);
        client.setMonEpargne(epargne);

        Client saved = clientRepository.save(client);

        return compteMapper.toDto(saved.getMonEpargne());
    }
}
