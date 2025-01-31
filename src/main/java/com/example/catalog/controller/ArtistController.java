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
        if (!SpotifyUtils.isValidId(id)) {
            return ResponseEntity.badRequest().build();
        }

        Artist artist = dataSourceService.getArtistById(id);
        if (artist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(artist);
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() throws IOException {
        List<Artist> artistList = dataSourceService.getAllArtists();
        if (artistList == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (artistList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artistList);
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) throws IOException {
        if (!SpotifyUtils.isValidId(artist.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Artist createdArtist = dataSourceService.addArtist(artist);
        if (createdArtist == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtistById(@PathVariable String id, @RequestBody Artist updatedArtist) throws IOException {
        if (!SpotifyUtils.isValidId(id)) {
            return ResponseEntity.badRequest().build();
        }
        Artist currArtist = dataSourceService.getArtistById(id);
        if (currArtist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        updatedArtist.setId(id);
        Artist savedArtist = dataSourceService.updateArtist(updatedArtist);
        if (savedArtist == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(savedArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable String id) throws IOException {
        if (!SpotifyUtils.isValidId(id)) {
            return ResponseEntity.badRequest().build();
        }
        boolean isDeleted = dataSourceService.deleteArtistById(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<List<Album>> getAlbumsByArtistId(@PathVariable String id) throws IOException {
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
