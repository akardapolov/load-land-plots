FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY target/*.jar /app/load_land_plots.jar

EXPOSE 8888
ENTRYPOINT ["java","-jar","/app/load_land_plots.jar"]
