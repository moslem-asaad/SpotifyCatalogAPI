package com.example.catalog.controller;

import com.example.catalog.model.Artist;
import com.example.catalog.services.DataSourceService;
import com.example.catalog.utils.SpotifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
        if (artistList == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (artistList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artistList);
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) throws IOException{
        if (!SpotifyUtils.isValidId(artist.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Artist createdArtist = dataSourceService.addArtist(artist);
        if (createdArtist == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtistById(@PathVariable String id, @RequestBody Artist updatedArtist) throws IOException{
        if (!SpotifyUtils.isValidId(id)){
            return ResponseEntity.badRequest().build();
        }
        Artist currArtist = dataSourceService.getArtistById(id);
        if (currArtist == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        updatedArtist.setId(id);
        Artist savedArtist = dataSourceService.updateArtist(updatedArtist);
        if (savedArtist == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(savedArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable String id) throws IOException{
        if (!SpotifyUtils.isValidId(id)) {
            return ResponseEntity.badRequest().build();
        }
        boolean isDeleted = dataSourceService.deleteArtistById(id);
        if (!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
