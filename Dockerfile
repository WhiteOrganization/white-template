FROM openjdk:17-jdk-alpine

ARG JAR_FILE_PATH=target/
ARG JAR_FILE=white-template-0.1-SNAPSHOT-jar-with-dependencies.jar
ARG DOCKER_JAR=${JAR_FILE}

#COPY "./target/white-template-0.1-SNAPSHOT-jar-with-dependencies.jar" "app.jar"
ADD ${JAR_FILE_PATH}${JAR_FILE} ${DOCKER_JAR}
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "${DOCKER_JAR}"]




#to download [(and create)?] image
#docker build -t "white-template-docker" .

#for docker to run the container (https://docs.docker.com/language/nodejs/run-containers/)
#docker run --name white-template-docker -p 9090:8080 white-template-docker:latest
#docker run -it -p 8080:8080 white-template-docker:latest
