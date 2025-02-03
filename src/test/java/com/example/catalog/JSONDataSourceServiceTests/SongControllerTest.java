package com.example.catalog.JSONDataSourceServiceTests;
import com.example.catalog.controller.SongController;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Song;
import com.example.catalog.services.DataSourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SongControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSourceService dataSourceService; // Uses real service


    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Path jsonFilePath = Path.of("data/popular_songsTests.json");
    private final Path backupFilePath = Path.of("data/popular_artistsTests_backup.json");

    @BeforeAll
    void backupJsonFile() throws IOException {
        // Backup the JSON file before tests
        Files.copy(jsonFilePath, backupFilePath, StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterAll
    void restoreJsonFile() throws IOException {
        // Restore the original JSON file after tests
        Files.copy(backupFilePath, jsonFilePath, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(backupFilePath);
    }

    @Test
    void testGetAllSongs_Success() throws Exception {
        mockMvc.perform(get("/songs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testGetSongById_Success() throws Exception {
        mockMvc.perform(get("/songs/7qiZfU4dY1lWllzX7mPBI3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Shape of You"));
    }

    @Test
    void testCreateSong_Success() throws Exception {
        Song song = new Song();
        song.setId("TESTfU4dY1lWllzX7mPBI3");
        song.setName("Test Test");
        song.setPopularity(75);
        song.setUri("spotify:track:TESTfU4dY1lWllzX7mPBI3");
        song.setDuration_ms(200000);

        ArrayList<Artist> artists = new ArrayList<>();
        Artist artist = dataSourceService.getArtistById("1Xyo4u8uXC1ZmMpatF05PJ");
        artists.add(artist);
        song.setArtists(artists);

        mockMvc.perform(post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(song)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Test"));
    }

    @Test
    void testUpdateSong_Success() throws Exception {
        Song song = dataSourceService.getSongById("4Dvkj6JhhA12EX05fT7y2e");
        String name = song.getName();
        song.setName("Test Test Updated");

        mockMvc.perform(put("/songs/0VjIjW4GlUZAMYd2vXMi3b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(song)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Test Updated"));

        song = dataSourceService.getSongById("4Dvkj6JhhA12EX05fT7y2e");
        song.setName(name);
        mockMvc.perform(put("/songs/0VjIjW4GlUZAMYd2vXMi3b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(song)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name));
    }

}
