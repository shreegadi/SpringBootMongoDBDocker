FROM openjdk:21
ADD build/libs/springboot-mongodb-docker.jar springboot-mongodb-docker.jar
ENTRYPOINT ["java","-jar","/springboot-mongodb-docker.jar"]