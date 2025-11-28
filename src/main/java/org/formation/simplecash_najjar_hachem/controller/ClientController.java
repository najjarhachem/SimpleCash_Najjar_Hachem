package org.formation.simplecash_najjar_hachem.controller;


import org.formation.simplecash_najjar_hachem.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.formation.simplecash_najjar_hachem.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

   @GetMapping("{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
       return clientService.findById(id).map(client -> ResponseEntity.ok().body(client))
               .orElseGet(() -> ResponseEntity.notFound().build());
   }

   @GetMapping
    public List<ClientDto> getClients(){
       return clientService.findAll();
   }

   @PostMapping
    public ClientDto createClient(@RequestBody @Valid ClientCreateDto clientDto){
         return clientService.save(clientDto);
   }

   @PutMapping("{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpdateDto clientDto){
       return clientService.updateClient(id, clientDto).map(client -> ResponseEntity.ok().body(client))
               .orElseGet(() -> ResponseEntity.notFound().build());
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            // comptes non soldés
            return ResponseEntity.badRequest().build();
        } catch (IllegalArgumentException e) {
            // client introuvable
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/comptes/courant")
    public ResponseEntity<CourantDto> createCourantForClient(
            @PathVariable Long id,
            @RequestBody @Valid CourantDto dto
    ) {
        try {
            CourantDto created = clientService.createCourantForClient(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            // client introuvable
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            // déjà un compte courant
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/{id}/comptes/epargne")
    public ResponseEntity<EpargneDto> createEpargneForClient(
            @PathVariable Long id,
            @RequestBody @Valid EpargneDto dto
    ) {
        try {
            EpargneDto created = clientService.createEpargneForClient(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            // client introuvable
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            // déjà un compte épargne
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
