FROM gradle:8-jdk21-alpine AS temp

ENV APP_HOME=/app/api
WORKDIR $APP_HOME

COPY . $APP_HOME

RUN gradle build -x test --no-daemon

FROM eclipse-temurin:21-jre
ENV APP_HOME=/app/api
WORKDIR $APP_HOME

COPY --from=temp $APP_HOME/build/libs/shadow-deals-0.1-all.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]