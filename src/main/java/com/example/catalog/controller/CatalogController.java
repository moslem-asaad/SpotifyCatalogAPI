package com.example.catalog.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import com.example.catalog.utils.SpotifyUtils;

@RestController
public class CatalogController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/popularSongs")
    public ResponseEntity<JsonNode> getPopularSongs() throws IOException {
        ObjectNode errorResponse = objectMapper.createObjectNode();
        try {
            if(emptyPopularSongsHandler()) {
                errorResponse.put("message", "Service temporarily unavailable");
                errorResponse.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
            }

            ClassPathResource resource = new ClassPathResource("data/popular_songs.json");
            return new ResponseEntity<>(objectMapper.readTree(resource.getFile()), HttpStatus.OK);
        }catch (Exception e){
            errorResponse.put("message", "Internal server error occurred");
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<> (errorResponse,HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/popularArtists")
    public ResponseEntity<JsonNode> getPopularArtists() throws IOException {
        ObjectNode errorResponse = objectMapper.createObjectNode();
        try {
            if(emptyPopularSongsHandler()) {
                errorResponse.put("message", "Service temporarily unavailable");
                errorResponse.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
            }

            ClassPathResource resource = new ClassPathResource("data/popular_artists.json");
            return new ResponseEntity<>(objectMapper.readTree(resource.getFile()), HttpStatus.OK);
        }catch (Exception e){
            errorResponse.put("message", "Internal server error occurred");
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<> (errorResponse,HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<JsonNode> getAlbumById(@PathVariable String id) throws IOException {
        ObjectNode errorResponse = objectMapper.createObjectNode();
        try {
            // Handle 400: Invalid ID
            if (!SpotifyUtils.isValidId(id)) {
                errorResponse.put("message", "Invalid album ID provided.");
                errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<> (errorResponse,HttpStatus.BAD_REQUEST );
            }
            // Handle 403: Forbidden ID (all zeros)
            if (id.matches("0+")) {
                errorResponse.put("message", "Forbidden album id");
                errorResponse.put("status", HttpStatus.FORBIDDEN.value());
                return new ResponseEntity<> (errorResponse,HttpStatus.FORBIDDEN );
            }

            if(emptyPopularSongsHandler()) {
                errorResponse.put("message", "Service temporarily unavailable");
                errorResponse.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
                return new ResponseEntity<> (errorResponse,HttpStatus.SERVICE_UNAVAILABLE );
            }

            ClassPathResource resource = new ClassPathResource("data/albums.json");
            JsonNode albums = objectMapper.readTree(resource.getFile());
            JsonNode album = albums.get(id);
            if (album != null) {
                return new ResponseEntity<>(album,HttpStatus.OK);
            } else {
                errorResponse.put("message", "Album with id " + id + " not found");
                errorResponse.put("status", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<> (errorResponse,HttpStatus.NOT_FOUND );
            }
        }
        catch (Exception e){
            errorResponse.put("message", "Internal server error occurred");
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<> (errorResponse,HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    private boolean emptyPopularSongsHandler() throws IOException {
        ClassPathResource popularSongResource = new ClassPathResource("data/popular_songs.json");
        JsonNode popularSongs = objectMapper.readTree(popularSongResource.getFile());
        return popularSongs.isEmpty();
    }

}