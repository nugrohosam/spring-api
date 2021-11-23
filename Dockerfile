
FROM maven AS build

ENV POSTGRE_HOST=postgres
ENV RABBITMQ_HOST=rabbitmq
ENV REDIS_HOST=redis

WORKDIR /app
COPY . .
RUN mvn package -DskipTests -DPOSTGRE_HOST=${POSTGRE_HOST} -DRABBITMQ_HOST=${RABBITMQ_HOST} -DREDIS_HOST=${REDIS_HOST}

FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} app.jar