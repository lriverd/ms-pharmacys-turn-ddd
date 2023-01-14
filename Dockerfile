FROM openjdk:17-alpine

COPY ./build/libs/ms-pharmacys-turn-*.jar ./ms-pharmacys-turn.jar

CMD ["java", "-jar", "/ms-pharmacys-turn.jar"]
