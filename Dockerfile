# Définit l'image de base
FROM eclipse-temurin:21-jdk AS base

# Définit le répertoire de travail dans le conteneur
WORKDIR /workspace/app
# Créer un volume docker à partir du repertoire /tmp pour stocker les fichiers temporaires
VOLUME /tmp

# Copie les fichiers nécessaires pour la construction
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
# Résout les dépendances du projet
RUN ./mvnw dependency:resolve

# Définit le paramétrage de l'image pour l'environnement de développement
FROM base AS dev
# Commande à exécuter lors du démarrage du conteneur en mode développement
CMD ["./mvnw", "spring-boot:run"]

# Définit le paramétrage de l'image pour l'environnement de test
FROM base AS test
# Commande à exécuter lors du démarrage du conteneur en mode test
CMD ["./mvnw", "test"]

# Compilation du projet
FROM base AS build
RUN ./mvnw package

# Définit une image pour l'environnement de production
FROM eclipse-temurin:21-jre-jammy AS production
# Copie le fichier JAR de l'étape de construction précédente dans l'image de production
COPY --from=build /workspace/app/target/back-0.0.1-SNAPSHOT.jar back.jar
# Commande à exécuter lors du démarrage du conteneur en mode production
CMD ["java", "-jar", "/back.jar"]