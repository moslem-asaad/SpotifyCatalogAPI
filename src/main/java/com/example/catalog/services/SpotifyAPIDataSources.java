package com.example.catalog.services;

import com.example.catalog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SpotifyAPIDataSources implements DataSourceService{

    @Autowired
    private SpotifyAuthService spotifyAuthService;
    private final RestTemplate restTemplate = new RestTemplate();
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
        String accessToken = spotifyAuthService.getAccessToken();
        if (accessToken == null) {
            throw new IOException("Failed to retrieve Spotify access token.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String albums_ids = "4yP0hdKOZPNshxUOjY0cZj,3T4tUhGYeRNVUGevb0wThu,5658aM19fA3JVwTK6eQX70";
        String url = "https://api.spotify.com/v1/albums?ids="+albums_ids;
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        List<Album> albums = new ArrayList<>();
        if (response.getBody() != null && response.getBody().containsKey("albums")) {
            List<Map<String, Object>> albumsList = (List<Map<String, Object>>) response.getBody().get("albums");
            for (Map<String, Object> albumData : albumsList) {
                Album album = new Album();
                album.setId(albumData.get("id").toString());
                album.setName(albumData.get("name").toString());
                album.setUri(albumData.get("uri").toString());
                album.setRelease_date(albumData.get("release_date").toString());
                album.setTotal_tracks((Integer) albumData.get("total_tracks"));
                album.setImages(extractImages(albumData));
                album.setTracks(extractTracks(albumData));
                albums.add(album);
            }
        }
        return albums;
    }

    @Override
    public Album getAlbumById(String id) throws IOException {
        if (id == null || id.isEmpty()) {
            return null; // Invalid input
        }

        String accessToken = spotifyAuthService.getAccessToken();
        if (accessToken == null) {
            throw new IOException("Failed to retrieve Spotify access token.");
        }

        String url = "https://api.spotify.com/v1/albums/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url,HttpMethod.GET, entity, Map.class);
        if (response.getBody() == null) {
            return null;
        }
        Map<String, Object> albumData = response.getBody();
        Album album = new Album();
        album.setId(albumData.get("id").toString());
        album.setName(albumData.get("name").toString());
        album.setUri(albumData.get("uri").toString());
        album.setRelease_date(albumData.get("release_date").toString());
        album.setTotal_tracks((Integer) albumData.get("total_tracks"));
        album.setImages(extractImages(albumData));
        album.setTracks(extractTracks(albumData));
        return album;
    }

    private List<Image> extractImages(Map<String, Object> albumData){
        if (albumData.containsKey("images")) {
            List<Image> images = new ArrayList<>();
            List<Map<String, Object>> imagesList = (List<Map<String, Object>>) albumData.get("images");
            for (Map<String, Object> img : imagesList) {
                Image image = new Image();
                image.setUrl((String) img.get("url"));
                image.setHeight((Integer) img.get("height"));
                image.setWidth((Integer) img.get("width"));
                images.add(image);
            }
            return images;
        }
        return null;
    }

    private List<Track> extractTracks(Map<String, Object> albumData){
        if (albumData.containsKey("tracks")) {
            List<Track> trackList = new ArrayList<>();
            Map<String, Object> tracksData = (Map<String, Object>) albumData.get("tracks");
            List<Map<String, Object>> trackItems = (List<Map<String, Object>>) tracksData.get("items");
            for (Map<String, Object> trackData : trackItems) {
                Track track = new Track();
                track.setId((String) trackData.get("id"));
                track.setName((String) trackData.get("name"));
                track.setDuration_ms( (Integer) trackData.get("duration_ms"));
                track.setExplicit((Boolean) trackData.get("explicit"));
                track.setUri((String) trackData.get("uri"));
                trackList.add(track);
            }
            return trackList;
        }
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

    @Override
    public List<Song> getAllSongs() throws IOException {
        return null;
    }

    @Override
    public Song getSongById(String id) throws IOException {
        return null;
    }

    @Override
    public Song createSong(Song song) throws IOException {
        return null;
    }

    @Override
    public Song updateSong(Song song) throws IOException {
        return null;
    }

    @Override
    public boolean deleteSongById(String id) throws IOException {
        return false;
    }
}
