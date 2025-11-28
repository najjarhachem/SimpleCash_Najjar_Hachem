package org.formation.simplecash_najjar_hachem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.formation.simplecash_najjar_hachem.dto.VirementRequestDto;
import org.formation.simplecash_najjar_hachem.entity.Compte;
import org.formation.simplecash_najjar_hachem.repository.ComptRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VirementService {

    private final ComptRepository compteRepository;


    @Transactional
    public void faireVirement(VirementRequestDto request) {
        Compte source = compteRepository.findById(request.idSource())
                .orElseThrow(() -> new IllegalArgumentException("Compte source introuvable"));
        Compte destination = compteRepository.findById(request.idDestination())
                .orElseThrow(() -> new IllegalArgumentException("Compte destination introuvable"));

        float montant = request.montant();
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être strictement positif");
        }

        // on utilise tes méthodes métier sur Compte (debiter / crediter)
        source.debiter(montant);
        destination.crediter(montant);

        // JPA est transactionnel : le flush se fera en fin de méthode
        compteRepository.save(source);
        compteRepository.save(destination);
    }

}
