package com.sba.awesome.maad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sba.awesome.maad.api.MusicApiController;
import com.sba.awesome.maad.model.Album;
import com.sba.awesome.maad.model.Artist;

@RestController
public class ArtistController {
	
	@Autowired
	private MusicApiController musicApiController;
	
	@RequestMapping("/artist")
	public List<Artist> searchArtist(@RequestParam(value="name") String name) {
		return musicApiController.findArtists(name);
	}
	
	@RequestMapping("/artist/albums")
	public List<Album> searchAlbumsFromArtist(@RequestParam(value="artist") Artist artist) {
		return musicApiController.findAlbumFromArtist(artist);
	}

}
