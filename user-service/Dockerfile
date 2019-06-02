FROM openjdk:8u212-b04-jdk
ARG JAR_FILE
COPY './build/libs/user-service-1.0-SNAPSHOT.jar' '/user-service-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/user-service-1.0-SNAPSHOT.jar"]