# Etapa 1: Compilacion de la aplicacion
FROM gradle:8-jdk21 AS build 

WORKDIR /app

COPY . .

RUN gradle clean bootJar --no-daemon

# Etapa 2: Ejecución de la aplicación
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/build/libs/discografia-1.jar discografia-1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "discografia-1.jar"]