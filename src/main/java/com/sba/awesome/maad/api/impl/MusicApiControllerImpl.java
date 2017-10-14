package com.sba.awesome.maad.api.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sba.awesome.maad.api.MusicApiController;
import com.sba.awesome.maad.model.Artist;

@Service
public class MusicApiControllerImpl implements MusicApiController {
	
	private final String API_CALL = "https://api.discogs.com";
	private final String SEARCH_ARTIST_REQUEST = "/database/search?q=%s&type=artist";
	private final String DISCOGS_KEY="pgELpzUFGCUBfKpoxAag";
	private final String DISCOGS_SECRET="LUjMzukloTmBuqHTUuxwxjSJOWGLmZzJ";
	private RestTemplate restTemplate;
	private String currentRequest;
	
	public MusicApiControllerImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public List<Artist> findArtists(String input) {
		createRequest(SEARCH_ARTIST_REQUEST, input);
		
		ResponseEntity<String> response = restTemplate.getForEntity(currentRequest, String.class);
		String responseStr = response.getBody();
		Gson gson = new GsonBuilder().create();
		Reponse reponse = gson.fromJson(responseStr , Reponse.class);
		
		return null;
	}

	private void createRequest(String request, String ... inputs) {
		StringBuilder builder = new StringBuilder(API_CALL);
		builder.append(String.format(request, inputs));
		builder.append("&key=" + DISCOGS_KEY + "&secret=" + DISCOGS_SECRET);
		this.currentRequest = builder.toString();
	}
	
	static class Pagination implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4750853052926620378L;
		private Integer per_page;
		private Integer items;
		public Integer getPer_page() {
			return per_page;
		}
		public void setPer_page(Integer per_page) {
			this.per_page = per_page;
		}
		public Integer getItems() {
			return items;
		}
		public void setItems(Integer items) {
			this.items = items;
		}
		public Integer getPage() {
			return page;
		}
		public void setPage(Integer page) {
			this.page = page;
		}
		public Url getUrls() {
			return urls;
		}
		public void setUrls(Url urls) {
			this.urls = urls;
		}
		private Integer page;
		private Url urls;
	}
	
	static class Url implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4698463104851154787L;
		private String last;
		public String getLast() {
			return last;
		}
		public void setLast(String last) {
			this.last = last;
		}
		private String next;
		public String getNext() {
			return next;
		}
		public void setNext(String next) {
			this.next = next;
		}
	}
	
	static class Resultat implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -174396032097837256L;
		private String thumb;
		public String getThumb() {
			return thumb;
		}
		public void setThumb(String thumb) {
			this.thumb = thumb;
		}
		private String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		public String getResource_url() {
			return resource_url;
		}
		public void setResource_url(String resource_url) {
			this.resource_url = resource_url;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		private String uri;
		private String resource_url;
		private String type;
		private Integer id;
	}
	
	static class Reponse implements Serializable{
		
		private static final long serialVersionUID = -6337490548493026507L;
		private Pagination pagination;
		public Pagination getPagination() {
			return pagination;
		}
		public void setPagination(Pagination pagination) {
			this.pagination = pagination;
		}
		private Resultat[] results;
		
		public Resultat[] getResults() {
			return results;
		}
		public void setResults(Resultat[] results) {
			this.results = results;
		}
	}

}
