FROM maven:3.6-jdk-8 AS build
COPY pom.xml /
RUN ["mvn", "package", "-DskipTests"]
COPY . /
RUN ["mvn", "clean", "install"]

FROM ubuntu:18.04
COPY --from=build . /
ENTRYPOINT ["java", "-jar", "target/jbox-0.0.1-SNAPSHOT.jar"]