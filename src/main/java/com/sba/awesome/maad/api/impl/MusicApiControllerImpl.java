package com.sba.awesome.maad.api.impl;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sba.awesome.maad.api.MusicApiController;
import com.sba.awesome.maad.model.Album;
import com.sba.awesome.maad.model.Artist;

@Service
public class MusicApiControllerImpl implements MusicApiController {
	
	private final String API_CALL = "https://api.discogs.com";
	private final String SEARCH_ARTIST_REQUEST = "/database/search?q=%s&type=artist&per_page=20";
	private final String SEARCH_ALBUMS_FROM_ARTIST_REQUEST = "/artists/%s/releases?per_page=500";
	private final String FIND_ARTIST_BY_ID = "/artists/%s";
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
		List<Artist> listArtist = extractMaadArtistFromReponse(reponse);
		return listArtist;
	}

	@Override
	public List<Album> findAlbumFromArtist(Artist artist) {
		createRequest(SEARCH_ALBUMS_FROM_ARTIST_REQUEST, String.valueOf(artist.getApiId()));
		ResponseEntity<String> response = restTemplate.getForEntity(currentRequest, String.class);
		String responseStr = response.getBody();
		Gson gson = new GsonBuilder().create();
		Reponse reponse = gson.fromJson(responseStr , Reponse.class);
		
		List<Album> listAlbum = extractMaadAlbumFromReponse(reponse);
		return listAlbum;
	}
	
	@Override
	public Artist findArtist(Integer idArtist) {
		createRequestWithoutKey(FIND_ARTIST_BY_ID, String.valueOf(idArtist));
		ResponseEntity<String> response = restTemplate.getForEntity(currentRequest, String.class);
		String responseStr = response.getBody();
		Gson gson = new GsonBuilder().create();
		ReponseArtist reponse = gson.fromJson(responseStr , ReponseArtist.class);
		Artist artist = new Artist(idArtist, null, reponse.getName(), reponse.getUri()); //TODO: add thumb
		artist.setListAlbum(findAlbumFromArtist(artist));
		return artist;
	}

	private List<Album> extractMaadAlbumFromReponse(Reponse reponse) {
		// Parcourir la liste des résultats de la réponse
		// N'extraire que les albums de type master et de role main
		List<Album> listAlbum = reponse.getReleases().stream()
				.filter(s -> s.getRole() != null)
				.filter(s -> s.getRole().equals("Main"))
				.filter(s -> s.getType() != null)
				.filter(s -> s.getType().equals("master"))
				.sorted((s1, s2) -> Integer.compare(s2.getYear() != null ? s2.getYear() : 0, s1.getYear() != null ? s1.getYear() : 0))
				.map(s -> new Album(null, s.getTitle(), s.getId(), s.getResource_url(), s.getThumb(), s.getYear()))
				.collect(Collectors.toList());
		return listAlbum;
	}

	private List<Artist> extractMaadArtistFromReponse(Reponse reponse) {
		//Parcourir la liste des resultats de la réponse
		//N'extraire que les artistes ayant un thumb
		List<Artist> listArtist = reponse.getResults().stream()
			.filter(s -> s.getThumb() != null)
			.filter(s -> s.getThumb().length() != 0)
			.map(s -> new Artist(s.getId(), s.getThumb(), s.getTitle(), s.getResource_url()))
			.collect(Collectors.toList());
		return listArtist;
	}

	private void createRequest(String request, String ... inputs) {
		StringBuilder builder = new StringBuilder(API_CALL);
		builder.append(String.format(request, inputs));
		builder.append("&key=" + DISCOGS_KEY + "&secret=" + DISCOGS_SECRET);
		this.currentRequest = builder.toString();
	}
	
	private void createRequestWithoutKey(String request, String ... inputs) {
		StringBuilder builder = new StringBuilder(API_CALL);
		builder.append(String.format(request, inputs));
		this.currentRequest = builder.toString();
	}
	
	static class ReponseArtist implements Serializable {
		private String uri;
		private String name;
		

		public String getUri() {
			return uri;
		}

		public void setUri(String releases_url) {
			this.uri = releases_url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
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
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
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
		private String title;
		private String uri;
		private String resource_url;
		private String type;
		private Integer id;
		private String role;
		private Integer year;

		public String getThumb() {
			return thumb;
		}
		public void setThumb(String thumb) {
			this.thumb = thumb;
		}
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
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public Integer getYear() {
			return year;
		}
		public void setYear(Integer year) {
			this.year = year;
		}
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
		private List<Resultat> results;
		
		public List<Resultat> getResults() {
			return results;
		}
		public void setResults(List<Resultat> results) {
			this.results = results;
		}
		
		private List<Resultat> releases;
		public List<Resultat> getReleases() {
			return releases;
		}
		public void setReleases(List<Resultat> releases) {
			this.releases = releases;
		}
	}
	
	static class Release implements Serializable {
		
		private static final long serialVersionUID = 4503223295027109327L;
		private String main_release;

		public String getMain_release() {
			return main_release;
		}

		public void setMain_release(String main_release) {
			this.main_release = main_release;
		}
	}

	
}
