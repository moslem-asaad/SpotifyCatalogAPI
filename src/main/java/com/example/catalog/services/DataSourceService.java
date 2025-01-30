package com.example.catalog.services;

import com.example.catalog.model.Artist;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface DataSourceService {
    Artist getArtistById(String id) throws IOException;
    List<Artist> getAllArtists()throws IOException;
    Artist addArtist(Artist artist) throws IOException;
}
