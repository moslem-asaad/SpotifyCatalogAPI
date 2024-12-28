package com.example.catalog.controller;

import com.example.catalog.model.Artist;
import com.example.catalog.utils.CatalogUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

import com.example.catalog.utils.SpotifyUtils;

@RestController
public class CatalogController {

    private final ObjectMapper objectMapper = new ObjectMapper();

   /* @GetMapping("/popularSongs")
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
    }*/

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

    @GetMapping("/popularSongs/filter")
    public ResponseEntity<?> filterSongsByNameOrPopularity(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") int minPopularity) throws IOException {
        ObjectNode errorResponse = objectMapper.createObjectNode();
        try {
            if (emptyPopularSongsHandler()) {
                errorResponse.put("message", "Service temporarily unavailable");
                errorResponse.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
            }
            ClassPathResource resource = new ClassPathResource("data/popular_songs.json");
            JsonNode allSongs = objectMapper.readTree(resource.getFile());
            List<JsonNode> songs = StreamSupport.stream(allSongs.spliterator(), false).toList();
            CatalogUtils catalogUtils = new CatalogUtils();
            if (name != null && !name.isEmpty()) {
                songs = songs.stream()
                        .filter(song -> song.get("name").asText().equalsIgnoreCase(name))
                        .toList();
            }
            songs = catalogUtils.filterSongsByPopularity(songs, minPopularity);
            return new ResponseEntity<>(songs, HttpStatus.OK);
        } catch (Exception e) {
            errorResponse.put("message", "Internal server error occurred");
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/songs/mostRecent")
    public  ResponseEntity<JsonNode> getMostRecentSong() throws IOException{
        ObjectNode errorResponse = objectMapper.createObjectNode();
        try {
            if (emptyPopularSongsHandler()) {
                errorResponse.put("message", "Service temporarily unavailable");
                errorResponse.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
            }
            ClassPathResource resource = new ClassPathResource("data/popular_songs.json");
            JsonNode allSongs = objectMapper.readTree(resource.getFile());
            List<JsonNode> songs = StreamSupport.stream(allSongs.spliterator(), false).toList();
            CatalogUtils catalogUtils = new CatalogUtils();
            JsonNode mostRecentSong = catalogUtils.getMostRecentSong(songs);
            return new ResponseEntity<>(mostRecentSong, HttpStatus.OK);
        }catch (Exception e) {
            errorResponse.put("message", "Internal server error occurred");
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/songs/longest")
    public  ResponseEntity<JsonNode> getLongestSong() throws IOException{
        ObjectNode errorResponse = objectMapper.createObjectNode();
        try {
            if (emptyPopularSongsHandler()) {
                errorResponse.put("message", "Service temporarily unavailable");
                errorResponse.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
            }
            ClassPathResource resource = new ClassPathResource("data/popular_songs.json");
            JsonNode allSongs = objectMapper.readTree(resource.getFile());
            List<JsonNode> songs = StreamSupport.stream(allSongs.spliterator(), false).toList();
            CatalogUtils catalogUtils = new CatalogUtils();
            JsonNode mostRecentSong = catalogUtils.getLongestSong(songs);
            return new ResponseEntity<>(mostRecentSong, HttpStatus.OK);
        }catch (Exception e) {
            errorResponse.put("message", "Internal server error occurred");
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/popularSongs")
    public ResponseEntity<?> getPopularSongs(@RequestParam(defaultValue = "0") int offset,
                                    @RequestParam(defaultValue = "-1") int limit) throws IOException {

        ObjectNode errorResponse = objectMapper.createObjectNode();
        try {
            if(emptyPopularSongsHandler()) {
                errorResponse.put("message", "Service temporarily unavailable");
                errorResponse.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
            }

            ClassPathResource resource = new ClassPathResource("data/popular_songs.json");
            JsonNode allSongs = objectMapper.readTree(resource.getFile());
            List<JsonNode> songs = StreamSupport.stream(allSongs.spliterator(), false).toList();
            int fromIndex = Math.min(offset, songs.size());
            int toIndex = (limit == -1) ? songs.size() : Math.min(offset + limit, songs.size());
            if (fromIndex > toIndex) {
                errorResponse.put("message", "Invalid offset or limit values");
                errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            List<JsonNode> paginatedSongs = songs.subList(fromIndex,toIndex);

            return new ResponseEntity<>(paginatedSongs, HttpStatus.OK);
        }catch (Exception e){
            errorResponse.put("message", "Internal server error occurred");
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<> (errorResponse,HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable String id) throws IOException {
        if (! SpotifyUtils.isValidId(id)) {
            return ResponseEntity.badRequest().build();
        }

        ClassPathResource resource = new ClassPathResource("data/popular_artists.json");
        JsonNode artists = objectMapper.readTree(resource.getFile());

        JsonNode artistNode = artists.get(id);
        if (artistNode == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return  ResponseEntity.ok(objectMapper.treeToValue(artistNode, Artist.class));
    }

}

