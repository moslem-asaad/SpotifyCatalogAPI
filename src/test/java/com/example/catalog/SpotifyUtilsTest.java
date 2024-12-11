package com.example.catalog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.example.catalog.utils.SpotifyUtils.isValidId;
import static com.example.catalog.utils.SpotifyUtils.isValidURI;
import static com.example.catalog.utils.SpotifyUtils.getSpotifyClient;
import static org.junit.jupiter.api.Assertions.*;


public class SpotifyUtilsTest {



    //-------------------------------Spotify Id Tests------------------------
    @Test
    public void testValidId() {
        assertTrue(isValidId("6rqhFgbbKwnb9MLmUQDhG6")); // valid Spotify ID
        assertTrue(isValidId("1a2B3c4D5e6F7g8H9iJkL0mN")); // valid 22 character ID
        assertTrue(isValidId("a1b2C3d4E5f6G7h8I9jK0L1m2N")); // valid 30 character ID
    }

    @Test
    public void testInvalidId() {
        assertFalse(isValidId(null)); // null ID
        assertFalse(isValidId("")); // empty ID
        assertFalse(isValidId("shortID")); // too short ID (less than 15 characters)
        assertFalse(isValidId("thisIDiswaytoolongtobevalidaaaa")); // too long ID (more than 30 characters)
        assertFalse(isValidId("!@#$$%^&*()_+")); // invalid characters
        assertFalse(isValidId("1234567890abcdefGHIJKLMNO!@#")); // includes invalid characters
    }



    //-------------------Spotify URI tests-----------------------------


    @Test
    public void testValidURI(){
        assertTrue(isValidURI("spotify:track:6rqhFgbbKwnb9MLmUQDhG6"));
        assertTrue(isValidURI("spotify:album:1a2B3c4D5e6F7g8H9iJkL0mN"));
        assertTrue(isValidURI("spotify:playlist:a1b2C3d4E5f6G7h8I9jK0L1m2N"));
        for(int i = 0 ; i<100;i++){
            assertTrue(isValidURI(generateSpotifyUri()));
        }
    }

    @Test
    public void testInvalidURI(){
        // Null and empty strings
        assertFalse(isValidURI(null));
        assertFalse(isValidURI(""));

        // Missing prefix
        assertFalse(isValidURI("track:123456789012345"));
        assertFalse(isValidURI(":track:123456789012345"));

        // Invalid resource type
        assertFalse(isValidURI("spotify:song:123456789012345"));
        assertFalse(isValidURI("spotify:tracks:123456789012345"));
        assertFalse(isValidURI("spotify::123456789012345"));

        // Invalid Base62 identifier
        assertFalse(isValidURI("spotify:track:12345678901234"));
        assertFalse(isValidURI("spotify:track:ac1vd23fds456wedf789012345678901"));
        assertFalse(isValidURI("spotify:track:1234_invalid_5678"));
        assertFalse(isValidURI("spotify:track:12345@6789012345"));

        // Missing or extra colons
        assertFalse(isValidURI("spotify:track123456789012345"));
        assertFalse(isValidURI("spotify:track::123456789012345"));

        // Case sensitivity
        assertFalse(isValidURI("Spotify:track:123456789012345"));
        assertFalse(isValidURI("spotify:Track:123456789012345"));

        // Miscellaneous invalid patterns
        assertFalse(isValidURI("spotify:track:"));
        assertFalse(isValidURI("spotify:track"));
        assertFalse(isValidURI("spotify:track: "));
        assertFalse(isValidURI("spotify:track:123456789012345 "));
        assertFalse(isValidURI("spotify: track:123456789012345"));

    }


    private static final String[] TYPES = {"track", "album", "artist", "playlist"};
    private static final int MIN_ID_LENGTH = 15;
    private static final int MAX_ID_LENGTH = 30;

    public static String generateSpotifyUri() {
        Random random = new Random();
        String type = TYPES[random.nextInt(TYPES.length)];
        int idLength = MIN_ID_LENGTH + random.nextInt(MAX_ID_LENGTH - MIN_ID_LENGTH + 1);
        String id = generateRandomAlphanumericString(idLength);
        return "spotify:" + type + ":" + id;
    }

    private static String generateRandomAlphanumericString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }



    //--------------------------getSpotifyClient Tests--------------------------------

    @Test
    public void inValidGetSpotifyClientParams(){
        assertThrows(IllegalArgumentException.class,() ->getSpotifyClient(null,"11554592"),"Illegal client id - null");
        assertThrows(IllegalArgumentException.class,() ->getSpotifyClient("","11554592"),"Illegal client id - empty");
        assertThrows(IllegalArgumentException.class,() ->getSpotifyClient("ClientId1123",null),"Illegal client secret - null");
        assertThrows(IllegalArgumentException.class,() ->getSpotifyClient("ClientId1123",""),"Illegal client secret - empty");
    }




}
