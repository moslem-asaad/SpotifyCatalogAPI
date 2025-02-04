package com.example.catalog.JSONDataSourceServiceTests;
import com.example.catalog.controller.SongController;
import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Song;
import com.example.catalog.model.Track;
import com.example.catalog.services.DataSourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import org.springframework.util.FileCopyUtils;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestPropertySource(properties = "datasource.type=json")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSourceService dataSourceService; // Uses real service


    private final ObjectMapper objectMapper = new ObjectMapper();

    private ClassPathResource jsonFile = new ClassPathResource("data/albumsTest.json");
    private ClassPathResource targetFile = new ClassPathResource("data/albumsTest_backup.json");


    @BeforeAll
    void backupJsonFile() throws IOException {
        InputStream inputStream = jsonFile.getInputStream();
        FileCopyUtils.copy(inputStream, Files.newOutputStream(targetFile.getFile().toPath()));
    }

    @AfterEach
    void restoreJsonFile() throws IOException {
        ClassPathResource backupFile = new ClassPathResource("data/albumsTest_backup.json");
        InputStream inputStream = backupFile.getInputStream();
        File file = jsonFile.getFile();
        FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
    }

    @Test
    void testGetAllAlbums_Success() throws Exception {
        mockMvc.perform(get("/albums")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testGetAlbumById_Success() throws Exception {
        mockMvc.perform(get("/albums/4yP0hdKOZPNshxUOjY0cZj")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("After Hours"));
    }

    @Test
    void testCreateAlbum_Success() throws Exception {
        Album album = new Album();
        album.setId("AlbumW4GlUZAMYd2vXMi3b");
        album.setName("Test Test");

        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateAlbum_Exists() throws Exception {
        Album album = new Album();
        album.setId("4yP0hdKOZPNshxUOjY0cZj");
        album.setName("Test Test");
        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testUpdateAlbum_Success() throws Exception {
        Album album = dataSourceService.getAlbumById("3T4tUhGYeRNVUGevb0wThu");
        album.setName("Test Test Updated");

        mockMvc.perform(put("/albums/3T4tUhGYeRNVUGevb0wThu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Test Updated"));
    }

    @Test
    void testUpdateAlbum_NotFound() throws Exception {
        Album album = dataSourceService.getAlbumById("4yP0hdKOZPNshxUOjY0cZj");
        album.setName("Test Test Updated");

        mockMvc.perform(put("/albums/12jIjW4GlUZAMYd2vXMi3b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAlbum_Success() throws Exception {

        mockMvc.perform(delete("/albums/4yP0hdKOZPNshxUOjY0cZj")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/albums/4yP0hdKOZPNshxUOjY0cZj")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAlbum_NotFound() throws Exception {
        mockMvc.perform(delete("/albums/5Dvkj6JhhA12EX05fT7y2e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAlbum_InvalidId() throws Exception {
        mockMvc.perform(delete("/albums/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAlbumTracks_Success() throws Exception {
        mockMvc.perform(get("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testAddTrackToAlbum_Success() throws Exception {
        Track track = new Track();
        track.setId("8oolFzHipTMg2nL7shhdz2");
        track.setName("Test Track");
        track.setDuration_ms(20000);
        mockMvc.perform(post("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(track)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddTrackToAlbum_TrackExists() throws Exception {
        Track track = new Track();
        track.setId("7oolFzHipTMg2nL7shhdz2");
        track.setName("Test Track");
        track.setDuration_ms(20000);
        mockMvc.perform(post("/albums/3T4tUhGYeRNVUGevb0wThu/tracks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(track)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testUpdateTrackInAlbum_Success() throws Exception {
        Album album = dataSourceService.getAlbumById("4yP0hdKOZPNshxUOjY0cZj");
        Track track = new Track();
        track.setId("6b5P51m8xx2XA6U7sdNZ5E");
        track.setUri("spotify:track:6b5P51m8xx2XA6U7sdNZ5E");
        track.setExplicit(true);
        track.setName("Test Updated");

        mockMvc.perform(put("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks/6b5P51m8xx2XA6U7sdNZ5E")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(track)))
                .andExpect(status().isOk());
        album = dataSourceService.getAlbumById("4yP0hdKOZPNshxUOjY0cZj");
        for (Track track1 : album.getTracks()){
            if (track1.getId().equals(track.getId())){
                Assertions.assertEquals(track.getName(),track1.getName());
            }
        }
    }

    @Test
    void testUpdateTrackInAlbum_TrackExists() throws Exception {
        Track track = new Track();
        track.setId("7oolFzHipTMg2nL7shhdz2");
        track.setName("Test Track");
        track.setDuration_ms(20000);
        mockMvc.perform(post("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks/7oolFzHipTMg2nL7shhdz2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(track)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testDeleteTrack_Success() throws Exception {

        mockMvc.perform(delete("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks/6b5P51m8xx2XA6U7sdNZ5E")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteTrack_NotFound() throws Exception {
        mockMvc.perform(delete("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks/1oolFzHipTMg2nL7shhdz2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTrackFromAlbum_InvalidId() throws Exception {
        mockMvc.perform(delete("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks/12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}