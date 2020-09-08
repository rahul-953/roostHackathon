package com.microservices.imdb.movieservice;

public class IMDBMovie {

	private String title;
	private String trailerLink;
	private String year;
	private String plot;
	private String length;
	private String rating;
	private String ratingVotes;
	private String imdbId;
	private String poster;

	public IMDBMovie() {
		super();
	}

	public IMDBMovie(String title, String trailerLink, String year, String plot, String length, String rating,
			String ratingVotes, String imdbId, String poster) {
		super();
		this.title = title;
		this.trailerLink = trailerLink;
		this.year = year;
		this.plot = plot;
		this.length = length;
		this.rating = rating;
		this.ratingVotes = ratingVotes;
		this.imdbId = imdbId;
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTrailerLink() {
		return trailerLink;
	}

	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRatingVotes() {
		return ratingVotes;
	}

	public void setRatingVotes(String ratingVotes) {
		this.ratingVotes = ratingVotes;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

}
