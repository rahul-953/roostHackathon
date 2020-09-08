FROM openjdk:8-jre-alpine as builder
COPY target/imdb-movie-details-*.jar /app/
CMD ["/usr/bin/java", "-jar", "/app/imdb-movie-details-0.0.1-SNAPSHOT.jar", "com.microservices.imdb.movieservice.ImdbMovieDetailsServiceApplication"]
EXPOSE 30046
EXPOSE 30047