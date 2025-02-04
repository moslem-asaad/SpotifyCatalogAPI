package com.example.catalog.SpotifyAPIDataSourcesTests;

import com.example.catalog.services.SpotifyAPIDataSources;
import com.example.catalog.services.SpotifyAuthService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private SpotifyAPIDataSources dataSourceService;
    @SpyBean
    private SpotifyAuthService spotifyAuthService;

    Map<String, Object> songsBody = new HashMap<>();

    Map<String, Object> song1;
    Map<String, Object> song2;

    //Map<String, Object> tracksResponse;

    @BeforeEach
    void setup(){

        String token = "BQD9v7z1yJY6G8FhU4K5Nq2WmLpOaXsT3VYdR8CZ0MbXYH";
        doReturn(token).when(spotifyAuthService).getAccessToken();

        Map<String, Object> album1 = new HashMap<>();
        album1.put("id", "4yP0hdKOZPNshxUOjY0cZj");
        album1.put("name", "After Hours");
        album1.put("uri", "spotify:album:4yP0hdKOZPNshxUOjY0cZj");
        album1.put("release_date", "2020-03-20");
        album1.put("total_tracks", 14);

        Map<String, Object> album2 = new HashMap<>();
        album2.put("id", "3T4tUhGYeRNVUGevb0wThu");
        album2.put("name", "÷ (Deluxe)");
        album2.put("uri", "spotify:album:3T4tUhGYeRNVUGevb0wThu");
        album2.put("release_date", "2017-03-03");
        album2.put("total_tracks", 16);

        Map<String, Object> artist1 = new HashMap<>();
        artist1.put("id", "1Xyo4u8uXC1ZmMpatF05PJ");
        artist1.put("name", "The Weeknd");
        artist1.put("followers", 0);
        artist1.put("popularity", 0);
        artist1.put("uri", "spotify:artist:1Xyo4u8uXC1ZmMpatF05PJ");

        Map<String, Object> artist2 = new HashMap<>();
        artist2.put("id", "6eUKZXaKkcviH0Ku9w2n3V");
        artist2.put("name", "Ed Sheeran");
        artist2.put("followers", 0);
        artist2.put("popularity", 0);
        artist2.put("uri", "spotify:artist:6eUKZXaKkcviH0Ku9w2n3V");

        song1 = new HashMap<>();
        song1.put("id", "0VjIjW4GlUZAMYd2vXMi3b");
        song1.put("name", "Blinding Lights");
        song1.put("popularity", 87);
        song1.put("duration_ms", 200040);
        song1.put("uri", "spotify:track:0VjIjW4GlUZAMYd2vXMi3b");
        song1.put("album", album1);
        song1.put("artists", List.of(artist1));

        song2 = new HashMap<>();
        song2.put("id", "7qiZfU4dY1lWllzX7mPBI3");
        song2.put("name", "Shape of You");
        song2.put("popularity", 85);
        song2.put("duration_ms", 233712);
        song2.put("uri", "spotify:track:7qiZfU4dY1lWllzX7mPBI3");
        song2.put("album", album2);
        song2.put("artists", List.of(artist2));

        songsBody.put("tracks", List.of(song1, song2));



    }

    @Test
    void testGetAllSongs_Success() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(songsBody);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/songs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)))
                .andExpect(jsonPath("$[0].name").value("Blinding Lights"))
                .andExpect(jsonPath("$[1].name").value("Shape of You"));
    }

    @Test
    void testGetAllSongs_NullSong() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(null);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/songs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetSongtById_NullSong() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(null);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/songs/0VjIjW4GlUZAMYd2vXMi3b")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetSongById_Success() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(song1);
        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/songs/0VjIjW4GlUZAMYd2vXMi3b")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Blinding Lights"));
    }



}
