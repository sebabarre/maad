package com.sba.awesome.maad.api;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.Assert.*;

import com.sba.awesome.maad.api.impl.MusicApiControllerImpl;
import com.sba.awesome.maad.model.Artist;

public class MusicApiControllerTest {
	
	private MusicApiController musicApiController;
	
	@Test
	public void shouldReturnSomething() {
		musicApiController = (MusicApiController) new MusicApiControllerImpl(new RestTemplateBuilder());
		List<Artist> listArtist = musicApiController.findArtists("nirvana");
		assertNotNull(listArtist);
		assertTrue(listArtist.size() > 0);
	}
}
