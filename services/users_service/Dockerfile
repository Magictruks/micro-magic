# Utilisez une image de base Java
FROM openjdk:17

# Copy User App Jar
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} user.jar

# Exposez le port utilisé par votre application
EXPOSE 8080

# Exécutez l'application
ENTRYPOINT ["java","-jar","/user.jar"]
