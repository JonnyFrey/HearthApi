FROM openjdk:8-jdk as builder
MAINTAINER Jonny Frey, waffelmonsterjcf@gmail.com
RUN mkdir -p /build
WORKDIR /build

RUN curl -Ls https://git.io/sbt > /usr/local/bin/sbt && chmod 0755 /usr/local/bin/sbt
COPY project/build.properties project/build.properties
RUN sbt update

COPY build.sbt build.sbt
COPY project project
RUN sbt update test:update runtime:update

COPY . /build

RUN sbt test:compile
RUN sbt universal:packageZipTarball
RUN tar xvzf /build/target/universal/hearth-api.tgz

FROM openjdk:8-jre-slim-stretch

RUN mkdir -p /srv/
WORKDIR /srv/

COPY --from=builder /build/hearth-api/ /srv/

EXPOSE 9000

CMD /srv/bin/hearthapi