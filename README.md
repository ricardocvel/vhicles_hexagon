# desafio_java_cloud





Dependencias
mvn clear 
mvn install -DskipTests

play local
mvn spring-boot:run

Monta container
docker build -t vehicle .
docker run -p 8081:8081 vehicle