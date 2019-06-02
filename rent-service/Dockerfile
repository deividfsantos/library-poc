FROM openjdk:8u212-b04-jdk
ARG JAR_FILE
COPY './build/libs/rent-service-1.0-SNAPSHOT.jar' '/rent-service-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/rent-service-1.0-SNAPSHOT.jar"]