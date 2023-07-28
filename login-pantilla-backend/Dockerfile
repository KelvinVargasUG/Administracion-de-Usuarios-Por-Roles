# Establece la imagen base de OpenJDK para Java 11
FROM adoptopenjdk:11-jdk-hotspot

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia el archivo JAR generado por Maven en la imagen
COPY target/login-pantilla-backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecuta la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
