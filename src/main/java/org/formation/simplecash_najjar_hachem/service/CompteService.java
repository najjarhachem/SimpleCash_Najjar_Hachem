package org.formation.simplecash_najjar_hachem.service;

import org.formation.simplecash_najjar_hachem.dto.CourantDto;
import org.formation.simplecash_najjar_hachem.dto.EpargneDto;
import org.formation.simplecash_najjar_hachem.entity.Compte;
import org.formation.simplecash_najjar_hachem.entity.Courant;
import org.formation.simplecash_najjar_hachem.entity.Epargne;

import java.util.List;
import java.util.Optional;

public interface CompteService {


        List<Compte> findAll();

        Optional<Compte> findById(Long id);

        Courant createCourant(CourantDto dto);

        Epargne createEpargne(EpargneDto dto);

        void deleteCompte(Long id);
    }

