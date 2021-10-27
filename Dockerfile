FROM openjdk:11
MAINTAINER acolak
VOLUME /tmp
EXPOSE 8080
ADD target/ReadingIsGood-0.0.1-SNAPSHOT.jar ReadingIsGood_docker.jar
ENTRYPOINT ["java","-jar","/ReadingIsGood_docker.jar"]