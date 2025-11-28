package org.formation.simplecash_najjar_hachem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;
import java.util.Date;

@Entity
public abstract class Compte {

    @Id
    @GeneratedValue
    private int Id;

    private float solde;

    private LocalDate creationDate;


    public void crediter(float montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à créditer doit être positif");
        }
        this.solde += montant;
    }

    public void debiter(float montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à débiter doit être positif");
        }
        this.solde -= montant;
    }


}
