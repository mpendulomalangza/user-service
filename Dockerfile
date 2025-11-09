FROM amazonlinux:2
ARG version=17.0.17.10-1
ADD target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar","user-service.jar"]