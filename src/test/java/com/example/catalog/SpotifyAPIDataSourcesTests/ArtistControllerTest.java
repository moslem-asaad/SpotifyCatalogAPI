package com.example.catalog.SpotifyAPIDataSourcesTests;

import com.example.catalog.model.Album;
import com.example.catalog.model.Artist;
import com.example.catalog.model.Song;
import com.example.catalog.services.SpotifyAPIDataSources;
import com.example.catalog.services.SpotifyAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private SpotifyAPIDataSources dataSourceService;
    @SpyBean
    private SpotifyAuthService spotifyAuthService;

    Map<String, Object> artistsBody = new HashMap<>();

    Map<String, Object> artist1;
    Map<String, Object> artist2;

    //Map<String, Object> tracksResponse;

    @BeforeEach
    void setup(){

        String token = "BQD9v7z1yJY6G8FhU4K5Nq2WmLpOaXsT3VYdR8CZ0MbXYH";
        doReturn(token).when(spotifyAuthService).getAccessToken();

        artist1 = new HashMap<>();
        artist1.put("id","6eUKZXaKkcviH0Ku9w2n3V");
        artist1.put("name","Ed Sheeran");
        artist1.put("popularity",86);
        artist1.put("uri","spotify:artist:6eUKZXaKkcviH0Ku9w2n3V");

        artist2 = new HashMap<>();
        artist2.put("id","1Xyo4u8uXC1ZmMpatF05PJ");
        artist2.put("name","The Weeknd");
        artist2.put("popularity",95);
        artist2.put("uri","spotify:artist:1Xyo4u8uXC1ZmMpatF05PJ");

        artistsBody.put("artists", Arrays.asList(artist1, artist2));

    }

    @Test
    void testGetAllArtists_Success() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(artistsBody);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/artists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)))
                .andExpect(jsonPath("$[0].name").value("Ed Sheeran"))
                .andExpect(jsonPath("$[1].name").value("The Weeknd"));
    }

    @Test
    void testGetAllArtists_NullArtist() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(null);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/artists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetArtistById_NullArtist() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(null);

        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/artists/6eUKZXaKkcviH0Ku9w2n3V")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetArtistById_Success() throws Exception {
        ResponseEntity<Map> mockResponse = ResponseEntity.ok(artist1);
        doReturn(mockResponse)
                .when(dataSourceService)
                .callAPI(anyString(), anyString(), any(HttpMethod.class));

        mockMvc.perform(get("/artists/6eUKZXaKkcviH0Ku9w2n3V")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ed Sheeran"));
    }



}
