# 1. Image de base légère contenant uniquement le JRE Java 17
FROM eclipse-temurin:17-jre

# 2. Dossier de travail à l'intérieur du conteneur
WORKDIR /app

# 3. Copier le fichier .jar généré par Maven dans le conteneur

# 4. Commande exécutée au démarrage du conteneur
CMD ["java", "-cp", "app.jar", "com.tp.Calculator"]