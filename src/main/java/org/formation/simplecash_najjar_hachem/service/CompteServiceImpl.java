package org.formation.simplecash_najjar_hachem.service;

import lombok.RequiredArgsConstructor;
import org.formation.simplecash_najjar_hachem.dto.CourantDto;
import org.formation.simplecash_najjar_hachem.dto.EpargneDto;
import org.formation.simplecash_najjar_hachem.entity.Compte;
import org.formation.simplecash_najjar_hachem.entity.Courant;
import org.formation.simplecash_najjar_hachem.entity.Epargne;
import org.formation.simplecash_najjar_hachem.mapper.CompteMapper;
import org.formation.simplecash_najjar_hachem.repository.ComptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompteServiceImpl implements CompteService {

    private final ComptRepository compteRepository;
    private final CompteMapper compteMapper;

    @Override
    public List<Compte> findAll() {
        return compteRepository.findAll();
    }

    @Override
    public Optional<Compte> findById(Long id) {
        return compteRepository.findById(id);
    }

    @Override
    public Courant createCourant(CourantDto dto) {
        Courant courant = compteMapper.toEntity(dto);
        return (Courant) compteRepository.save(courant);
    }

    @Override
    public Epargne createEpargne(EpargneDto dto) {
        Epargne epargne = compteMapper.toEntity(dto);
        return (Epargne) compteRepository.save(epargne);
    }

    @Override
    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }
}
