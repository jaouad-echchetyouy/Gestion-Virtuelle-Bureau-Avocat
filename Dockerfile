# =============================================================================
# Stage 1: Build avec Maven + JDK 17 (Eclipse Temurin)
# =============================================================================
FROM maven:3.9.9-eclipse-temurin-17-alpine AS builder

# Installer git pour les dépendances qui en ont besoin
RUN apk add --no-cache git

WORKDIR /app

# Copier seulement le pom.xml d'abord pour profiter du cache Docker (layer caching)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

# Télécharger les dépendances (cacheable si pom.xml n'a pas changé)
RUN ./mvnw dependency:go-offline -B

# Copier le code source
COPY src ./src

# Builder le projet (skip tests pour l'image de build, tests dans CI)
RUN ./mvnw clean package -DskipTests -B

# =============================================================================
# Stage 2: Runtime avec JRE 17 (Eclipse Temurin) - image minimale
# =============================================================================
FROM eclipse-temurin:17-jre-alpine AS runtime

# Créer un utilisateur non-root pour la sécurité
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

WORKDIR /app

# Copier le WAR depuis le stage de build
COPY --from=builder /app/target/spring-security-login-and-registration.war app.war

# Changer le propriétaire
RUN chown appuser:appgroup app.war

# Passer à l'utilisateur non-root
USER appuser

# Variables d'environnement pour la JVM
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"

# Exposer le port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Point d'entrée
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.war"]