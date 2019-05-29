FROM java:8-jdk-alpine
ADD ./build/libs/*.jar opt/shipmentmanagement-0.0.1-SNAPSHOT.jar
ADD STAR_ias.p12 opt/
EXPOSE 8502
WORKDIR /opt/
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=mysql","shipmentmanagement-0.0.1-SNAPSHOT.jar"]