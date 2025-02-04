package com.example.catalog.SpotifyAPIDataSourcesTests;

import com.example.catalog.exceptions.ResourceNotFoundException;
import com.example.catalog.model.Album;
import com.example.catalog.services.DataSourceService;
import com.example.catalog.services.SpotifyAPIDataSources;
import com.example.catalog.utils.SpotifyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpotifyAPIDataSources dataSourceService;

    private List<Album> albums;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup(){
        Album album1 = new Album();
        album1.setId("4yP0hdKOZPNshxUOjY0cZj");
        album1.setName("After Hours");
        album1.setRelease_date("2020-03-20");
        album1.setTotal_tracks(14);
        album1.setUri("spotify:album:4yP0hdKOZPNshxUOjY0cZj1");

        Album album2 = new Album();
        album2.setId("3T4tUhGYeRNVUGevb0wThu");
        album2.setName("\u00f7 (Deluxe)");
        album2.setRelease_date("2017-03-03");
        album2.setTotal_tracks(16);
        album2.setUri("spotify:album:3T4tUhGYeRNVUGevb0wThu");

        albums = Arrays.asList(album1, album2);

    }

    @Test
    void testGetAllAlbums_Success() throws Exception {
        when(dataSourceService.getAllAlbums()).thenReturn(albums);

        mockMvc.perform(get("/albums")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testGetAlbumById_Success() throws Exception {
        when(dataSourceService.getAlbumById("4yP0hdKOZPNshxUOjY0cZj")).thenReturn(albums.get(0));

        mockMvc.perform(get("/albums/4yP0hdKOZPNshxUOjY0cZj")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("After Hours"));
    }

    @Test
    public void createAlbum_NotFound_ReturnsNotFound() throws Exception {
        Album album = new Album();
        album.setId("12345");
        album.setName("Mock Album");
        album.setRelease_date("2025-01-01");
        album.setTotal_tracks(10);
        album.setUri("spotify:album:12345");

        doThrow(new ResourceNotFoundException("Album not found")).when(dataSourceService).createAlbum(any(Album.class));

        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Resource Not Found"))
                .andExpect(jsonPath("$.message").value("Album not found"));
    }

    @Test
    public void updateAlbum_NotFound_ReturnsNotFound() throws Exception {
        Album album = new Album();
        album.setId("12345");
        album.setName("Mock Album");
        album.setRelease_date("2025-01-01");
        album.setTotal_tracks(10);
        album.setUri("spotify:album:12345");

        doThrow(new ResourceNotFoundException("Album not found")).when(dataSourceService).updateAlbum(any(Album.class));

        mockMvc.perform(put("/albums/1yP0hdKOZPNshxUOjY0cZj")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Resource Not Found"))
                .andExpect(jsonPath("$.message").value("Album not found"));
    }

//    @Test
//    void testDeleteAlbum_NotFound() throws Exception {
//        mockMvc.perform(delete("/albums/5Dvkj6JhhA12EX05fT7y2e")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }

}
