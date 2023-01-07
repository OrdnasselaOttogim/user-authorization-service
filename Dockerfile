FROM openjdk:17

COPY target/user-authorization-service.jar user-authorization-service.jar

ENTRYPOINT ["java","-jar","user-authorization-service.jar"]

EXPOSE 8090
