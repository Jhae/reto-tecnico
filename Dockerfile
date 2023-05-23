FROM openjdk:11
ADD build/libs/exchanged-0.0.1-SNAPSHOT.jar bundle.jar
EXPOSE 8010
CMD ["java", "-jar", "bundle.jar"]
