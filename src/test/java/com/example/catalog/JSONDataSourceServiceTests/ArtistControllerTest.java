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
public class ArtistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSourceService dataSourceService; // Uses real service


    private final ObjectMapper objectMapper = new ObjectMapper();

    private ClassPathResource jsonFile = new ClassPathResource("data/popular_artistsTest.json");
    private ClassPathResource targetFile = new ClassPathResource("data/popular_artistsTest.json_backup.json");


    @BeforeAll
    void backupJsonFile() throws IOException {
        InputStream inputStream = jsonFile.getInputStream();
        FileCopyUtils.copy(inputStream, Files.newOutputStream(targetFile.getFile().toPath()));
    }

    @AfterEach
    void restoreJsonFile() throws IOException {
        ClassPathResource backupFile = new ClassPathResource("data/popular_artistsTest.json_backup.json");
        InputStream inputStream = backupFile.getInputStream();
        File file = jsonFile.getFile();
        FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
    }

    @Test
    void testGetAllArtists_Success() throws Exception {
        mockMvc.perform(get("/artists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testGetArtistById_Success() throws Exception {
        mockMvc.perform(get("/artists/1Xyo4u8uXC1ZmMpatF05PJ")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("The Weeknd"));
    }

    @Test
    void testCreateArtist_Success() throws Exception {
        Artist artist = new Artist();
        artist.setId("0Xyo4u8uXC1ZmMpatF05PJ");
        artist.setName("Test name");
        mockMvc.perform(post("/artists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artist)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateAlbum_Exists() throws Exception {
        Artist artist = new Artist();
        artist.setId("1Xyo4u8uXC1ZmMpatF05PJ");
        artist.setName("Test name");
        mockMvc.perform(post("/artists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artist)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testUpdateArtist_Success() throws Exception {
        Artist artist = dataSourceService.getArtistById("1Xyo4u8uXC1ZmMpatF05PJ");
        artist.setName("Test Test Updated");

        mockMvc.perform(put("/artists/1Xyo4u8uXC1ZmMpatF05PJ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artist)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Test Updated"));
    }

    @Test
    void testUpdateAlbum_NotFound() throws Exception {
        Artist artist = dataSourceService.getArtistById("1Xyo4u8uXC1ZmMpatF05PJ");
        artist.setName("Test Test Updated");

        mockMvc.perform(put("/artists/12jIjW4GlUZAMYd2vXMi3b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artist)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteArtist_Success() throws Exception {

        mockMvc.perform(delete("/artists/1Xyo4u8uXC1ZmMpatF05PJ")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/artists/1Xyo4u8uXC1ZmMpatF05PJ")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteArtist_NotFound() throws Exception {
        mockMvc.perform(delete("/artists/5Dvkj6JhhA12EX05fT7y2e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteArtist_InvalidId() throws Exception {
        mockMvc.perform(delete("/artists/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetArtistAlbums_Success() throws Exception {
        mockMvc.perform(get("/artists/1Xyo4u8uXC1ZmMpatF05PJ/albums")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testGetArtistAlbums_ArtistsNotExist() throws Exception {
        mockMvc.perform(get("/artists/0Xyo4u8uXC1ZmMpatF05PJ/albums")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetArtistSongs_Success() throws Exception {
        mockMvc.perform(get("/artists/1Xyo4u8uXC1ZmMpatF05PJ/songs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testGetArtistSongs_ArtistsNotExist() throws Exception {
        mockMvc.perform(get("/artists/0Xyo4u8uXC1ZmMpatF05PJ/songs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}