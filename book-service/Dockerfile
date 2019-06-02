FROM openjdk:8u212-b04-jdk
ARG JAR_FILE
COPY './build/libs/book-service-1.0-SNAPSHOT.jar' '/book-service-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/book-service-1.0-SNAPSHOT.jar"]