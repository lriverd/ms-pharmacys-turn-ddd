FROM openjdk:17-alpine

COPY ./build/libs/ .
ENTRYPOINT ["/bin/sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./ms-pharmacys-turn-*.jar"]
