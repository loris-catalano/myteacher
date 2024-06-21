FROM eclipse-temurin:17-jre-focal
#FROM ghcr.io/graalvm/graalvm-community:17


ENV MONGO_ADDRESS=172.31.6.3
ENV MONGO_PORT=27017



ADD target/Payment-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
