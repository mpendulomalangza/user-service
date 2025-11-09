FROM openjdk:17-jdk
ADD target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar","user-service.jar"]