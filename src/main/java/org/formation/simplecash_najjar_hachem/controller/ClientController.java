package org.formation.simplecash_najjar_hachem.controller;


import org.formation.simplecash_najjar_hachem.dto.ClientCreateDto;
import org.formation.simplecash_najjar_hachem.dto.ClientDto;
import org.formation.simplecash_najjar_hachem.dto.ClientUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

   @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
       clientService.deleteClient(id);
       return ResponseEntity.noContent().build();
   }
}
