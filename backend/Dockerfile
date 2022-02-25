FROM maven AS build-stage

WORKDIR /app

COPY . .

RUN mvn package

FROM tomcat:9

COPY --from=build-stage /app/target/detQA.war /usr/local/tomcat/webapps
