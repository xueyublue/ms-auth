# Start with a base image containing Java runtime
FROM openjdk:17-slim as build

# Information around who maintains the image
MAINTAINER darren

# Add the application's jar to the container
COPY target/ms-auth-1.0.0-SNAPSHOT.jar ms-auth-1.0.0-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "/ms-auth-1.0.0-SNAPSHOT.jar"]