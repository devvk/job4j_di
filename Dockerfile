FROM maven:3.9.11-eclipse-temurin-21

WORKDIR /job4j_di

COPY . .

RUN mvn clean package

CMD ["java", "-jar", "target/job4j_di.jar"]

# docker build -t job4j_di:1.0.0 .
# docker run --rm job4j_di:1.0.0