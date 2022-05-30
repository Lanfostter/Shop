#base image: linux alpine os with open jdk 8
FROM openjdk:18-jdk-alpine
#copy jar from local into docker image
COPY target/shop-0.0.1-SNAPSHOT.jar shop-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "shop-0.0.1-SNAPSHOT.jar"]