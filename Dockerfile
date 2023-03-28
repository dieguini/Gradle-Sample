FROM eclipse-temurin:8u362-b09-jdk-jammy
EXPOSE 8080
WORKDIR /app
COPY gradle/build/libs/gradle-0.0.1-SNAPSHOT.jar /app/app.jar
 
ENTRYPOINT ["java", "-jar", "/app/app.jar"]