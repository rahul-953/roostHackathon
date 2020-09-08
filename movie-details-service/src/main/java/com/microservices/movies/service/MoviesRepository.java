package com.microservices.movies.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie, Long> {

	Optional<Movie> findById(Long id);

	Optional<Movie> findByTitleIgnoreCase(String movieName);
}
