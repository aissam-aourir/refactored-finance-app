# AVEC MAVEAN
FROM maven:3.9.6-eclipse-temurin-22 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# IMAGE FINAL
FROM eclipse-temurin:22-jre
WORKDIR /app
COPY --from=build /app/target/refactored-finance-app-1.0-SNAPSHOT.jar app.jar

#MAPPING PORTUAIRE
EXPOSE 8080

#COMMANDE DE LAMCEMENT
ENTRYPOINT ["java", "-jar", "app.jar"]