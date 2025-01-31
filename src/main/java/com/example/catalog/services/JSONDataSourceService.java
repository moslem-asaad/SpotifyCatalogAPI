package com.example.catalog.services;

import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Track;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    private final String artistPath = "data/popular_artistsTest.json";
    private final String albumPath = "data/albums.json";

    @Override
    public Artist getArtistById(String id) throws IOException {
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null){
            return null;
        }
        JsonNode artistNode = artists.get(id);
        if (artistNode == null) {
            return null;
        }
        return objectMapper.treeToValue(artistNode, Artist.class);
    }

    @Override
    public List<Artist> getAllArtists() throws IOException {
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null){
            return null;
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
            return null;
        }
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null) {
            artists = objectMapper.createObjectNode();
        }
        ObjectNode artistsObject = (ObjectNode) artists;
        if (artistsObject.has(artist.getId())){
            return null;
        }

        artistsObject.set(artist.getId(),objectMapper.valueToTree(artist));

        if (saveJsonData(artistsObject,artistPath)){
            return artist;
        }else{
            return null;
        }
    }

    @Override
    public Artist updateArtist(Artist artist) throws IOException {
        if (artist == null || artist.getId() == null || artist.getId().isEmpty()) {
            return null;
        }
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null){
            return null;
        }
        ObjectNode artistsObject = (ObjectNode) artists;
        if (!artistsObject.has(artist.getId())){
            return null;
        }

        artistsObject.set(artist.getId(),objectMapper.valueToTree(artist));
        if (saveJsonData(artistsObject,artistPath)){
            return artist;
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteArtistById(String id) throws IOException {
        JsonNode artists = loadJsonData(artistPath);
        if (artists == null || !artists.has(id)){
            return false;
        }
        ((ObjectNode)artists).remove(id);
        return saveJsonData(artists,artistPath);
    }

    @Override
    public List<Album> getAllAlbums() throws IOException {
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null){
            return null;
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
        JsonNode album = getJsonAlbum(id);
        if (album == null) {
            return null;
        }
        return objectMapper.treeToValue(album, Album.class);
    }

    private JsonNode getJsonAlbum(String id) throws IOException {
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null || !albums.has(id)){
            return null;
        }
        return albums.get(id);
    }

    @Override
    public Album createAlbum(Album album) throws IOException {
        if (album == null || album.getId() == null || album.getId().isEmpty()) {
            return null;
        }
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null){
            return null;
        }
        ObjectNode albumsObject = (ObjectNode) albums;
        if (albumsObject.has(album.getId())){
            return null;
        }

        albumsObject.set(album.getId(),objectMapper.valueToTree(album));
        if (saveJsonData(albumsObject,albumPath)){
            return album;
        }else{
            return null;
        }
    }

    @Override
    public Album updateAlbum(Album album) throws IOException {
        if (album == null || album.getId() == null || album.getId().isEmpty()) {
            return null;
        }
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null){
            return null;
        }
        ObjectNode albumsObject = (ObjectNode) albums;
        if (!albumsObject.has(album.getId())){
            return null;
        }

        albumsObject.set(album.getId(),objectMapper.valueToTree(album));
        if (saveJsonData(albumsObject,albumPath)){
            return album;
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteAlbumById(String id) throws IOException {
        JsonNode albums = loadJsonData(albumPath);
        if (albums == null || !albums.has(id)){
            return false;
        }
        ((ObjectNode)albums).remove(id);
        return saveJsonData(albums,albumPath);
    }

    @Override
    public List<Track> getAlbumTracks(String id) throws IOException {
        JsonNode album = getJsonAlbum(id);
        if (album == null || !album.has("tracks")) {
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


    private JsonNode loadJsonData(String path) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);

        if (in == null) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return objectMapper.readTree(reader);


//        ClassPathResource resource = new ClassPathResource(path);
//        return objectMapper.readTree(resource.getFile());
    }

    private boolean saveJsonData(JsonNode jsonData,String dataFilePath) {
        try {
            File file = new ClassPathResource(dataFilePath).getFile();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonData);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}
