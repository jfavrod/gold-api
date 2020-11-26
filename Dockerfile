FROM openjdk:14-alpine
RUN apk add --no-cache maven
EXPOSE 8080
RUN mkdir /app
WORKDIR /app
ENV PATH="${PATH}:/app/"
COPY env/non-prod /app/.env
COPY scripts/start.sh /app/start.sh
COPY target/gold-0.0.1-SNAPSHOT.jar /app/gold-api.jar
RUN chmod +x /app/.env
RUN chmod +x /app/start.sh
CMD [ "start.sh" ]
