FROM adoptopenjdk:11-jdk-hotspot
MAINTAINER kelvinVargas

# Agregar el usuario y grupo "spring"
RUN addgroup spring && adduser -D -G spring spring

USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} login-pantilla-backend.jar

ENTRYPOINT ["java", "-jar", "/login-pantilla-backend.jar"]
