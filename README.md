L'application est entièrement conteneurisée avec un **multi-stage build** :

### Images utilisées
- Build : `maven:3.9.6-eclipse-temurin-22`
- Runtime : `eclipse-temurin:22-jre` (80 Mo seulement)

### Lancement
```bash
docker-compose up --build