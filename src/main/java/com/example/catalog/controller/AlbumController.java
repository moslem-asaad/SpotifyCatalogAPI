package com.example.catalog.controller;

import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Track;
import com.example.catalog.services.DataSourceService;
import com.example.catalog.utils.SpotifyUtils;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private DataSourceService dataSourceService;

    @Autowired
    public AlbumController(DataSourceService dataSourceService){
        this.dataSourceService = dataSourceService;
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() throws IOException {
        List<Album> albums = dataSourceService.getAllAlbums();
        if (albums.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable String id) throws IOException{
        Album album = dataSourceService.getAlbumById(id);
        return ResponseEntity.ok(album);
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) throws IOException{
        Album createdAlbum = dataSourceService.createAlbum(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbumById(@PathVariable String id, @RequestBody Album updatedAlbum) throws IOException{
        Album currAlbum = dataSourceService.getAlbumById(id);
        updatedAlbum.setId(id);
        Album savedAlbum = dataSourceService.updateAlbum(updatedAlbum);
        return ResponseEntity.ok(savedAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbumById(@PathVariable String id) throws IOException{
        boolean isDeleted = dataSourceService.deleteAlbumById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/tracks")
    public ResponseEntity<List<Track>> getAlbumTracks(@PathVariable String id) throws IOException{
        List<Track> tracks = dataSourceService.getAlbumTracks(id);
        if (tracks==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tracks);
    }

    @PostMapping("/{id}/tracks")
    public ResponseEntity<Album> addNewTrackToAlbum(@PathVariable String id,@RequestBody Track track) throws IOException{
        dataSourceService.addNewTrackToAlbum();
        Album album = dataSourceService.getAlbumById(id);
        Album newAlbum =  album.addTrack(track);
        if (newAlbum ==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        Album savedAlbum =  dataSourceService.updateAlbum(newAlbum);
        return ResponseEntity.ok(savedAlbum);
    }

    @PutMapping("/{id}/tracks/{track_id}")
    public ResponseEntity<Album> updateTrackInAlbum(@PathVariable String id,@RequestBody Track track) throws IOException {
        dataSourceService.updateTrackInAlbum();
        Album album = dataSourceService.getAlbumById(id);
        Album newAlbum =  album.updateTrack(track);
        if (newAlbum ==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        Album savedAlbum =  dataSourceService.updateAlbum(newAlbum);
        return ResponseEntity.ok(savedAlbum);
    }

    @DeleteMapping("/{id}/tracks/{track_id}")
    public ResponseEntity<Album> deleteTrackFromAlbum(@PathVariable String id,@PathVariable String track_id) throws IOException {
        dataSourceService.deleteTrackFromAlbum(id,track_id);
        Album album = dataSourceService.getAlbumById(id);
        Album newAlbum =  album.deleteTrack(track_id);
        if (newAlbum ==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        Album savedAlbum =  dataSourceService.updateAlbum(newAlbum);
        return ResponseEntity.ok(savedAlbum);
    }
}
