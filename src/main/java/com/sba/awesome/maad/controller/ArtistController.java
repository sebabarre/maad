package com.sba.awesome.maad.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sba.awesome.maad.api.MusicApiController;
import com.sba.awesome.maad.model.Album;
import com.sba.awesome.maad.model.Artist;
import com.sba.awesome.maad.repository.ArtistRepository;

@CrossOrigin(origins = "*")
@RestController
public class ArtistController {
	
	@Autowired
	private MusicApiController musicApiController;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@RequestMapping("/artist")
	@GET
	public List<Artist> searchArtist(@RequestParam(value="name") String name) {
		return musicApiController.findArtists(name);
	}
	
	@RequestMapping("/artist/all")
	public List<Artist> getAllFollowedArtist() {
		return artistRepository.findAll();
	}

	@RequestMapping("/artist/albums")
	public List<Album> searchAlbumsFromArtist(@RequestParam(value="idArtist") Integer idArtist) {
		Artist artist = new Artist(idArtist, null, null, null);
		return musicApiController.findAlbumFromArtist(artist);
	}
	
	@RequestMapping("/artist/add")
	public Artist addArtist(@RequestParam(value="idArtist") Integer idArtist) {
		System.out.println(idArtist);
		Artist artist = musicApiController.findArtist(idArtist);
		artistRepository.save(artist);
		return null;
	}
}
