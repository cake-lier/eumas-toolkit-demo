FROM eclipse-temurin:21

# Copy the jar
RUN mkdir /opt/app
COPY build/libs/app.jar /opt/app

ENTRYPOINT java -jar /opt/app/app.jar