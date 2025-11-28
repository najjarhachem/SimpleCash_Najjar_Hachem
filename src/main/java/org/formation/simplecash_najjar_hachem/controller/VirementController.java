package org.formation.simplecash_najjar_hachem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.formation.simplecash_najjar_hachem.dto.VirementRequestDto;
import org.formation.simplecash_najjar_hachem.service.VirementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/virements")
@RequiredArgsConstructor
public class VirementController {

    private final VirementService virementService;

    @PostMapping
    public ResponseEntity<Void> faireVirement(@RequestBody @Valid VirementRequestDto request) {
        virementService.faireVirement(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}