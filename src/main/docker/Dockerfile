FROM java:8

ARG JAR_FILE

ADD target/${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom","-jar","/app.jar"]