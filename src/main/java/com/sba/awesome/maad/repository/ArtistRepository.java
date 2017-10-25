package com.sba.awesome.maad.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sba.awesome.maad.model.Artist;

public interface ArtistRepository extends MongoRepository<Artist, String> {

}
