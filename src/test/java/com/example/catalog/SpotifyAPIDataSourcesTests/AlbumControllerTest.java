package com.example.catalog.SpotifyAPIDataSourcesTests;

import com.example.catalog.exceptions.ResourceNotFoundException;
import com.example.catalog.model.Album;
import com.example.catalog.model.Track;
import com.example.catalog.services.DataSourceService;
import com.example.catalog.services.SpotifyAPIDataSources;
import com.example.catalog.services.SpotifyAuthService;
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
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private SpotifyAPIDataSources dataSourceService;
    @SpyBean
    private SpotifyAuthService spotifyAuthService;

    Map<String, Object> albumsBody = new HashMap<>();

    Map<String, Object> album1;
    Map<String, Object> album2;

    Map<String, Object> tracksResponse;

    @BeforeEach
    void setup(){

        String token = "BQD9v7z1yJY6G8FhU4K5Nq2WmLpOaXsT3VYdR8CZ0MbXYH";
        doReturn(token).when(spotifyAuthService).getAccessToken();

        Map<String, Object> track1 = new HashMap<>();
        track1.put("id", "6b5P51m8xx2XA6U7sdNZ5E");
        track1.put("name", "Alone Again");
        track1.put("duration_ms", 250053);
        track1.put("explicit", true);
        track1.put("uri", "spotify:track:6b5P51m8xx2XA6U7sdNZ5E");

        Map<String, Object> track2 = new HashMap<>();
        track2.put("id", "2K9Ovn1o2bTGqbsABGC6m3");
        track2.put("name", "Too Late");
        track2.put("duration_ms", 239973);
        track2.put("explicit", true);
        track2.put("uri", "spotify:track:2K9Ovn1o2bTGqbsABGC6m3");

        List<Map<String, Object>> tracksList = Arrays.asList(track1, track2);

        tracksResponse = new HashMap<>();
        tracksResponse.put("items", tracksList);


        album1 = new HashMap<>();
        album1.put("id", "4yP0hdKOZPNshxUOjY0cZj");
        album1.put("name", "Mock Album One");
        album1.put("release_date", "2025-01-01");
        album1.put("total_tracks", 12);
        album1.put("uri", "spotify:album:4yP0hdKOZPNshxUOjY0cZj");
        album1.put("tracks", tracksResponse);

        album2 = new HashMap<>();
        album2.put("id", "3T4tUhGYeRNVUGevb0wThu");
        album2.put("name", "Mock Album Two");
        album2.put("release_date", "2023-06-15");
        album2.put("total_tracks", 10);
        album2.put("uri", "spotify:album:3T4tUhGYeRNVUGevb0wThu");

        albumsBody.put("albums", Arrays.asList(album1, album2));

    }

    @Test
    void testGetAllAlbums_Success() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(albumsBody);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/albums")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)))
                .andExpect(jsonPath("$[0].name").value("Mock Album One"))
                .andExpect(jsonPath("$[1].name").value("Mock Album Two"));
    }

    @Test
    void testGetAllAlbums_NullAlbum() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(null);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/albums")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAlbumById_NullAlbum() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(null);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/albums/4yP0hdKOZPNshxUOjY0cZj")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAlbumById_Success() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(album1);
        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/albums/4yP0hdKOZPNshxUOjY0cZj")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mock Album One"));
    }

    @Test
    void testGetAlbumTracks_Success() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(tracksResponse);
        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testGetAlbumTracks_EmptyTracks() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(null);
        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/albums/4yP0hdKOZPNshxUOjY0cZj/tracks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
