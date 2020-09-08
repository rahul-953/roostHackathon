package com.microservices.imdb.movieservice;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

@SpringBootApplication
@RestController
public class ImdbMoviesController {

	final Logger log = LoggerFactory.getLogger(ImdbMoviesController.class);

	@GetMapping("/imdb/search/{movieName}")
	public IMDBMovie getImdbMovie(@PathVariable String movieName) throws Exception {

		log.info("Searching movie '{}'", movieName.toUpperCase());

		MyRestClient client = new MyRestClient();
		HttpResponse<JsonNode> response = client.search(movieName);
		JsonNode node = response.getBody();

		String id = getImdbTitleId(node);
		response = client.film(id);

		JSONObject obj = response.getBody().getObject();
		//log.info(obj.toString());
		log.info("Before '" + obj.getString("title") + "'");
		String movieTitle = obj.getString("title").substring(0,obj.getString("title").length()-1);
		log.info("After '" + movieTitle + "'");
		return new IMDBMovie(movieTitle, obj.getJSONObject("trailer").getString("link"),
				obj.getString("year"), obj.getString("plot"), obj.getString("length"), obj.getString("rating"),
				obj.getString("rating_votes"), obj.getString("id"), obj.getString("poster"));
	}

	private String getImdbTitleId(JsonNode node) throws Exception {

		try {
			return node.getObject().getJSONArray("titles").getJSONObject(0).getString("id");
		} catch (Exception ex) {
			log.error("INTERNAL_SERVER_ERROR {}", ex.getCause());
			throw new Exception("INTERNAL_SERVER_ERROR in searching movie.....");
		}
	}
}
