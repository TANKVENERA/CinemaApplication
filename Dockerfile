FROM openjdk:8
ADD target/cinema.jar cinema.jar
EXPOSE '8070'
ENTRYPOINT ["java", "-jar", "cinema.jar"]