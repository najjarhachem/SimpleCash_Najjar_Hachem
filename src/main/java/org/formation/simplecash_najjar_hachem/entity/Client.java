package org.formation.simplecash_najjar_hachem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "nom client vide")
    private String nom;

    @NotBlank(message = "prenom client vide")
    private String prenom;

    private String adresse;
    private String codePostale;
    private String ville;
    private String telephone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "epargne_id")
    private Epargne monEpargne;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "courant_id")
    private Courant monCourant;


    public void virement(float montant, Compte source, Compte destination) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Les comptes source et destination doivent être renseignés");
        }
        source.debiter(montant);
        destination.crediter(montant);
    }

    public void retrait(float montant, Compte compte) {
        if (compte == null) {
            throw new IllegalArgumentException("Le compte ne doit pas être nul");
        }
        compte.debiter(montant);
    }




}
