FROM openjdk:11

RUN mkdir /app

WORKDIR /app

COPY ./config /app/config
COPY ./start.sh /app/.
COPY ./livibank-ans-1.0.0-SNAPSHOT.jar /app/.

CMD ["bash", "-c", "./start.sh"]