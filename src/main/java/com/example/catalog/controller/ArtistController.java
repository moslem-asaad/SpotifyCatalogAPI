package com.example.catalog.controller;

import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Song;
import com.example.catalog.services.DataSourceService;
import com.example.catalog.utils.SpotifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final DataSourceService dataSourceService;

    @Autowired
    public ArtistController(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable String id) throws IOException {
        Artist artist = dataSourceService.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() throws IOException {
        List<Artist> artistList = dataSourceService.getAllArtists();
        if (artistList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artistList);
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) throws IOException {
        Artist createdArtist = dataSourceService.addArtist(artist);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtistById(@PathVariable String id, @RequestBody Artist updatedArtist) throws IOException {
        Artist currArtist = dataSourceService.getArtistById(id);
        updatedArtist.setId(id);
        Artist savedArtist = dataSourceService.updateArtist(updatedArtist);
        return ResponseEntity.ok(savedArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable String id) throws IOException {
        dataSourceService.deleteArtistById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<List<Album>> getAlbumsByArtistId(@PathVariable String id) throws IOException {
        dataSourceService.getArtistById(id);
        List<Song> allSongs = dataSourceService.getAllSongs();
        if (allSongs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Album> albums = new ArrayList<>();
        for (Song song : allSongs) {
            if (song.getArtists() != null) {
                for (Artist artist : song.getArtists()) {
                    if (artist.getId().equals(id) && song.getAlbum() != null) {
                        albums.add(song.getAlbum());
                    }
                }
            }
        }
        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getSongsByArtistId(@PathVariable String id) throws IOException {
        dataSourceService.getArtistById(id);
        List<Song> allSongs = dataSourceService.getAllSongs();
        if (allSongs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Song> songs = new ArrayList<>();
        for (Song song : allSongs) {
            if (song.getArtists() != null) {
                for (Artist artist : song.getArtists()) {
                    if (artist.getId().equals(id) ) {
                        songs.add(song);
                    }
                }
            }
        }
        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(songs);
    }
}
