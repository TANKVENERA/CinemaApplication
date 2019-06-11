FROM openjdk:8
ADD target/cinema.jar cinema.jar
ENTRYPOINT ["java", "-jar", "cinema.jar" ]