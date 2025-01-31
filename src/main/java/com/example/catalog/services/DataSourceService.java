package com.example.catalog.services;

import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Track;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface DataSourceService {
    Artist getArtistById(String id) throws IOException;
    List<Artist> getAllArtists()throws IOException;
    Artist addArtist(Artist artist) throws IOException;
    Artist updateArtist(Artist artist) throws IOException;
    boolean deleteArtistById(String id) throws IOException;

    List<Album> getAllAlbums() throws IOException;

    Album getAlbumById(String id) throws IOException;

    Album createAlbum(Album album) throws IOException;
    Album updateAlbum(Album album) throws IOException;

    boolean deleteAlbumById(String id) throws IOException;

    List<Track> getAlbumTracks(String id) throws IOException;

}
