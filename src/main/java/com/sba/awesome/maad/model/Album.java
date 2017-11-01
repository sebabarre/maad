package com.sba.awesome.maad.model;

import org.springframework.data.annotation.Id;

public class Album {

	@Id
	private String id;
	private String name;
	private Integer apiId;
	private String apiUrl;
	private String thumb;
	private Integer year;
	
	public Album(String id, String name, Integer apiId, String apiUrl, String thumb, Integer year) {
		this.id = id;
		this.name = name;
		this.apiId = apiId;
		this.apiUrl = apiUrl;
		this.thumb = thumb;
		this.year = year;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
