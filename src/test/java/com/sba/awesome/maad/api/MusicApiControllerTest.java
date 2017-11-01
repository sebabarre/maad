package com.sba.awesome.maad.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import com.sba.awesome.maad.api.impl.MusicApiControllerImpl;
import com.sba.awesome.maad.model.Album;
import com.sba.awesome.maad.model.Artist;

public class MusicApiControllerTest {
	
	private MusicApiController musicApiController;
	
	@Test
	public void shouldReturnSomethingForArtist() {
		musicApiController = (MusicApiController) new MusicApiControllerImpl(new RestTemplateBuilder());
		List<Artist> listArtist = musicApiController.findArtists("nirvana");
		assertNotNull(listArtist);
		assertTrue(listArtist.size() > 0);
	}
	
	@Test
	public void shouldReturnSomethingForAlbum() {
		musicApiController = (MusicApiController) new MusicApiControllerImpl(new RestTemplateBuilder());
		List<Artist> listArtist = musicApiController.findArtists("arcade fire");
		List<Album> listAlbum = musicApiController.findAlbumFromArtist(listArtist.get(0));
		assertNotNull(listAlbum);
		assertTrue(listAlbum.size() > 0);
	}
}
