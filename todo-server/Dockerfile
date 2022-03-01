FROM openjdk:latest
EXPOSE 8080
ENV APP_HOME /app
RUN mkdir $APP_HOME
WORKDIR $APP_HOME
COPY /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]