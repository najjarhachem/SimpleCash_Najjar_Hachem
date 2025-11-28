package org.formation.simplecash_najjar_hachem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.formation.simplecash_najjar_hachem.entity.Compte;

import java.util.ConcurrentModificationException;

@Setter
@Getter
@Entity
@DiscriminatorValue("EPARGNE")
public class Epargne extends Compte {


    private float plafond;

}
