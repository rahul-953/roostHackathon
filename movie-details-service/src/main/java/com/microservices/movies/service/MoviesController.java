package com.microservices.movies.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.movies.errors.MovieNotFoundException;

@SpringBootApplication
@RestController
@RequestMapping("/movies")
public class MoviesController {

	final Logger log = LoggerFactory.getLogger(MoviesController.class);

  @Bean
	@LoadBalanced
	RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private MoviesRepository repo;

	private int totalMovies = 1;

	@GetMapping()
	public ModelAndView getAllMovies() {
		List<Movie> allMovies = repo.findAll();
		ModelAndView model = new ModelAndView("movies");
		model.addObject("movies", allMovies);

		return model;
	}

	@GetMapping("/search/id/{movieId}")
	public ModelAndView getMovieById(@PathVariable Long movieId) {

		ModelAndView model = new ModelAndView("movieDetail");
		Optional<Movie> movies = repo.findById(movieId);
		if (!movies.isPresent()) {
			throw new MovieNotFoundException("Id does not exists...");
		}

		model.addObject("movieTitle", movies.get().getTitle());
		model.addObject("movie", movies.get());

		return model;
	}

	@GetMapping("/search/name/{movieName}")
	public ModelAndView getMovieByName(@PathVariable String movieName, HttpServletRequest request) throws Exception {

		ModelAndView model = new ModelAndView("movieDetail");
		Optional<Movie> movies = repo.findByTitleIgnoreCase(movieName);
		if (!movies.isPresent()) {
			  String message = "No movie found of the name '" + movieName + "'. Try using '/add/{movieName}' endpoint.";
				throw new MovieNotFoundException(message);
		}

		model.addObject("movieTitle", movies.get().getTitle());
		model.addObject("movie", movies.get());

		return model;
	}

	@GetMapping("/add/{movieName}")
	public ModelAndView searchAndAddMovieByName(@PathVariable String movieName, HttpServletRequest request) throws Exception {

		ModelAndView model = new ModelAndView("movieDetail");
		Optional<Movie> movies = repo.findByTitleIgnoreCase(movieName);
		if (!movies.isPresent()) {

			JSONObject imdbResponse = null;
		log.info("Searching for " + "http://" + request.getServerName() + ":30046/imdb/search/" + movieName + "\n\n");
			String clientResponse = restTemplate
					.getForObject("http://10.10.0.11" /* request.getServerName()*/ + ":30046/imdb/search/" + movieName, String.class);

			try {
				log.info("Client Response :: " + clientResponse + "\n\n");
				imdbResponse = new JSONObject(clientResponse);
			} catch (JSONException ex) {
				throw new MovieNotFoundException("No movie found of the specified name");
			}

			movies = repo.findByTitleIgnoreCase(imdbResponse.getString("title"));

			if (movies.isPresent()) {
				model.addObject("movieTitle", movies.get().getTitle());
				model.addObject("movie", movies.get());
				return model;
			}

			model.addObject("movie",
					insertMovie(new Movie((long) (totalMovies + 1), imdbResponse.getString("title"),
							imdbResponse.getString("trailerLink"), imdbResponse.getString("year"),
							imdbResponse.getString("plot"), imdbResponse.getString("length"),
							imdbResponse.getString("rating"), imdbResponse.getString("ratingVotes"),
							imdbResponse.getString("imdbId"), imdbResponse.getString("poster"))));
			model.addObject("movieTitle", imdbResponse.getString("title").trim());

			return model;
		}

		model.addObject("movieTitle", movies.get().getTitle());
		model.addObject("movie", movies.get());

		return model;
	}

	@PostMapping
	public Movie insertMovie(@RequestBody Movie movie) {
		repo.save(movie);
		totalMovies++;
		return movie;
	}
}
