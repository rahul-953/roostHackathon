FROM openjdk:8-jre-alpine
COPY target/movie-details-service-*.jar /app/
CMD ["/usr/bin/java", "-jar", "/app/movie-details-service-0.0.1-SNAPSHOT.jar", "com.microservices.movies.service.MovieDetailsServiceApplication"]
EXPOSE 30047
EXPOSE 30046