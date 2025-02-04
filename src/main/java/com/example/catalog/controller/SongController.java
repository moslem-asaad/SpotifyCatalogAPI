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
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private DataSourceService dataSourceService;
    @Autowired
    public SongController(DataSourceService dataSourceService){
        this.dataSourceService = dataSourceService;
    }
    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() throws IOException {
        List<Song> songs = dataSourceService.getAllSongs();
        if (songs == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (songs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(songs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable String id) throws IOException{
        Song song = dataSourceService.getSongById(id);
        if (song == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(song);
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) throws IOException{
        Song createdSong = dataSourceService.createSong(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSong);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSongById(@PathVariable String id, @RequestBody Song updatedSong) throws IOException{
        Song currSong = dataSourceService.getSongById(id);
        updatedSong.setId(id);
        Song savedSong = dataSourceService.updateSong(updatedSong);
        return ResponseEntity.ok(savedSong);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSongById(@PathVariable String id) throws IOException{
        boolean isDeleted = dataSourceService.deleteSongById(id);
        if (!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
