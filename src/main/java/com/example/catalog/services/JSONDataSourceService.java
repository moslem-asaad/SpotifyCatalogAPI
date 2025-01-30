package com.example.catalog.services;

import com.example.catalog.model.Artist;
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

    private final String artistPath = "data/popular_artistsTests.json";

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
