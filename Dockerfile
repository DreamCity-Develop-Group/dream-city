FROM java:8
VOLUME /tmp
COPY city-comm/city-comm-0.0.1-SNAPSHOT.jar city-comm.jar
RUN bash -c "touch /city-comm.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","city-comm.jar"]