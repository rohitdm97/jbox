FROM maven:3.6-jdk-8 AS build
COPY pom.xml /
RUN ["mvn", "package", "-DskipTests"]
COPY . /
RUN ["mvn", "clean", "install"]

FROM drohitdmallappanavar/ubaserdm:2ed6e6d
COPY --from=build . /
ENTRYPOINT ["java", "-jar", "target/jbox-0.0.1-SNAPSHOT.jar"]
