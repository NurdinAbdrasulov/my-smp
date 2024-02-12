FROM openjdk:17-alpine
WORKDIR /workspace
COPY target/qr-code-generator-0.0.1-SNAPSHOT.jar /workspace/
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "-Dspring.datasource.url=jdbc:postgresql://host.docker.internal:5432/spm", "qr-code-generator-0.0.1-SNAPSHOT.jar"]

