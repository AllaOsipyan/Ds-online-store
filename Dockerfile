FROM maven:latest as build
COPY pom.xml demo/pom.xml
COPY AdminPanel/pom.xml demo/AdminPanel/pom.xml
COPY AdminPanel/src demo/AdminPanel/src
RUN mvn -f demo/AdminPanel/pom.xml clean package -DskipTests

FROM java:latest

WORKDIR /src/java
EXPOSE 8080
COPY --from=build demo/AdminPanel/target/AdminPanel-0.0.1-SNAPSHOT.jar /opt/demo/AdminPanel/AdminPanel-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/opt/demo/AdminPanel/AdminPanel-0.0.1-SNAPSHOT.jar"]
