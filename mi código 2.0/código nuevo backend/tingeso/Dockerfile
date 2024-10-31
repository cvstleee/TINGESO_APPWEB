FROM openjdk:17
ARG JAR_FILE=target/tingesobackend.jar
COPY ${JAR_FILE} tingesobackend.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/tingesobackend.jar"]