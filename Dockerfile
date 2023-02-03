FROM openjdk:17-jdk-slim


#ENV JAR_FILE_PATH=target/
#ENV JAR_FILE=white-template-0.1-SNAPSHOT-with-dependencies.jar
#ENV DOCKER_JAR=${JAR_FILE}

#COPY "./target/white-template-0.1-SNAPSHOT-with-dependencies.jar" "app.jar"
ADD target/white-template-0.1-SNAPSHOT-with-dependencies.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]




#to download [(and create)?] image
#docker build -t "white-template-docker" .

#for docker to run the container (https://docs.docker.com/language/nodejs/run-containers/)
#docker run --name white-template-docker -p 9090:8080 white-template-docker:latest
#docker run -it -p 8080:8080 white-template-docker:latest
