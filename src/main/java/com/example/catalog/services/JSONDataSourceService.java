package com.example.catalog.services;

import com.example.catalog.exceptions.*;
import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Song;
import com.example.catalog.model.Track;
import com.example.catalog.utils.SpotifyUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class JSONDataSourceService implements DataSourceService{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${artists.path}")
    private String artistPath;
    @Value("${albums.path}")
    private String albumPath;
    @Value("${songs.path}")
    private String songsPath;

    @Override
    public Artist getArtistById(String id) throws IOException {
        isValidId(id);
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null){
            throw new ResourceNotFoundException("Artists not found.");
        }
        JsonNode artistNode = artists.get(id);
        if (artistNode == null) {
            throw new ResourceNotFoundException("Artist with ID " + id + " not found.");
        }
        return objectMapper.treeToValue(artistNode, Artist.class);
    }

    @Override
    public List<Artist> getAllArtists() throws IOException {
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null){
            throw new ResourceNotFoundException("Artists not found.");
        }
        List<Artist> artistList = new ArrayList<>();
        Iterator<JsonNode> elements = artists.elements();
        while (elements.hasNext()) {
            artistList.add(objectMapper.treeToValue(elements.next(), Artist.class));
        }
        return artistList;
    }

    @Override
    public Artist addArtist(Artist artist) throws IOException{
        if (artist == null || artist.getId() == null || artist.getId().isEmpty()) {
            throw new MissingDataException("Missing required fields");
        }
        isValidId(artist.getId());
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null) {
            artists = objectMapper.createObjectNode();
        }
        ObjectNode artistsObject = (ObjectNode) artists;
        if (artistsObject.has(artist.getId())){
            throw new ItemExistsException("Artist Already Exists");
        }

        artistsObject.set(artist.getId(),objectMapper.valueToTree(artist));

        saveJsonData(artistsObject,artistPath);
        return artist;

    }

    @Override
    public Artist updateArtist(Artist artist) throws IOException {
        if (artist == null || artist.getId() == null || artist.getId().isEmpty()) {
            throw new MissingDataException("Missing required fields");
        }
        isValidId(artist.getId());
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null){
            throw new ResourceNotFoundException("Artists not found.");
        }
        ObjectNode artistsObject = (ObjectNode) artists;
        if (!artistsObject.has(artist.getId())){
            throw new ItemExistsException("Artist Doesn't Exists");
        }

        artistsObject.set(artist.getId(),objectMapper.valueToTree(artist));
        saveJsonData(artistsObject,artistPath);
        return artist;
    }

    @Override
    public boolean deleteArtistById(String id) throws IOException {
        JsonNode artists = loadJsonData(artistPath);
        isValidId(id);
        if (artists == null || !artists.has(id)){
            throw new ResourceNotFoundException("Artist not found.");
        }
        ((ObjectNode)artists).remove(id);
        return saveJsonData(artists,artistPath);
    }

    @Override
    public List<Album> getAllAlbums() throws IOException {
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null){
            throw new ResourceNotFoundException("Albums not found.");
        }
        List<Album> albumList = new ArrayList<>();
        Iterator<JsonNode> elements = albums.elements();
        while (elements.hasNext()) {
            albumList.add(objectMapper.treeToValue(elements.next(), Album.class));
        }
        return albumList;
    }

    @Override
    public Album getAlbumById(String id) throws IOException {
        isValidId(id);
        JsonNode album = getJsonAlbum(id);
        if (album == null) {
            throw new ResourceNotFoundException("Album with ID " + id + " not found.");
        }
        return objectMapper.treeToValue(album, Album.class);
    }

    private void isValidId(String id){
        if (!SpotifyUtils.isValidId(id)) {
            throw new InvalidIdException("Invalid Id");
        }
    }

    private JsonNode getJsonAlbum(String id) throws IOException {
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null || !albums.has(id)){
            throw new ResourceNotFoundException("Album with ID " + id + " not found.");
        }
        return albums.get(id);
    }

    @Override
    public Album createAlbum(Album album) throws IOException {
        if (album == null || album.getId() == null || album.getId().isEmpty()) {
            throw new MissingDataException("Missing required fields");
        }
        isValidId(album.getId());
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null){
            throw new ResourceNotFoundException("Albums not found.");
        }
        ObjectNode albumsObject = (ObjectNode) albums;
        if (albumsObject.has(album.getId())){
            throw new ItemExistsException("Album Already Exists");
        }

        albumsObject.set(album.getId(),objectMapper.valueToTree(album));
        saveJsonData(albumsObject,albumPath);
        return album;
    }

    @Override
    public Album updateAlbum(Album album) throws IOException {
        if (album == null || album.getId() == null || album.getId().isEmpty()) {
            throw new MissingDataException("Missing required fields");
        }
        isValidId(album.getId());
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null){
            throw new ResourceNotFoundException("Albums not found.");
        }
        ObjectNode albumsObject = (ObjectNode) albums;
        if (!albumsObject.has(album.getId())){
            throw new ItemExistsException("Album Doesn't Exist");
        }

        albumsObject.set(album.getId(),objectMapper.valueToTree(album));
        saveJsonData(albumsObject,albumPath);
        return album;

    }

    @Override
    public void addNewTrackToAlbum() {}

    @Override
    public void updateTrackInAlbum() {}

    @Override
    public void deleteTrackFromAlbum(String album_id,String track_id) {
        isValidId(album_id);
        isValidId(track_id);
    }

    @Override
    public boolean deleteAlbumById(String id) throws IOException {
        isValidId(id);
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null || !albums.has(id)){
            throw new ResourceNotFoundException("Album with ID " + id + " not found.");
        }
        ((ObjectNode)albums).remove(id);
        return saveJsonData(albums,albumPath);
    }

    @Override
    public List<Track> getAlbumTracks(String id) throws IOException {
        isValidId(id);
        JsonNode album = getJsonAlbum(id);
        if (album == null ) {
            throw new ResourceNotFoundException("Album not found.");
        }
        if (!album.has("tracks")){
            return null;
        }
        JsonNode tracksNode = album.get("tracks");
        List<Track> tracks = new ArrayList<>();
        Iterator<JsonNode> elements = tracksNode.elements();
        while (elements.hasNext()) {
            tracks.add(objectMapper.treeToValue(elements.next(), Track.class));
        }
        return tracks;

    }

    @Override
    public List<Song> getAllSongs() throws IOException {
        JsonNode songs = loadJsonData(songsPath);
        if (songs == null){
            throw new ResourceNotFoundException("Songs not found.");
        }
        List<Song> songList = new ArrayList<>();
        Iterator<JsonNode> elements = songs.elements();
        while (elements.hasNext()) {
            songList.add(objectMapper.treeToValue(elements.next(), Song.class));
        }
        return songList;
    }

    @Override
    public Song getSongById(String id) throws IOException {
        isValidId(id);
        JsonNode songs = loadJsonData(songsPath);
        if (songs == null) {
            throw new ResourceNotFoundException("Songs not found.");
        }
        if (!songs.isArray()){
            throw new InternalException("Internal Error");
        }
        for (JsonNode songNode : songs) {
            JsonNode songIdNode = songNode.get("id");
            if (songIdNode != null && songIdNode.asText().equals(id)) {
                return objectMapper.treeToValue(songNode, Song.class);
            }
        }
        throw new ResourceNotFoundException("Song not found.");
    }

    @Override
    public Song createSong(Song song) throws IOException {
        if (song == null || song.getId() == null || song.getId().isEmpty()) {
            throw new MissingDataException("Missing required fields");
        }
        isValidId(song.getId());
        JsonNode songsNode = loadJsonData(songsPath);
        if (songsNode == null || !songsNode.isArray()) {
            songsNode = objectMapper.createArrayNode();
        }

        for (JsonNode existingSongNode : songsNode) {
            JsonNode songIdNode = existingSongNode.get("id");
            if (songIdNode != null && songIdNode.asText().equals(song.getId())) {
                throw new ItemExistsException("Song already exists");
            }
        }

        JsonNode songNode = objectMapper.valueToTree(song);
        ((ArrayNode) songsNode).add(songNode);
        saveJsonData(songsNode,songsPath);
        return song;

    }

    @Override
    public Song updateSong(Song song) throws IOException {
        if (song == null || song.getId() == null || song.getId().isEmpty()) {
            throw new MissingDataException("Missing required fields");
        }
        isValidId(song.getId());
        JsonNode songsNode = loadJsonData(songsPath);
        if (songsNode == null) {
            throw new ResourceNotFoundException("Songs not found.");
        }
        if (!songsNode.isArray()){
            throw new InternalException("Internal Error");
        }

        ArrayNode updatedSongsArray = objectMapper.createArrayNode();
        boolean found = false;

        for (JsonNode existingSongNode : songsNode) {
            JsonNode songIdNode = existingSongNode.get("id");
            if (songIdNode != null && songIdNode.asText().equals(song.getId())) {
                updatedSongsArray.add(objectMapper.valueToTree(song));
                found = true;
            } else {
                updatedSongsArray.add(existingSongNode);
            }
        }

        if (!found) {
            throw new ResourceNotFoundException("Song With Id: " + song.getId() +" not found.");
        }

        saveJsonData(updatedSongsArray,songsPath);
        return song;

    }

    @Override
    public boolean deleteSongById(String id) throws IOException {
        if (id == null || id.isEmpty()) {
            return false;
        }
        isValidId(id);
        JsonNode songsNode = loadJsonData(songsPath);
        if (songsNode == null || !songsNode.isArray()) {
            return false;
        }
        ArrayNode updatedSongsArray = objectMapper.createArrayNode();
        boolean found = false;
        for (JsonNode existingSongNode : songsNode) {
            JsonNode songIdNode = existingSongNode.get("id");
            if (songIdNode != null && songIdNode.asText().equals(id)) {
                found = true;
            } else {
                updatedSongsArray.add(existingSongNode);
            }
        }
        if (!found) {
            return false;
        }
        return saveJsonData(updatedSongsArray,songsPath);
    }


    private JsonNode loadJsonData(String path) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);

        if (in == null) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return objectMapper.readTree(reader);
    }

    private boolean saveJsonData(JsonNode jsonData,String dataFilePath) throws IOException {
        try {
            File file = new ClassPathResource(dataFilePath).getFile();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonData);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalException("Error Occurred in saving json data");
        }
    }


}
