package com.example.catalog.services;

import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Song;
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

    List<Song> getAllSongs() throws IOException;

    Song getSongById(String id) throws IOException;

    Song createSong(Song song) throws IOException;

    Song updateSong(Song song) throws IOException;

    boolean deleteSongById(String id) throws IOException;
}
