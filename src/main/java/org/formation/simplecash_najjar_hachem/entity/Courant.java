package org.formation.simplecash_najjar_hachem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.formation.simplecash_najjar_hachem.entity.Compte;

@Setter
@Getter
@Entity
@DiscriminatorValue("COURANT")
public class Courant extends Compte {


    private float remuneration;

}
