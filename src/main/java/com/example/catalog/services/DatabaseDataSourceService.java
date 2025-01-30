package com.example.catalog.services;

import com.example.catalog.model.Artist;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DatabaseDataSourceService implements DataSourceService{

    @Override
    public Artist getArtistById(String id) {
        return null;
        //return db.findById(id).orElse(null);
    }

    @Override
    public List<Artist> getAllArtists() throws IOException {
        return null;
    }

    @Override
    public Artist addArtist(Artist artist) throws IOException {
        return null;
    }
}
