package com.sba.awesome.maad.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Artist {

	private String title;
	
	@Id
	private String id;
	private Integer apiId;
	private String resource_url;
	private String thumb;
	
	private List<Album> listAlbum;

	public Artist(Integer apiId, String thumb, String title, String resource_url) {
		this.apiId = apiId;
		this.thumb = thumb;
		this.title = title;
		this.resource_url = resource_url;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getApiId() {
		return apiId;
	}

	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}

	public String getApiUrl() {
		return resource_url;
	}

	public void setApiUrl(String apiUrl) {
		this.resource_url = apiUrl;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Override
	public String toString() {
		return title;
	}

	public List<Album> getListAlbum() {
		return listAlbum;
	}

	public void setListAlbum(List<Album> listAlbum) {
		this.listAlbum = listAlbum;
	}
	
	
}
