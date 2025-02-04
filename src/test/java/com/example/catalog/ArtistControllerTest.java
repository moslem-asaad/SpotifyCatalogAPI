package com.example.catalog;

import com.example.catalog.model.Artist;
import com.example.catalog.services.DataSourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Ensure @BeforeAll and @AfterAll work
class ArtistControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSourceService dataSourceService; // Uses real service

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Path jsonFilePath = Path.of("src/main/resources/data/popular_artistsTests.json");
//    private final Path backupFilePath = Path.of("src/main/resources/data/popular_artistsTests_backup.json");
//
//    @BeforeAll
//    void backupJsonFile() throws IOException {
//        // Backup the JSON file before tests
//        Files.copy(jsonFilePath, backupFilePath, StandardCopyOption.REPLACE_EXISTING);
//    }
//
//    @AfterAll
//    void restoreJsonFile() throws IOException {
//        // Restore the original JSON file after tests
//        Files.copy(backupFilePath, jsonFilePath, StandardCopyOption.REPLACE_EXISTING);
//        Files.delete(backupFilePath);
//    }

    @Test
    void testCreateArtist_Success() throws Exception {
        Artist artist = new Artist();
        artist.setId("1111111");
        artist.setName("Integration Test Artist");
        artist.setFollowers(3000);
        artist.setGenres(Arrays.asList("Jazz", "Blues"));
        artist.setPopularity(90);
        artist.setUri("spotify:artist:999");

        mockMvc.perform(post("/artists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artist)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1111111"))
                .andExpect(jsonPath("$.name").value("Integration Test Artist"))
                .andExpect(jsonPath("$.followers").value(3000))
                .andExpect(jsonPath("$.genres[0]").value("Jazz"))
                .andExpect(jsonPath("$.popularity").value(90));
    }
}
