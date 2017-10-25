package com.sba.awesome.maad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sba.awesome.maad.model.Artist;
import com.sba.awesome.maad.repository.ArtistRepository;

@SpringBootApplication
public class MaadApplication 
//implements CommandLineRunner 
{
	
	@Autowired
	ArtistRepository artistRepository;

	public static void main(String[] args) {
		SpringApplication.run(MaadApplication.class, args);
	}
	
//	@Override
// TODELETE WHEN TESTS ARE DONE
//	public void run(String... args) throws Exception {
//		
//		Artist artist = new Artist(1, "thumb", "title", "ressource");
//		artistRepository.save(artist);
//		System.out.println("TADA");
//	}
}
