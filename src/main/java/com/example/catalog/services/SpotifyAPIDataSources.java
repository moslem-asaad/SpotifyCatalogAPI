package com.example.catalog.services;

import com.example.catalog.exceptions.InvalidIdException;
import com.example.catalog.exceptions.MissingDataException;
import com.example.catalog.exceptions.ResourceNotFoundException;
import com.example.catalog.exceptions.UnsupportedOperationException;
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
        isValidId(id);
        String accessToken = isValidToken();
        String url = "https://api.spotify.com/v1/artists/" + id;
        ResponseEntity<Map> response = callAPI(accessToken,url,HttpMethod.GET);
        if (response.getBody() == null) {
            throw new ResourceNotFoundException("Artist with ID " + id + " not found.");
        }
        Map<String, Object> artistData = response.getBody();
        return getArtist(artistData);
    }



    @Override
    public List<Artist> getAllArtists() throws IOException {
        String accessToken = isValidToken();
        String artists_ids = "1Xyo4u8uXC1ZmMpatF05PJ,6eUKZXaKkcviH0Ku9w2n3V,4GNC7GD6oZMSxPGyXy4MNB";
        String url = "https://api.spotify.com/v1/artists?ids="+artists_ids;
        ResponseEntity<Map> response = callAPI(accessToken,url,HttpMethod.GET);
        List<Artist> artists = new ArrayList<>();
        if (response.getBody() == null) {
            throw new ResourceNotFoundException("Artists not found.");
        }
        if (response.getBody() != null && response.getBody().containsKey("artists")) {
            List<Map<String, Object>> artistsList = (List<Map<String, Object>>) response.getBody().get("artists");
            for (Map<String, Object> artistData : artistsList) {
                artists.add(getArtist(artistData));
            }
        }
        return artists;
    }

    @Override
    public Artist addArtist(Artist artist) throws IOException {
        throw new UnsupportedOperationException("Create Artist Not supported on real spotify app");
    }

    @Override
    public Artist updateArtist(Artist artist) throws IOException {
        throw new UnsupportedOperationException("Update Artist Not supported on real spotify app");
    }

    @Override
    public boolean deleteArtistById(String id) throws IOException {
        throw new UnsupportedOperationException("Delete Artist Not supported on real spotify app");
    }

    @Override
    public List<Album> getAllAlbums() throws IOException {
        String accessToken = isValidToken();
        String albums_ids = "4yP0hdKOZPNshxUOjY0cZj,3T4tUhGYeRNVUGevb0wThu,5658aM19fA3JVwTK6eQX70";
        String url = "https://api.spotify.com/v1/albums?ids="+albums_ids;
        ResponseEntity<Map> response = callAPI(accessToken,url,HttpMethod.GET);
        List<Album> albums = new ArrayList<>();
        if (response.getBody() == null) {
            throw new ResourceNotFoundException("Albums not found.");
        }
        if (response.getBody() != null && response.getBody().containsKey("albums")) {
            List<Map<String, Object>> albumsList = (List<Map<String, Object>>) response.getBody().get("albums");
            for (Map<String, Object> albumData : albumsList) {
                albums.add(getAlbum(albumData));
            }
        }
        return albums;
    }

    @Override
    public Album getAlbumById(String id) throws IOException {
        isValidId(id);
        String accessToken = isValidToken();
        String url = "https://api.spotify.com/v1/albums/" + id;
        ResponseEntity<Map> response = callAPI(accessToken,url,HttpMethod.GET);
        if (response.getBody() == null) {
            throw new ResourceNotFoundException("Album with ID " + id + " not found.");
        }
        Map<String, Object> albumData = response.getBody();
        return getAlbum(albumData);
    }

    @Override
    public Album createAlbum(Album album) throws IOException {
        throw new UnsupportedOperationException("Create Album Not supported on real spotify app");
    }

    @Override
    public Album updateAlbum(Album album) throws IOException {
        throw new UnsupportedOperationException("Update Album Not supported on real spotify app");
    }

    @Override
    public void addNewTrackToAlbum() {
        throw new UnsupportedOperationException("Add new track to Not supported on real spotify app");
    }

    @Override
    public void updateTrackInAlbum() {
        throw new UnsupportedOperationException("Update track in Album Not supported on real spotify app");
    }

    @Override
    public void deleteTrackFromAlbum(String album_id,String track_id) {
        throw new UnsupportedOperationException("Delete track from an Album Not supported on real spotify app");
    }

    @Override
    public boolean deleteAlbumById(String id) throws IOException {
        throw new UnsupportedOperationException("Delete Album Not supported on real spotify app");
    }

    @Override
    public List<Track> getAlbumTracks(String id) throws IOException {
        isValidId(id);
        String accessToken = isValidToken();
        String url = "https://api.spotify.com/v1/albums/" + id + "/tracks";
        List<Track> trackList = new ArrayList<>();
        ResponseEntity<Map> response = callAPI(accessToken,url,HttpMethod.GET);

        if (response.getBody() == null || !response.getBody().containsKey("items")) {
            return trackList;
        }
        List<Map<String, Object>> trackItems = (List<Map<String, Object>>) response.getBody().get("items");
        trackList = extractTracksHelper(trackItems);
        return trackList;
    }

    @Override
    public List<Song> getAllSongs() throws IOException {
        String accessToken = isValidToken();
        String trackIds = "0VjIjW4GlUZAMYd2vXMi3b,7qiZfU4dY1lWllzX7mPBI3,7qEHsqek33rTcFNT9PFqLf,4Dvkj6JhhA12EX05fT7y2e,3KkXRkHbMCARz0aVfEt68P,5aAx2yezTd8zXrkmtKl66Z,1xznGGDReH1oQq0xzbwXa3,5PjdY0CKGZdEuoNab3yDmX,2QjOHCTQ1Jl3zawyYOpxh6,0CcQNd8CINkwQfe1RDtGV6,6CDzDgIUqeDY5g8ujExx2f,2XU0oxnq2qxCpomAAuJY8K,0tgVpDi06FyKpA1z0VMD4v,7wGoVu4Dady5GV0Sv4UIsx,5G4uNOOs5ZyVicj6vHl6KY,0u2P5u6lvoDfwTYjAADbn4,5uCax9HTNlzGybIStD3vDh,2NQzj8czPzUw9ufCOvAT8W,7BKLCZ1jbUBVqRi2FVlTVw,6UelLqGlWMcVH1E5c4H7lY,0TK2YIli7K1leLovkQiNik,6RUKPb4LETWmmr3iAEQktW,6EIsrLeTHnX1fNU2Ci36RL,7yq4Qj7cqayVTp3FF9CWbm,3PfIrDoz19wz7qK7tYeu62,2EiGECydkS2M8OCcRHQZhT,3JvKfv6T31zO0ini8iNItO,5edBgVtRD0fvWk140Sl21T,1Bk4mFK1shZUoHfYJMwqWp,1HNkqx9Ahdgi1Ixy2xkKkL,6krwVrqihThMtvID5LlaYj,285pBltuF7vW8TeWk8hdRR,21jGcNKet2qwijlDFuPiPb,2snvV5XQw30jstCYlr1W5o,6T8cJz5lAqGer9GUHGyelE,4u7EnebtmKWzUH433cf5Qv,2Fxmhks0bxGSBdJ92vM42m,34gCuhDGsG4bRPIf9bb02f,3bidbhpOYeV4knp8AIu8Xn,3AMftFgLBMpeMnRZZ0hTF4,3AJwUDP919kvQ9QcozQPxg,6gBFPUFcJLzWGx4lenP6h2,2VxeLyX666F8uXCJ0dZF8B,4iwLDA6XdWfkQnCzyqNjKq,1BxfuPKGuaTgP7aM0Bbdwr,3hB5DgAiMAQ4DzYbsMq1IT,5XeFesFbtLpXzIVDNQP22n,0tKcYR2II1VCQWT79i5NrW,3U4isOIWM3VvDubwSI3y7a,3hRV0jL3vUpRrcy398teAU,3eoan9A3pDSzDZzDzh0Xrr,2Ch7LmS7r2Gy2kc64wv3Bz,6g1NlCpW7fgqDnWbCCDrHl,2Oehrcv4Kov0SuIgWyQY9e,25khomWgBVamSdKw7hzm3l,23ZFWDVtZgdKY2s509MR6Z,3CRDbSIZ4r5MsZ0YwxuEkn,1mea3bSkSGXuIRvnydlB5b,68Dni7IE4VyPkTOH9mRWHr,3JN0Zx6KfsqtMnTyvI0iDX,7ujx3NYtwO2LkmKGz59mXp,7DFNE7NO0raLIUbgzY2rzm,6ocbgoVGwYJhOv1GgI9NsF,003vvx7Niy0yvhvHt4a68B,7lQ8MOhq6IN2w8EYcFNSUk,7MJQ9Nfxzh8LPZ9e9u68Fq,0B7wvvmu9EISAwZnOpjhNI,5FVd6KXrgO9B3JPmC8OPst,47Slg6LuqLaX0VodpSCvPt,5wANPM4fQCJwkGd4rN57mH,4c9ihNMojL2wOGglzQ2aCG,7ef4DlsgrMEH11cDZd32M6,48DKpTEVJ2pAjxQbWTad3q,4ZtFanR9U6ndgddUvNcjcG,3ee8Jmje8o58CHK66QrVC2,0lYBSQXN6rCTvUZvg9S0lU,3B54sVLJ402zGa6Xm4YGNe,7DSAEUvxU8FajXtRloy8M0,60a0Rd6pjrkxjPbaKzXjfq,7m9OqQk4RVRkw9JJdeAw96,1JSTJqkT5qHq8MDJnJbRE1,0KKkJNfGyhkQ5aFogxQAPU,2dpaYNEQHiRxtZbfNsse99,5g7sDjBhZ4I3gcFIpkrLuI,7GX5flRQZVHRAGd6B4TmDO,2xLMifQCjDGFmkHkpNLD9h,69bp2EbF7Q2rqc5N3ylezZ,4WjH9Bzt3kx7z8kl0awxh4,2ekn2ttSfGqwhhate0LSR0,2tfZPbEIwU2TdlRdtCiFoF,7JJmb5XwzOO8jgpou264Ml,463CkQjx2Zk1yXoBuierM9,4CeeEOM32jQcH3eN9Q2dGj,6zSpb8dQRaw0M1dK8PBwQz,2yRiJCQXmFu5gqY00gmQhQ,1mXVgsBdtIVeCLJnSnmtdV,2whoe0pPKh4JRn6eWSIqTV,6QkEV7Krf7WV2UdILlgN4Z,3OHfY25tqY28d16oZczHc8,1rfofaqEpACxVEHIZBJe6W";
        String url = "https://api.spotify.com/v1/tracks?ids=" + trackIds;
        ResponseEntity<Map> response = callAPI(accessToken,url,HttpMethod.GET);
        List<Song> songList = new ArrayList<>();
        if (response.getBody() != null && response.getBody().containsKey("tracks")) {
            List<Map<String, Object>> tracksList = (List<Map<String, Object>>) response.getBody().get("tracks");
            for (Map<String, Object> trackData : tracksList) {
                songList.add(getSong(trackData));
            }
        }
        return songList;
    }

    @Override
    public Song getSongById(String id) throws IOException {
        String accessToken = isValidToken();
        String url = "https://api.spotify.com/v1/tracks/" + id;
        ResponseEntity<Map> response = callAPI(accessToken,url,HttpMethod.GET);
        if (response.getBody() == null) {
            throw new ResourceNotFoundException("Song with ID " + id + " not found.");
        }
        Map<String, Object> songData = response.getBody();
        return getSong(songData);
    }

    @Override
    public Song createSong(Song song) throws IOException {
        throw new UnsupportedOperationException("Create Song Not supported on real spotify app");
    }

    @Override
    public Song updateSong(Song song) throws IOException {
        throw new UnsupportedOperationException("Update Song Not supported on real spotify app");
    }

    @Override
    public boolean deleteSongById(String id) throws IOException {
        throw new UnsupportedOperationException("Delete Song Not supported on real spotify app");
    }




    private Song getSong(Map<String, Object> trackData){
        Song song = new Song();
        song.setId((String) trackData.get("id"));
        song.setName((String) trackData.get("name"));
        song.setDuration_ms((Integer) trackData.get("duration_ms"));
        song.setUri((String) trackData.get("uri"));
        song.setPopularity((Integer) trackData.get("popularity"));
        if (trackData.containsKey("album")){
            song.setAlbum(getAlbum((Map<String, Object>) trackData.get("album")));
        }
        if (trackData.containsKey("artists")) {
            List<Artist> artists = new ArrayList<>();
            List<Map<String, Object>> artistsList = (List<Map<String, Object>>) trackData.get("artists");
            for (Map<String, Object> artistData : artistsList) {
                Artist artist = new Artist();
                artist.setId((String) artistData.get("id"));
                artist.setName((String) artistData.get("name"));
                artist.setUri((String) artistData.get("uri"));
                artists.add(artist);
            }
            song.setArtists(artists);
        }
        return song;
    }

    private void isValidId(String id){
        if (id == null || id.isEmpty()) {
            throw new InvalidIdException("Artist ID cannot be null or empty.");
        }
    }

    private String isValidToken() throws IOException {
        String accessToken = spotifyAuthService.getAccessToken();
        if (accessToken == null) {
            throw new IOException("Failed to retrieve Spotify access token.");
        }
        return accessToken;
    }

    private ResponseEntity<Map> callAPI(String accessToken,String url,HttpMethod method){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, method, entity, Map.class);
    }
    private ResponseEntity<Void> callAPI2(String accessToken,String url,HttpMethod method){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, method, entity, Void.class);
    }

    private List<Image> extractImages(Map<String, Object> data){
        if (data.containsKey("images")) {
            List<Image> images = new ArrayList<>();
            List<Map<String, Object>> imagesList = (List<Map<String, Object>>) data.get("images");
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
            trackList = extractTracksHelper(trackItems);
            return trackList;
        }
        return null;
    }

    private List<Track> extractTracksHelper(List<Map<String, Object>> trackItems){
        List<Track> trackList = new ArrayList<>();
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

    private Album getAlbum(Map<String, Object> albumData){
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

    private Artist getArtist(Map<String, Object> artistData){
        Artist artist = new Artist();
        artist.setId((String) artistData.get("id"));
        artist.setName((String) artistData.get("name"));
        artist.setPopularity((Integer) artistData.get("popularity"));
        artist.setFollowers(((Map<String, Integer>) artistData.get("followers")).get("total"));
        artist.setUri((String) artistData.get("uri"));
        if (artistData.containsKey("genres")) {
            artist.setGenres((List<String>) artistData.get("genres"));
        }
        artist.setImages(extractImages(artistData));
        return artist;
    }

}
