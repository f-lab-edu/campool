FROM openjdk:8-jdk
EXPOSE 8080
ADD target/campool-0.0.1-SNAPSHOT.jar webapp.jar
ENTRYPOINT ["java","-jar","/webapp.jar"]
