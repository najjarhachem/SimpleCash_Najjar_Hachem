package org.formation.simplecash_najjar_hachem.repository;

import org.formation.simplecash_najjar_hachem.dto.ClientDto;
import org.formation.simplecash_najjar_hachem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
