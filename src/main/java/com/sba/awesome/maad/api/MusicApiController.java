package com.sba.awesome.maad.api;

import java.util.List;

import com.sba.awesome.maad.model.Album;
import com.sba.awesome.maad.model.Artist;

public interface MusicApiController {
	
	public List<Artist> findArtists(String input);
	
	public List<Album> findAlbumFromArtist(Artist artist);

}
