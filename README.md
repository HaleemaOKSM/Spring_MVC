# Products App — TP Spring Boot

> Application Web JEE de gestion de produits basée sur **Spring Boot**, **Spring Data JPA**, **Hibernate**, **Thymeleaf** et **Spring Security**.


##  Objectif du TP

Développer une application CRUD complète permettant de gérer des produits, avec authentification et autorisation par rôle, interface Thymeleaf responsive avec Bootstrap 5, et persistance des données via JPA/Hibernate.


##  Technologies utilisées

| Technologie            | Version   | Rôle                                      |
|------------------------|-----------|-------------------------------------------|
| Java                   | 17        | Langage de développement                  |
| Spring Boot            | 3.2.0     | Framework principal                       |
| Spring Web (MVC)       | —         | Contrôleurs HTTP et routing               |
| Spring Data JPA        | —         | Couche d'accès aux données                |
| Hibernate              | —         | ORM (Object-Relational Mapping)           |
| Thymeleaf              | —         | Moteur de templates HTML                  |
| Thymeleaf Layout       | —         | Template partagé (layout/template.html)   |
| Spring Security        | —         | Authentification et autorisation          |
| Spring Validation      | —         | Validation des formulaires                |
| Lombok                 | —         | Réduction du code boilerplate             |
| H2 Database            | —         | Base de données en mémoire (tests)        |
| MySQL                  | —         | Base de données en production             |
| Bootstrap 5            | 5.3.0     | Interface responsive                      |
| Bootstrap Icons        | 1.11.0    | Icônes                                    |
| Maven                  | —         | Gestionnaire de dépendances               |



##  Structure du projet

```
src/
└── main/
    ├── java/ma/enset/productsapp/
    │   ├── SpringMvcApplication.java      ← Point d'entrée + données initiales
    │   ├── entities/
    │   │   └── Product.java                 ← Entité JPA
    │   ├── repository/
    │   │   └── ProductRepository.java       ← Interface Spring Data JPA
    │   ├── web/
    │   │   └── ProductController.java          ← Contrôleur principal CRUD
    │   └── sec/
    │       └── SecurityConfig.java          ← Configuration Spring Security
    └── resources/
        ├── application.properties           ← Configuration de l'application
        └── templates/
            ├── layout1.html          ← Layout partagé (navbar, footer)
            ├── products,html
            ├──login.html  
            ├──new-products.html        
            └── NotAuthorized.html
```


## Configuration

### Base de données H2 (par défaut — développement)

```properties
spring.datasource.url=jdbc:h2:mem:productsdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### Base de données MySQL (production)

Commenter la section H2 et décommenter :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/productsdb?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## Lancer l'application

### Prérequis

- Java 17+
- Maven 3.8+
- (Optionnel) MySQL Server si vous utilisez MySQL

### Commandes

```bash
# Cloner le projet
git clone https://github.com/votre-repo/products-app.git
cd products-app

# Compiler et lancer
mvn spring-boot:run
```

L'application sera accessible sur : **http://localhost:8084/login**


## Comptes utilisateurs

| Utilisateur | Mot de passe | Rôle        | Permissions                        |
|-------------|--------------|-------------|------------------------------------|
| `user`      | `1234`       | `ROLE_USER` | Lecture seule (liste, recherche)   |
| `admin`     | `1234`       | `ROLE_ADMIN`| CRUD complet (ajout, édition, suppression) |

> Les mots de passe sont encodés avec **BCryptPasswordEncoder**.


## Routes disponibles
| Page                  | Template          | Description                                                  |
|-----------------------|-------------------|--------------------------------------------------------------|
| `/login`              | `login.html`      | Formulaire de connexion (username + password)                |
| `/user/index`         | `products.html`   | Tableau de tous les produits (liste complète)                |
| `/admin/newProduct`   | `new-product.html`| Formulaire d'ajout avec validation côté serveur              |
| `/admin/delete`       | —                 | Suppression via POST, redirige vers `/user/index`            |
| `/notAuthorized`      | `notAuthorized.html` | Page affichée si l'accès est refusé                       |
 
---

##  Entité Product

```java
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 50)
    private String name;

    @NotNull @Min(0)
    private Double price;

    @NotNull @Min(0)
    private Integer quantity;
}
```


##  Fonctionnalités implémentées

###  Étapes du TP

- [x] **Étape 1** — Création du projet Spring Boot avec toutes les dépendances
- [x] **Étape 2** — Entité JPA `Product` avec annotations Lombok et Validation
- [x] **Étape 3** — Interface `ProductRepository` basée sur Spring Data JPA
- [x] **Étape 4** — Test de la couche DAO via `CommandLineRunner` au démarrage
- [x] **Étape 5** — Désactivation de la protection par défaut de Spring Security
- [x] **Étape 6** — Contrôleur MVC + vues Thymeleaf :
    - [x] Affichage de la liste des produits
    - [x] Suppression d'un produit
    - [x] Page template avec Thymeleaf Layout + Bootstrap
    - [x] Formulaire d'ajout avec validation
- [x] **Étape 7** — Sécurisation avec Spring Security (rôles USER / ADMIN).
