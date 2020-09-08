package com.microservices.movies.service;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "trailer_link")
	private String trailerLink;

	@Column(name = "year")
	private String year;

	@Column(name = "plot", length = Integer.MAX_VALUE)
	private String plot;

	@Column(name = "length")
	private String length;

	@Column(name = "rating")
	private String rating;

	@Column(name = "rating_votes")
	private String ratingVotes;

	@Column(name = "imdb_id")
	private String imdbId;

	@Column(name = "poster", length = Integer.MAX_VALUE)
	private String poster;

	public Movie() {
		super();
	}

	public Movie(Long id, String title, String trailerLink, String year, String plot, String length, String rating,
			String ratingVotes, String imdbId, String poster) {
		super();
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
