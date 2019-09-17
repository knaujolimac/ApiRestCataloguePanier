## Auteur - JUAN CAMILO CHAPARRO CASTRO
### Cet API REST permet la gestion et l'achat de produits dans le panier. Cet application a été developpé avec SpringBoot et Maven. L'IDE utilisé a été STS Spring Tool Suite
===================================

## Technologies
===================================
* Spring Boot 4-4.0.2.RELEASE
* Spring Data JPA
* Lombok
* h2
* JDK 8
* JUnit
* Mockito
* Slf4j

Note: Si vous devez configurer le projet dans l'IDE, vous devez installer la bibliothèque Lombok pour éviter les erreurs de compilation. Comme cette bibliothèque nous permet de générer les méthodes get et set de tous les objets (DTO, entités, etc.), elle offre également la possibilité d’utiliser @Slf4j.


## Cet API REST - Operations
===================================

* GET http://localhost:8443/CatalogueProdApi/afficherCatalogueProduits
  Operation qui retourne un catalogue de produits.
  
* GET http://localhost:8443/CatalogueProdApi/afficherDetailProduit/produit/{idProduit}
  Operation qui retourne le detail d'un produit avec son id.
  
* POST  http://localhost:8443/CatalogueProdApi/ajouterProduitPanier/produit/{idProduit}
  Operation qui retourne le detail d'un produit avec son id.

* DELETE http://localhost:8443/CatalogueProdApi/enleverProduitPanier/produit/{idProduit}
  Operation qui enleve un produit du catalogue au panier
  
* GET http://localhost:8443/CatalogueProdApi/afficherContenuPanier
  Operation qui retourne le contenu du panier
  
  
## Comment lancer l'aplication
===================================

Dans le dossier [install] situé dans le répertoire suivant [ApiRestCataloguePanier/ApiCatalogueProdServices/install/], vous trouverez le fichier portant le nom ApiCatalogueProdServices.jar.
Vous devez le télécharger sur votre ordinateur et utiliser la ligne de commande pour écrire la phrase suivante:

java -jar ApiCatalogueProdServices.jar

Ensuite, vous pouvez utiliser l'outil POSTMAN pour pouvoir les appeler.
