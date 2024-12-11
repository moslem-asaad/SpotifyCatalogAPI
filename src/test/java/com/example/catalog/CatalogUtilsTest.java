package com.example.catalog;

import com.example.catalog.utils.CatalogUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogUtilsTest {

    private CatalogUtils catalogUtils;
    private List<JsonNode> songs;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        catalogUtils = new CatalogUtils();
        objectMapper = new ObjectMapper();

        String jsonData = """
                    [
                        
                        {
                            "duration_ms": 200040,
                            "id": "0VjIjW4GlUZAMYd2vXMi3b",
                            "name": "Blinding Lights",
                            "popularity": 87,
                            "uri": "spotify:track:0VjIjW4GlUZAMYd2vXMi3b",
                            "album": {
                              "id": "4yP0hdKOZPNshxUOjY0cZj",
                              "name": "After Hours",
                              "uri": "spotify:album:4yP0hdKOZPNshxUOjY0cZj",
                              "release_date": "2020-03-20",
                              "total_tracks": 14,
                              "images": [
                                {
                                  "width": 640,
                                  "height": 640,
                                  "url": null
                                },
                                {
                                  "url": "ab67616d00001e028863bc11d2aa12b54f5aeb36.jpeg",
                                  "width": 300,
                                  "height": 300
                                },
                                {
                                  "url": "ab67616d000048518863bc11d2aa12b54f5aeb36.jpeg",
                                  "width": 64,
                                  "height": 64
                                }
                              ]
                            },
                            "artists": [
                              {
                                "id": "1Xyo4u8uXC1ZmMpatF05PJ",
                                "name": "The Weeknd",
                                "uri": "spotify:artist:1Xyo4u8uXC1ZmMpatF05PJ"
                              }
                            ]
                          },
                          {
                            "duration_ms": 200040,
                            "id": "0VjIjW4GlUZAMYd2vXMi3b",
                            "name": "ZZzzlinding Lights",
                            "popularity": 85,
                            "uri": "spotify:track:0VjIjW4GlUZAMYd2vXMi3b",
                            "album": {
                              "id": "4yP0hdKOZPNshxUOjY0cZj",
                              "name": "After Hours",
                              "uri": "spotify:album:4yP0hdKOZPNshxUOjY0cZj",
                              "release_date": "2020-03-20",
                              "total_tracks": 14,
                              "images": [
                                {
                                  "width": 640,
                                  "height": 640,
                                  "url": null
                                },
                                {
                                  "url": "ab67616d00001e028863bc11d2aa12b54f5aeb36.jpeg",
                                  "width": 300,
                                  "height": 300
                                },
                                {
                                  "url": "ab67616d000048518863bc11d2aa12b54f5aeb36.jpeg",
                                  "width": 64,
                                  "height": 64
                                }
                              ]
                            },
                            "artists": [
                              {
                                "id": "1Xyo4u8uXC1ZmMpatF05PJ",
                                "name": "The Weeknd",
                                "uri": "spotify:artist:1Xyo4u8uXC1ZmMpatF05PJ"
                              }
                            ]
                          },
                          {
                            "duration_ms": 233712,
                            "id": "7qiZfU4dY1lWllzX7mPBI3",
                            "name": "Shape of You",
                            "popularity": 86,
                            "uri": "spotify:track:7qiZfU4dY1lWllzX7mPBI3",
                            "album": {
                              "id": "3T4tUhGYeRNVUGevb0wThu",
                              "name": "\\u00f7 (Deluxe)",
                              "uri": "spotify:album:3T4tUhGYeRNVUGevb0wThu",
                              "release_date": "2017-03-03",
                              "total_tracks": 16,
                              "images": [
                                {
                                  "width": 640,
                                  "height": 640,
                                  "url": null
                                },
                                {
                                  "url": "ab67616d00001e02ba5db46f4b838ef6027e6f96.jpeg",
                                  "width": 300,
                                  "height": 300
                                },
                                {
                                  "url": "ab67616d00004851ba5db46f4b838ef6027e6f96.jpeg",
                                  "width": 64,
                                  "height": 64
                                }
                              ]
                            },
                            "artists": [
                              {
                                "id": "6eUKZXaKkcviH0Ku9w2n3V",
                                "name": "Ed Sheeran",
                                "uri": "spotify:artist:6eUKZXaKkcviH0Ku9w2n3V"
                              }
                            ]
                          },
                          {
                            "duration_ms": 182160,
                            "id": "7qEHsqek33rTcFNT9PFqLf",
                            "name": "Someone You Loved",
                            "popularity": 86,
                            "uri": "spotify:track:7qEHsqek33rTcFNT9PFqLf",
                            "album": {
                              "id": "5658aM19fA3JVwTK6eQX70",
                              "name": "Divinely Uninspired To A Hellish Extent",
                              "uri": "spotify:album:5658aM19fA3JVwTK6eQX70",
                              "release_date": "2019-05-17",
                              "total_tracks": 12,
                              "images": [
                                {
                                  "width": 640,
                                  "height": 640,
                                  "url": null
                                },
                                {
                                  "url": "ab67616d00001e02fc2101e6889d6ce9025f85f2.jpeg",
                                  "width": 300,
                                  "height": 300
                                },
                                {
                                  "url": "ab67616d00004851fc2101e6889d6ce9025f85f2.jpeg",
                                  "width": 64,
                                  "height": 64
                                }
                              ]
                            },
                            "artists": [
                              {
                                "id": "4GNC7GD6oZMSxPGyXy4MNB",
                                "name": "Lewis Capaldi",
                                "uri": "spotify:artist:4GNC7GD6oZMSxPGyXy4MNB"
                              }
                            ]
                          },
                          {
                            "duration_ms": 167303,
                            "id": "4Dvkj6JhhA12EX05fT7y2e",
                            "name": "As It Was",
                            "popularity": 87,
                            "uri": "spotify:track:4Dvkj6JhhA12EX05fT7y2e",
                            "album": {
                              "id": "5r36AJ6VOJtp00oxSkBZ5h",
                              "name": "Harry's House",
                              "uri": "spotify:album:5r36AJ6VOJtp00oxSkBZ5h",
                              "release_date": "2022-05-20",
                              "total_tracks": 13,
                              "images": [
                                {
                                  "width": 640,
                                  "height": 640,
                                  "url": null
                                },
                                {
                                  "url": "ab67616d00001e022e8ed79e177ff6011076f5f0.jpeg",
                                  "width": 300,
                                  "height": 300
                                },
                                {
                                  "url": "ab67616d000048512e8ed79e177ff6011076f5f0.jpeg",
                                  "width": 64,
                                  "height": 64
                                }
                              ]
                            },
                            "artists": [
                              {
                                "id": "6KImCVD70vtIoJWnq6nGn3",
                                "name": "Harry Styles",
                                "uri": "spotify:artist:6KImCVD70vtIoJWnq6nGn3"
                              }
                            ]
                          }
                    ]
                """;
        songs = new ArrayList<>();
        objectMapper.readTree(jsonData).forEach(songs::add);
    }


    //-----------------sort songs------------------
    @Test
    public void sortSongsTest(){
        catalogUtils.sortSongsByName(songs);
        assertEquals("As It Was",songs.get(0).get("name").asText());
        assertEquals("Blinding Lights",songs.get(1).get("name").asText());
        assertEquals("Shape of You",songs.get(2).get("name").asText());

    }


    //------------------filterSongsByPopularity------------------
    @Test
    void testFilterSongsByPopularity_MinPopularity87() {
        List<JsonNode> filteredSongs = catalogUtils.filterSongsByPopularity(songs, 87);
        assertEquals(2, filteredSongs.size());
        assertEquals("Blinding Lights", filteredSongs.get(0).get("name").asText());
        assertEquals("As It Was", filteredSongs.get(1).get("name").asText());
    }

    @Test
    void testFilterSongsByPopularity_MinPopularity86() {
        List<JsonNode> filteredSongs = catalogUtils.filterSongsByPopularity(songs, 86);
        assertEquals(4, filteredSongs.size());
        assertEquals("Blinding Lights", filteredSongs.get(0).get("name").asText());
        assertEquals("Shape of You", filteredSongs.get(1).get("name").asText());
        assertEquals("Someone You Loved", filteredSongs.get(2).get("name").asText());
        assertEquals("As It Was", filteredSongs.get(3).get("name").asText());
    }

    @Test
    void testFilterSongsByPopularity_MinPopularity88() {
        List<JsonNode> filteredSongs = catalogUtils.filterSongsByPopularity(songs, 88);
        assertEquals(0, filteredSongs.size());
    }

    @Test
    void testFilterSongsByPopularity_EmptyList() {
        List<JsonNode> filteredSongs = catalogUtils.filterSongsByPopularity(new ArrayList<>(), 85);
        assertEquals(0, filteredSongs.size());
    }

    @Test
    void testFilterSongsByPopularity_NegativePopularity() {
        List<JsonNode> filteredSongs = catalogUtils.filterSongsByPopularity(songs, -1);
        assertEquals(5, filteredSongs.size());
    }



    //-----------------------------doesSongExistByName------------------------------

    @Test
    void testDoesSongExistByName_SongExists() {
        assertTrue(catalogUtils.doesSongExistByName(songs, "Blinding Lights"));
        assertTrue(catalogUtils.doesSongExistByName(songs, "As It Was"));
    }
    @Test
    void testDoesSongExistByName_SongExistsCaseInsensitive() {
        assertTrue(catalogUtils.doesSongExistByName(songs, "Blinding Lights".toLowerCase()));
        assertTrue(catalogUtils.doesSongExistByName(songs, "As It Was".toLowerCase()));
    }

    @Test
    void testDoesSongExistByName_SongDoesNotExist() {
        assertFalse(catalogUtils.doesSongExistByName(songs, "blabla Song"));
    }

    @Test
    void testDoesSongExistByName_EmptyandNullSongName() {
        assertFalse(catalogUtils.doesSongExistByName(songs, ""));
        assertFalse(catalogUtils.doesSongExistByName(songs, null));
    }

    @Test
    void testDoesSongExistByName_EmptySongList() {
        assertFalse(catalogUtils.doesSongExistByName(new ArrayList<>(), "Blinding Lights"));
    }


    //------------------CountSongArtist-----------------
    @Test
    void testCountSongsByArtist_ExistingArtist() {
        assertEquals(2, catalogUtils.countSongsByArtist(songs, "The Weeknd"), "The artist 'The Weeknd' should have 2 songs.");
        assertEquals(1, catalogUtils.countSongsByArtist(songs, "Harry Styles"), "The artist 'Harry Styles' should have 1 song.");

    }

    @Test
    void testCountSongsByArtist_CaseInsensitiveMatch() {
        assertEquals(2, catalogUtils.countSongsByArtist(songs, "the weeknd"), "The artist 'the weeknd' (case insensitive) should have 1 song.");
    }

    @Test
    void testCountSongsByArtist_ArtistDoesNotExist() {
        assertEquals(0, catalogUtils.countSongsByArtist(songs, "blabla artist"), "The artist 'Nonexistent Artist' should have 0 songs.");
    }

    @Test
    void testCountSongsByArtist_NullArtistName() {
        assertEquals(0, catalogUtils.countSongsByArtist(songs, null), "A null artist name should match 0 songs.");
    }

    @Test
    void testCountSongsByArtist_EmptySongList() {
        assertEquals(0, catalogUtils.countSongsByArtist(new ArrayList<>(), "The Weeknd"), "An empty song list should match 0 songs.");
    }



    //--------------------------Longest Song--------------------------
    @Test
    void testGetLongestSong_ValidList() {
        // Test for a list with multiple songs
        JsonNode longestSong = catalogUtils.getLongestSong(songs);

        assertNotNull(longestSong, "The longest song should not be null.");
        assertEquals("Shape of You", longestSong.get("name").asText(), "The longest song should be 'Shape of You'.");
        assertEquals(233712, longestSong.get("duration_ms").asInt(), "The duration of the longest song should be 233712 ms.");
    }

    @Test
    void testGetLongestSong_EmptyList() {
        JsonNode longestSong = catalogUtils.getLongestSong(new ArrayList<>());
        assertNull(longestSong, "The longest song should be null for an empty list.");
    }

    @Test
    void testGetLongestSong_SingleSong() {
        List<JsonNode> singleSongList = songs.subList(0, 1); // List with only "Blinding Lights"
        JsonNode longestSong = catalogUtils.getLongestSong(singleSongList);
        assertNotNull(longestSong, "The longest song should not be null for a single-song list.");
        assertEquals("Blinding Lights", longestSong.get("name").asText(), "The longest song should be 'Blinding Lights'.");
        assertEquals(200040, longestSong.get("duration_ms").asInt(), "The duration of the longest song should be 200040 ms.");
    }

    //----------------------getSongByYear------------------------------

    @Test
    void testGetSongByYear_ExistingYear() {
        List<JsonNode> songsInYear = catalogUtils.getSongByYear(songs, 2020);
        assertNotNull(songsInYear, "The result should not be null.");
        assertEquals(2, songsInYear.size(), "There should be 2 song released in 2020.");
        assertEquals("Blinding Lights", songsInYear.get(0).get("name").asText(), "The song released in 2020 should be 'Blinding Lights'.");
    }


    @Test
    void testGetSongByYear_NoSongsInYear() {
        List<JsonNode> songsInYear = catalogUtils.getSongByYear(songs, 1999);
        assertNotNull(songsInYear, "The result should not be null.");
        assertEquals(0, songsInYear.size(), "There should be no songs released in 1999.");
    }

    @Test
    void testGetSongByYear_EmptySongList() {
        List<JsonNode> songsInYear = catalogUtils.getSongByYear(new ArrayList<>(), 2020);
        assertNotNull(songsInYear, "The result should not be null.");
        assertEquals(0, songsInYear.size(), "The result should be empty for an empty song list.");
    }

    @Test
    void testGetSongByYear_InvalidYear() {
        List<JsonNode> songsInYear = catalogUtils.getSongByYear(songs, -2020);
        assertNotNull(songsInYear, "The result should not be null.");
        assertEquals(0, songsInYear.size(), "The result should be empty for an invalid year.");
    }

    //-----------------------------getMostRecentSong--------------------------------
    @Test
    void testGetMostRecentSong_ValidList() {
        JsonNode mostRecentSong = catalogUtils.getMostRecentSong(songs);
        assertNotNull(mostRecentSong, "The most recent song should not be null.");
        assertEquals("As It Was", mostRecentSong.get("name").asText(), "The most recent song should be 'As It Was'.");
        assertEquals("2022-05-20", mostRecentSong.get("album").get("release_date").asText(), "The release date should be '2022-05-20'.");
    }

    @Test
    void testGetMostRecentSong_EmptyList() {
        JsonNode mostRecentSong = catalogUtils.getMostRecentSong(new ArrayList<>());
        assertNull(mostRecentSong, "The most recent song should be null for an empty list.");
    }

    @Test
    void testGetMostRecentSong_SingleSong() {
        List<JsonNode> singleSongList = songs.subList(0, 1);
        JsonNode mostRecentSong = catalogUtils.getMostRecentSong(singleSongList);
        assertNotNull(mostRecentSong, "The most recent song should not be null for a single-song list.");
        assertEquals("Blinding Lights", mostRecentSong.get("name").asText(), "The most recent song should be 'Blinding Lights'.");
        assertEquals("2020-03-20", mostRecentSong.get("album").get("release_date").asText(), "The release date should be '2020-03-20'.");
    }


}