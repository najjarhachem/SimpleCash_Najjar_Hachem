SimpleCash – Projet Java Spring Boot

Projet de gestion simplifiée de clients et de comptes bancaires, réalisé dans le cadre d’un travail académique.

L’application permet de gérer :

  des clients,
  des comptes bancaires (courant et épargne),
  des opérations de crédit, débit et virement,
  des règles métier autour de la suppression de clients.

Stack technique

  Java 17
  Spring Boot 3.5.8
  Base de données H2 (en mémoire)
  hibernate / JPA
  MapStruct (mappage DTO ↔ entités)
  Lombok

Architecture

  Organisation par couches classiques :
  
    entity : entités JPA (Client, Compte, Courant, Epargne, etc.)
    dto : objets de transfert pour l’API (ClientDto, ClientCreateDto, ClientUpdateDto, CourantDto, EpargneDto, OperationCompteDto, VirementRequestDto, etc.)
    mapper : interfaces MapStruct (ClientMapper, CompteMapper)
    repository : interfaces Spring Data JPA (ClientRepository, ComptRepository)
    service : logique métier (ClientService, ClientServiceImpl, CompteService, CompteServiceImpl, VirementService)
    controller : contrôleurs REST (ClientController, CompteController, VirementController)

Fonctionnalités réalisées
Gestion des clients

Entité Client avec les champs principaux : id, nom, prénom, adresse, code postal, ville, téléphone.
Relations vers les comptes :

monCourant : compte courant du client

monEpargne : compte épargne du client


DTO et mapping :

  ClientCreateDto, ClientUpdateDto, ClientDto
  ClientMapper pour convertir entité ↔ DTO et appliquer des mises à jour partielles.

Service :

  Création de client
  Liste de tous les clients
  Recherche par id
  Mise à jour d’un client
  Suppression d’un client avec règle métier :
  Règle métier pour la suppression :
  Impossible de supprimer un client si son compte courant ou son compte épargne existe avec un solde différent de 0.
  Si les comptes sont inexistants ou tous à 0, suppression du client et des comptes associés.

Contrôleur ClientController :

  GET /client ou /clients : liste des clients
  GET /client/{id} : détail d’un client 
  POST /client : création d’un client 
  PUT /client/{id} : mise à jour 
  DELETE /client/{id} : suppression avec vérification des soldes 
  
Gestion des comptes

DTO et mapping :

  CourantDto (solde, remuneration)
  EpargneDto (solde, plafond)
  OperationCompteDto (montant)


Contrôleur CompteController (base /comptes) :

  GET /comptes : tous les comptes
  GET /comptes/{id} : un compte
  POST /comptes/courant : création d’un compte courant
  POST /comptes/epargne : création d’un compte épargne
  POST /comptes/{id}/credit : créditer un compte
  POST /comptes/{id}/debit : débiter un compte
  DELETE /comptes/{id} : suppression d’un compte


Virements

DTO :
VirementRequestDto avec idSource, idDestination, montant.

Service VirementService :
faireVirement(VirementRequestDto request) :

charge les comptes source et destination

vérifie le montant

débite le compte source, crédite le compte destination

transaction gérée par Spring (@Transactional).

Contrôleur VirementController (base /virements) :

POST /virements : effectuer un virement entre deux comptes.



Niveau d’avancement

Fonctionnalités implémentées et testées :

  CRUD complet sur les clients (avec DTO et mapper).
  Règle métier de suppression des clients en fonction des soldes des comptes.
  Modélisation des comptes (compte abstrait + comptes spécialisés).
  Création et consultation de comptes.
  Crédit et débit d’un compte spécifique.
  Création de comptes rattachés à un client, avec la contrainte d’un compte courant et un compte épargne maximum par client.
  Virement d’un compte à un autre avec gestion transactionnelle.

Fonctionnalités prévues ou améliorations possibles :

  Utilisation de Spring AOP pour journaliser les virements (logs dédiés).
  Gestion plus détaillée des messages d’erreur dans les réponses HTTP.
  Ajout de tests unitaires et d’intégration.
  Sécurisation de l’API (rôles, authentification).
  Possible interface graphique (front-end) pour exploiter l’API.


  <img width="650" height="701" alt="Capture d’écran du 2025-11-28 17-47-00" src="https://github.com/user-attachments/assets/c81447ef-9bc7-492e-aef5-2e6ee4212b32" />
