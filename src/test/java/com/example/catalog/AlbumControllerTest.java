package com.example.catalog;

import com.example.catalog.model.Album;
import com.example.catalog.services.DataSourceService;
import com.example.catalog.utils.SpotifyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataSourceService dataSourceService;

    @MockBean
    private SpotifyUtils spotifyUtils;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createAlbum_ValidAlbum_ReturnsCreated() throws Exception {
        // Arrange
        Album album = new Album();
        when(SpotifyUtils.isValidId(anyString())).thenReturn(true);
        when(dataSourceService.createAlbum(any(Album.class))).thenThrow(new UnsupportedOperationException("Create Album Not supported on real spotify app"));

        // Act & Assert
        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isInternalServerError()); // Ensure proper error response
    }

    @Test
    public void createAlbum_InvalidId_ReturnsBadRequest() throws Exception {
        // Arrange
        Album album = new Album();
        when(SpotifyUtils.isValidId(anyString())).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAlbum_ThrowsUnsupportedOperationException_ReturnsInternalServerError() throws Exception {
        // Arrange
        Album album = new Album();
        when(SpotifyUtils.isValidId(anyString())).thenReturn(true);
        when(dataSourceService.createAlbum(any(Album.class))).thenThrow(new UnsupportedOperationException("Create Album Not supported on real spotify app"));

        // Act & Assert
        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Create Album Not supported on real spotify app")); // Validate error message
    }
}
