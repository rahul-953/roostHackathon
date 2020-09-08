package com.microservices.imdb.movieservice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MyRestClient {

	private static final String BASE_URL = "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/";
	private static final String API_KEY = "fe5f3fb894msheff771c5fe382afp15809ajsnd7ebcebb65ca";
	private static final String SEARCH_URL = BASE_URL + "search/";
	private static final String FILM_URL = BASE_URL + "film/";

	public HttpResponse<JsonNode> search(String param) {

		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get(SEARCH_URL + param)
					.header("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
					.header("x-rapidapi-key", API_KEY).asJson();

		} catch (UnirestException e) {
			System.out.println("======>" + e.getStackTrace());
		}

		return response;
	}

	public HttpResponse<JsonNode> film(String param) {

		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get(FILM_URL + param)
					.header("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
					.header("x-rapidapi-key", API_KEY).asJson();

		} catch (UnirestException e) {
			System.out.println("======>" + e.getStackTrace());
		}

		return response;
	}
}
