package com.sba.awesome.maad.model;

import org.springframework.data.annotation.Id;

public class Artist {

	private String name;
	
	@Id
	private String id;
	private Integer apiId;
	private String apiUrl;
	private String thumb;

	public Artist(Integer apiId, String thumb, String title, String resource_url) {
		this.apiId = apiId;
		this.thumb = thumb;
		this.name = title;
		this.apiUrl = resource_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
