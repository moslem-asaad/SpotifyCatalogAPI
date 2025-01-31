package com.example.catalog.services;

import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Track;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SpotifyAPIDataSources implements DataSourceService{
    @Override
    public Artist getArtistById(String id) throws IOException {
        return null;
    }

    @Override
    public List<Artist> getAllArtists() throws IOException {
        return null;
    }

    @Override
    public Artist addArtist(Artist artist) throws IOException {
        return null;
    }

    @Override
    public Artist updateArtist(Artist artist) throws IOException {
        return null;
    }

    @Override
    public boolean deleteArtistById(String id) throws IOException {
        return false;
    }

    @Override
    public List<Album> getAllAlbums() throws IOException {
        return null;
    }

    @Override
    public Album getAlbumById(String id) throws IOException {
        return null;
    }

    @Override
    public Album createAlbum(Album album) throws IOException {
        return null;
    }

    @Override
    public Album updateAlbum(Album album) throws IOException {
        return null;
    }

    @Override
    public boolean deleteAlbumById(String id) throws IOException {
        return false;
    }

    @Override
    public List<Track> getAlbumTracks(String id) throws IOException {
        return null;
    }
}
