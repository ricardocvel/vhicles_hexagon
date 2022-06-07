FROM java:8-jdk-alpine
COPY ./target/vehicles-0.0.2.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch vehicles-0.0.2.jar'
ENTRYPOINT ["java","-jar","vehicles-0.0.2.jar"]