package com.example.catalog;

import com.example.catalog.model.Album;
import com.example.catalog.services.DataSourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSourceService dataSourceService; // Uses real service

    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final Path jsonFilePath = Path.of("src/main/resources/data/popular_artistsTests.json");
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
    void testCreateAlbum() throws Exception {
        Album album = new Album();

        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isNotFound());
    }
}
