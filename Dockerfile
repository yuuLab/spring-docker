FROM openjdk:11-slim

COPY ./DemoApp/target/demoapp.jar /tmp/demoapp.jar
ENTRYPOINT ["java", "-jar", "/tmp/demoapp.jar"]