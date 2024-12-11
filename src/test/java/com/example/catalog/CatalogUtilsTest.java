package com.example.catalog;

import com.example.catalog.utils.CatalogUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CatalogUtilsTest {

    private CatalogUtils catalogUtils;
    private List<JsonNode> songs;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        catalogUtils = new CatalogUtils();
        objectMapper = new ObjectMapper();

        // Sample song data for testing. TODO - Add more songs
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
                       },
                       {
                         "duration_ms": 158040,
                         "id": "3KkXRkHbMCARz0aVfEt68P",
                         "name": "Sunflower - Spider-Man: Into the Spider-Verse",
                         "popularity": 79,
                         "uri": "spotify:track:3KkXRkHbMCARz0aVfEt68P",
                         "album": {
                           "id": "35s58BRTGAEWztPo9WqCIs",
                           "name": "Spider-Man: Into the Spider-Verse (Soundtrack From & Inspired by the Motion Picture)",
                           "uri": "spotify:album:35s58BRTGAEWztPo9WqCIs",
                           "release_date": "2018-12-14",
                           "total_tracks": 13,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02e2e352d89826aef6dbd5ff8f.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851e2e352d89826aef6dbd5ff8f.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "246dkjvS1zLTtiykXe5h60",
                             "name": "Post Malone",
                             "uri": "spotify:artist:246dkjvS1zLTtiykXe5h60"
                           },
                           {
                             "id": "1zNqQNIdeOUZHb8zbZRFMX",
                             "name": "Swae Lee",
                             "uri": "spotify:artist:1zNqQNIdeOUZHb8zbZRFMX"
                           }
                         ]
                       },
                       {
                         "duration_ms": 230453,
                         "id": "5aAx2yezTd8zXrkmtKl66Z",
                         "name": "Starboy",
                         "popularity": 2,
                         "uri": "spotify:track:5aAx2yezTd8zXrkmtKl66Z",
                         "album": {
                           "id": "09fggMHib4YkOtwQNXEBII",
                           "name": "Starboy",
                           "uri": "spotify:album:09fggMHib4YkOtwQNXEBII",
                           "release_date": "2016-11-25",
                           "total_tracks": 18,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e020c8599cbde51245c128bcea9.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048510c8599cbde51245c128bcea9.jpeg",
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
                           },
                           {
                             "id": "4tZwfgrHOc3mvqYlEYSvVi",
                             "name": "Daft Punk",
                             "uri": "spotify:artist:4tZwfgrHOc3mvqYlEYSvVi"
                           }
                         ]
                       },
                       {
                         "duration_ms": 173986,
                         "id": "1xznGGDReH1oQq0xzbwXa3",
                         "name": "One Dance",
                         "popularity": 2,
                         "uri": "spotify:track:1xznGGDReH1oQq0xzbwXa3",
                         "album": {
                           "id": "3hARKC8cinq3mZLLAEaBh9",
                           "name": "Views",
                           "uri": "spotify:album:3hARKC8cinq3mZLLAEaBh9",
                           "release_date": "2016-05-06",
                           "total_tracks": 20,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02726abca207567d5e41cb9667.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851726abca207567d5e41cb9667.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "3TVXtAsR1Inumwj472S9r4",
                             "name": "Drake",
                             "uri": "spotify:artist:3TVXtAsR1Inumwj472S9r4"
                           },
                           {
                             "id": "3tVQdUvClmAT7URs9V3rsp",
                             "name": "Wizkid",
                             "uri": "spotify:artist:3tVQdUvClmAT7URs9V3rsp"
                           },
                           {
                             "id": "77DAFfvm3O9zT5dIoG0eIO",
                             "name": "Kyla",
                             "uri": "spotify:artist:77DAFfvm3O9zT5dIoG0eIO"
                           }
                         ]
                       },
                       {
                         "duration_ms": 141805,
                         "id": "5PjdY0CKGZdEuoNab3yDmX",
                         "name": "STAY (with Justin Bieber)",
                         "popularity": 16,
                         "uri": "spotify:track:5PjdY0CKGZdEuoNab3yDmX",
                         "album": {
                           "id": "4bZJWQhHKJckFLJuYdvyX2",
                           "name": "F*CK LOVE 3: OVER YOU",
                           "uri": "spotify:album:4bZJWQhHKJckFLJuYdvyX2",
                           "release_date": "2021-07-23",
                           "total_tracks": 29,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e028e6551a2944764bc8e33a960.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048518e6551a2944764bc8e33a960.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2tIP7SsRs7vjIcLrU85W8J",
                             "name": "The Kid LAROI",
                             "uri": "spotify:artist:2tIP7SsRs7vjIcLrU85W8J"
                           },
                           {
                             "id": "1uNFoZAHBGtllmzznpCI3s",
                             "name": "Justin Bieber",
                             "uri": "spotify:artist:1uNFoZAHBGtllmzznpCI3s"
                           }
                         ]
                       },
                       {
                         "duration_ms": 240400,
                         "id": "2QjOHCTQ1Jl3zawyYOpxh6",
                         "name": "Sweater Weather",
                         "popularity": 90,
                         "uri": "spotify:track:2QjOHCTQ1Jl3zawyYOpxh6",
                         "album": {
                           "id": "4xkM0BwLM9H2IUcbYzpcBI",
                           "name": "I Love You.",
                           "uri": "spotify:album:4xkM0BwLM9H2IUcbYzpcBI",
                           "release_date": "2013-04-22",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e028265a736a1eb838ad5a0b921.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048518265a736a1eb838ad5a0b921.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "77SW9BnxLY8rJ0RciFqkHh",
                             "name": "The Neighbourhood",
                             "uri": "spotify:artist:77SW9BnxLY8rJ0RciFqkHh"
                           }
                         ]
                       },
                       {
                         "duration_ms": 204346,
                         "id": "0CcQNd8CINkwQfe1RDtGV6",
                         "name": "Believer",
                         "popularity": 2,
                         "uri": "spotify:track:0CcQNd8CINkwQfe1RDtGV6",
                         "album": {
                           "id": "5GlPAy2PRJW06GVFhKwGTl",
                           "name": "Evolve",
                           "uri": "spotify:album:5GlPAy2PRJW06GVFhKwGTl",
                           "release_date": "2017-06-23",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e027956bd9a3d7a15e4c2e37cc6.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048517956bd9a3d7a15e4c2e37cc6.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "53XhwfbYqKCa1cC15pYq2q",
                             "name": "Imagine Dragons",
                             "uri": "spotify:artist:53XhwfbYqKCa1cC15pYq2q"
                           }
                         ]
                       },
                       {
                         "duration_ms": 238805,
                         "id": "6CDzDgIUqeDY5g8ujExx2f",
                         "name": "Heat Waves",
                         "popularity": 7,
                         "uri": "spotify:track:6CDzDgIUqeDY5g8ujExx2f",
                         "album": {
                           "id": "69K1zrf6TkXHdYUO8n2qVi",
                           "name": "Heat Waves",
                           "uri": "spotify:album:69K1zrf6TkXHdYUO8n2qVi",
                           "release_date": "2020-06-29",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02ab9d1ae18b640b7b0ce390d4.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851ab9d1ae18b640b7b0ce390d4.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4yvcSjfu4PC0CYQyLy4wSq",
                             "name": "Glass Animals",
                             "uri": "spotify:artist:4yvcSjfu4PC0CYQyLy4wSq"
                           }
                         ]
                       },
                       {
                         "duration_ms": 209438,
                         "id": "2XU0oxnq2qxCpomAAuJY8K",
                         "name": "Dance Monkey",
                         "popularity": 72,
                         "uri": "spotify:track:2XU0oxnq2qxCpomAAuJY8K",
                         "album": {
                           "id": "0UywfDKYlyiu1b38DRrzYD",
                           "name": "Dance Monkey (Stripped Back) / Dance Monkey",
                           "uri": "spotify:album:0UywfDKYlyiu1b38DRrzYD",
                           "release_date": "2019-10-17",
                           "total_tracks": 2,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02c6f7af36ecdc3ed6e0a1f169.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851c6f7af36ecdc3ed6e0a1f169.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2NjfBq1NflQcKSeiDooVjY",
                             "name": "Tones And I",
                             "uri": "spotify:artist:2NjfBq1NflQcKSeiDooVjY"
                           }
                         ]
                       },
                       {
                         "duration_ms": 263400,
                         "id": "0tgVpDi06FyKpA1z0VMD4v",
                         "name": "Perfect",
                         "popularity": 86,
                         "uri": "spotify:track:0tgVpDi06FyKpA1z0VMD4v",
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
                         "duration_ms": 218320,
                         "id": "7wGoVu4Dady5GV0Sv4UIsx",
                         "name": "rockstar",
                         "popularity": 10,
                         "uri": "spotify:track:7wGoVu4Dady5GV0Sv4UIsx",
                         "album": {
                           "id": "4AabsQ6plH3OmdE6BWI04j",
                           "name": "rockstar",
                           "uri": "spotify:album:4AabsQ6plH3OmdE6BWI04j",
                           "release_date": "2017-09-15",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02345f823b6c1dbd3b4cc7b382.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851345f823b6c1dbd3b4cc7b382.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "246dkjvS1zLTtiykXe5h60",
                             "name": "Post Malone",
                             "uri": "spotify:artist:246dkjvS1zLTtiykXe5h60"
                           },
                           {
                             "id": "1URnnhqYAYcrqrcwql10ft",
                             "name": "21 Savage",
                             "uri": "spotify:artist:1URnnhqYAYcrqrcwql10ft"
                           }
                         ]
                       },
                       {
                         "duration_ms": 142409,
                         "id": "5G4uNOOs5ZyVicj6vHl6KY",
                         "name": "Tunnel Of Love",
                         "popularity": 35,
                         "uri": "spotify:track:5G4uNOOs5ZyVicj6vHl6KY",
                         "album": {
                           "id": "4qzGXAu5FEVHfW5PeQwgqC",
                           "name": "Tunnel Of Love",
                           "uri": "spotify:album:4qzGXAu5FEVHfW5PeQwgqC",
                           "release_date": "2024-10-01",
                           "total_tracks": 2,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0204d172853b7ff094dad0c5a9.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485104d172853b7ff094dad0c5a9.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0HYqyl7vfFMF4reDxFbmFe",
                             "name": "Kalu",
                             "uri": "spotify:artist:0HYqyl7vfFMF4reDxFbmFe"
                           }
                         ]
                       },
                       {
                         "duration_ms": 200185,
                         "id": "0u2P5u6lvoDfwTYjAADbn4",
                         "name": "lovely (with Khalid)",
                         "popularity": 86,
                         "uri": "spotify:track:0u2P5u6lvoDfwTYjAADbn4",
                         "album": {
                           "id": "2sBB17RXTamvj7Ncps15AK",
                           "name": "lovely (with Khalid)",
                           "uri": "spotify:album:2sBB17RXTamvj7Ncps15AK",
                           "release_date": "2018-04-19",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e028a3f0a3ca7929dea23cd274c.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048518a3f0a3ca7929dea23cd274c.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6qqNVTkY8uBg9cP3Jd7DAH",
                             "name": "Billie Eilish",
                             "uri": "spotify:artist:6qqNVTkY8uBg9cP3Jd7DAH"
                           },
                           {
                             "id": "6LuN9FCkKOj5PcnpouEgny",
                             "name": "Khalid",
                             "uri": "spotify:artist:6LuN9FCkKOj5PcnpouEgny"
                           }
                         ]
                       },
                       {
                         "duration_ms": 211466,
                         "id": "5uCax9HTNlzGybIStD3vDh",
                         "name": "Say You Won't Let Go",
                         "popularity": 85,
                         "uri": "spotify:track:5uCax9HTNlzGybIStD3vDh",
                         "album": {
                           "id": "7oiJYvEJHsmYtrgviAVIBD",
                           "name": "Back from the Edge",
                           "uri": "spotify:album:7oiJYvEJHsmYtrgviAVIBD",
                           "release_date": "2016-10-28",
                           "total_tracks": 17,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0220beb61f61fcbeb33b10a9ab.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485120beb61f61fcbeb33b10a9ab.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4IWBUUAFIplrNtaOHcJPRM",
                             "name": "James Arthur",
                             "uri": "spotify:artist:4IWBUUAFIplrNtaOHcJPRM"
                           }
                         ]
                       },
                       {
                         "duration_ms": 266358,
                         "id": "2NQzj8czPzUw9ufCOvAT8W",
                         "name": "Sunlight",
                         "popularity": 47,
                         "uri": "spotify:track:2NQzj8czPzUw9ufCOvAT8W",
                         "album": {
                           "id": "6MlQBLTUfE3Ritq5gkPUOb",
                           "name": "Sunlight",
                           "uri": "spotify:album:6MlQBLTUfE3Ritq5gkPUOb",
                           "release_date": "2024-09-06",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0298d26bb9b919a7f9111dd186.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485198d26bb9b919a7f9111dd186.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2acqLrAZ1lspWmbpQzdiGE",
                             "name": "The Hourglass Effect",
                             "uri": "spotify:artist:2acqLrAZ1lspWmbpQzdiGE"
                           }
                         ]
                       },
                       {
                         "duration_ms": 244960,
                         "id": "7BKLCZ1jbUBVqRi2FVlTVw",
                         "name": "Closer",
                         "popularity": 84,
                         "uri": "spotify:track:7BKLCZ1jbUBVqRi2FVlTVw",
                         "album": {
                           "id": "0rSLgV8p5FzfnqlEk4GzxE",
                           "name": "Closer",
                           "uri": "spotify:album:0rSLgV8p5FzfnqlEk4GzxE",
                           "release_date": "2016-07-29",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02495ce6da9aeb159e94eaa453.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851495ce6da9aeb159e94eaa453.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "69GGBxA162lTqCwzJG5jLp",
                             "name": "The Chainsmokers",
                             "uri": "spotify:artist:69GGBxA162lTqCwzJG5jLp"
                           },
                           {
                             "id": "26VFTg2z8YR0cCuwLzESi2",
                             "name": "Halsey",
                             "uri": "spotify:artist:26VFTg2z8YR0cCuwLzESi2"
                           }
                         ]
                       },
                       {
                         "duration_ms": 174000,
                         "id": "6UelLqGlWMcVH1E5c4H7lY",
                         "name": "Watermelon Sugar",
                         "popularity": 82,
                         "uri": "spotify:track:6UelLqGlWMcVH1E5c4H7lY",
                         "album": {
                           "id": "7xV2TzoaVc0ycW7fwBwAml",
                           "name": "Fine Line",
                           "uri": "spotify:album:7xV2TzoaVc0ycW7fwBwAml",
                           "release_date": "2019-12-13",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0277fdcfda6535601aff081b6a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485177fdcfda6535601aff081b6a.jpeg",
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
                       },
                       {
                         "duration_ms": 190960,
                         "id": "0TK2YIli7K1leLovkQiNik",
                         "name": "Se\\u00f1orita",
                         "popularity": 75,
                         "uri": "spotify:track:0TK2YIli7K1leLovkQiNik",
                         "album": {
                           "id": "2ZaX1FdZCwchXl1QZiD4O4",
                           "name": "Se\\u00f1orita",
                           "uri": "spotify:album:2ZaX1FdZCwchXl1QZiD4O4",
                           "release_date": "2019-06-21",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02e6095c382c2853667c1623eb.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851e6095c382c2853667c1623eb.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7n2wHs1TKAczGzO7Dd2rGr",
                             "name": "Shawn Mendes",
                             "uri": "spotify:artist:7n2wHs1TKAczGzO7Dd2rGr"
                           },
                           {
                             "id": "4nDoRrQiYLoBzwC5BhVJzF",
                             "name": "Camila Cabello",
                             "uri": "spotify:artist:4nDoRrQiYLoBzwC5BhVJzF"
                           }
                         ]
                       },
                       {
                         "duration_ms": 247160,
                         "id": "6RUKPb4LETWmmr3iAEQktW",
                         "name": "Something Just Like This",
                         "popularity": 86,
                         "uri": "spotify:track:6RUKPb4LETWmmr3iAEQktW",
                         "album": {
                           "id": "4JPguzRps3kuWDD5GS6oXr",
                           "name": "Memories...Do Not Open",
                           "uri": "spotify:album:4JPguzRps3kuWDD5GS6oXr",
                           "release_date": "2017-04-07",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e020c13d3d5a503c84fcc60ae94.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048510c13d3d5a503c84fcc60ae94.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "69GGBxA162lTqCwzJG5jLp",
                             "name": "The Chainsmokers",
                             "uri": "spotify:artist:69GGBxA162lTqCwzJG5jLp"
                           },
                           {
                             "id": "4gzpq5DPGxSnKTe4SA8HAU",
                             "name": "Coldplay",
                             "uri": "spotify:artist:4gzpq5DPGxSnKTe4SA8HAU"
                           }
                         ]
                       },
                       {
                         "duration_ms": 171374,
                         "id": "6EIsrLeTHnX1fNU2Ci36RL",
                         "name": "where do i go?",
                         "popularity": 41,
                         "uri": "spotify:track:6EIsrLeTHnX1fNU2Ci36RL",
                         "album": {
                           "id": "2umeC5Wf7Iw5HIMHlsjEjR",
                           "name": "where do i go?",
                           "uri": "spotify:album:2umeC5Wf7Iw5HIMHlsjEjR",
                           "release_date": "2024-06-07",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02ac8e5eff90bc7d57b8e56b12.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851ac8e5eff90bc7d57b8e56b12.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "5Dsd7jTkXFpmNtZTWuBMDh",
                             "name": "leoleo",
                             "uri": "spotify:artist:5Dsd7jTkXFpmNtZTWuBMDh"
                           }
                         ]
                       },
                       {
                         "duration_ms": 204280,
                         "id": "7yq4Qj7cqayVTp3FF9CWbm",
                         "name": "Riptide",
                         "popularity": 78,
                         "uri": "spotify:track:7yq4Qj7cqayVTp3FF9CWbm",
                         "album": {
                           "id": "6rIbiUMmZJfqJRnXhVxFvg",
                           "name": "Dream Your Life Away",
                           "uri": "spotify:album:6rIbiUMmZJfqJRnXhVxFvg",
                           "release_date": "2014-09-08",
                           "total_tracks": 13,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02d3ce97395ff522b0d70c1094.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851d3ce97395ff522b0d70c1094.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "10exVja0key0uqUkk6LJRT",
                             "name": "Vance Joy",
                             "uri": "spotify:artist:10exVja0key0uqUkk6LJRT"
                           }
                         ]
                       },
                       {
                         "duration_ms": 183290,
                         "id": "3PfIrDoz19wz7qK7tYeu62",
                         "name": "Don't Start Now",
                         "popularity": 80,
                         "uri": "spotify:track:3PfIrDoz19wz7qK7tYeu62",
                         "album": {
                           "id": "7fJJK56U9fHixgO0HQkhtI",
                           "name": "Future Nostalgia",
                           "uri": "spotify:album:7fJJK56U9fHixgO0HQkhtI",
                           "release_date": "2020-03-27",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e024bc66095f8a70bc4e6593f4f.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048514bc66095f8a70bc4e6593f4f.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6M2wZ9GZgrQXHCFfjv46we",
                             "name": "Dua Lipa",
                             "uri": "spotify:artist:6M2wZ9GZgrQXHCFfjv46we"
                           }
                         ]
                       },
                       {
                         "duration_ms": 241688,
                         "id": "2EiGECydkS2M8OCcRHQZhT",
                         "name": "Take Me To Church",
                         "popularity": 1,
                         "uri": "spotify:track:2EiGECydkS2M8OCcRHQZhT",
                         "album": {
                           "id": "6bcl0ZcPJWJtyuRsOyhvfv",
                           "name": "Hozier (Special Edition)",
                           "uri": "spotify:album:6bcl0ZcPJWJtyuRsOyhvfv",
                           "release_date": "2014-11-06",
                           "total_tracks": 26,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e029a7dfdafc9e4f8a5db391855.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048519a7dfdafc9e4f8a5db391855.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2FXC3k01G6Gw61bmprjgqS",
                             "name": "Hozier",
                             "uri": "spotify:artist:2FXC3k01G6Gw61bmprjgqS"
                           }
                         ]
                       },
                       {
                         "duration_ms": 244360,
                         "id": "3JvKfv6T31zO0ini8iNItO",
                         "name": "Another Love",
                         "popularity": 86,
                         "uri": "spotify:track:3JvKfv6T31zO0ini8iNItO",
                         "album": {
                           "id": "0Gf1yE895FKK4YWVRuAeg8",
                           "name": "Long Way Down (Deluxe)",
                           "uri": "spotify:album:0Gf1yE895FKK4YWVRuAeg8",
                           "release_date": "2013-06-24",
                           "total_tracks": 15,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e021917a0f3f4152622a040913f.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048511917a0f3f4152622a040913f.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2txHhyCwHjUEpJjWrEyqyX",
                             "name": "Tom Odell",
                             "uri": "spotify:artist:2txHhyCwHjUEpJjWrEyqyX"
                           }
                         ]
                       },
                       {
                         "duration_ms": 257266,
                         "id": "5edBgVtRD0fvWk140Sl21T",
                         "name": "Counting Stars",
                         "popularity": 2,
                         "uri": "spotify:track:5edBgVtRD0fvWk140Sl21T",
                         "album": {
                           "id": "1Cm6wsvnTlOXrfI9PkD8i4",
                           "name": "Native",
                           "uri": "spotify:album:1Cm6wsvnTlOXrfI9PkD8i4",
                           "release_date": "2013-01-01",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e020dcd4d3fb73a5d9ba449a5f3.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048510dcd4d3fb73a5d9ba449a5f3.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "5Pwc4xIPtQLFEnJriah9YJ",
                             "name": "OneRepublic",
                             "uri": "spotify:artist:5Pwc4xIPtQLFEnJriah9YJ"
                           }
                         ]
                       },
                       {
                         "duration_ms": 173666,
                         "id": "1Bk4mFK1shZUoHfYJMwqWp",
                         "name": "Wavy",
                         "popularity": 50,
                         "uri": "spotify:track:1Bk4mFK1shZUoHfYJMwqWp",
                         "album": {
                           "id": "5H80DTznH6q3sVcb7aKAid",
                           "name": "Rewrite",
                           "uri": "spotify:album:5H80DTznH6q3sVcb7aKAid",
                           "release_date": "2024-09-04",
                           "total_tracks": 6,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e022bf7608150318c6f01237cad.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048512bf7608150318c6f01237cad.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7htnZJTm5SZsmKRrnOL5mk",
                             "name": "Yerin",
                             "uri": "spotify:artist:7htnZJTm5SZsmKRrnOL5mk"
                           }
                         ]
                       },
                       {
                         "duration_ms": 258986,
                         "id": "1HNkqx9Ahdgi1Ixy2xkKkL",
                         "name": "Photograph",
                         "popularity": 84,
                         "uri": "spotify:track:1HNkqx9Ahdgi1Ixy2xkKkL",
                         "album": {
                           "id": "1xn54DMo2qIqBuMqHtUsFd",
                           "name": "x (Deluxe Edition)",
                           "uri": "spotify:album:1xn54DMo2qIqBuMqHtUsFd",
                           "release_date": "2014-06-21",
                           "total_tracks": 16,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0213b3e37318a0c247b550bccd.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485113b3e37318a0c247b550bccd.jpeg",
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
                         "duration_ms": 179984,
                         "id": "6krwVrqihThMtvID5LlaYj",
                         "name": "Like Em Country",
                         "popularity": 41,
                         "uri": "spotify:track:6krwVrqihThMtvID5LlaYj",
                         "album": {
                           "id": "4V6MpMUEtrtPvcSVkGLkLF",
                           "name": "Like Em Country",
                           "uri": "spotify:album:4V6MpMUEtrtPvcSVkGLkLF",
                           "release_date": "2024-08-23",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0259ab6f9a91636c62b92de5d1.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485159ab6f9a91636c62b92de5d1.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6Jsh7nTKdhKsVHP5zHooI0",
                             "name": "Cwby",
                             "uri": "spotify:artist:6Jsh7nTKdhKsVHP5zHooI0"
                           }
                         ]
                       },
                       {
                         "duration_ms": 239835,
                         "id": "285pBltuF7vW8TeWk8hdRR",
                         "name": "Lucid Dreams",
                         "popularity": 84,
                         "uri": "spotify:track:285pBltuF7vW8TeWk8hdRR",
                         "album": {
                           "id": "6tkjU4Umpo79wwkgPMV3nZ",
                           "name": "Goodbye & Good Riddance",
                           "uri": "spotify:album:6tkjU4Umpo79wwkgPMV3nZ",
                           "release_date": "2018-12-10",
                           "total_tracks": 17,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02f7db43292a6a99b21b51d5b4.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851f7db43292a6a99b21b51d5b4.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4MCBfE4596Uoi2O4DtmEMz",
                             "name": "Juice WRLD",
                             "uri": "spotify:artist:4MCBfE4596Uoi2O4DtmEMz"
                           }
                         ]
                       },
                       {
                         "duration_ms": 215280,
                         "id": "21jGcNKet2qwijlDFuPiPb",
                         "name": "Circles",
                         "popularity": 84,
                         "uri": "spotify:track:21jGcNKet2qwijlDFuPiPb",
                         "album": {
                           "id": "4g1ZRSobMefqF6nelkgibi",
                           "name": "Hollywood's Bleeding",
                           "uri": "spotify:album:4g1ZRSobMefqF6nelkgibi",
                           "release_date": "2019-09-06",
                           "total_tracks": 17,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e029478c87599550dd73bfa7e02.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048519478c87599550dd73bfa7e02.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "246dkjvS1zLTtiykXe5h60",
                             "name": "Post Malone",
                             "uri": "spotify:artist:246dkjvS1zLTtiykXe5h60"
                           }
                         ]
                       },
                       {
                         "duration_ms": 183372,
                         "id": "2snvV5XQw30jstCYlr1W5o",
                         "name": "Cool Girls Party",
                         "popularity": 41,
                         "uri": "spotify:track:2snvV5XQw30jstCYlr1W5o",
                         "album": {
                           "id": "34RRJGyPmQcrRts5vdcPXs",
                           "name": "Cool Girls Party",
                           "uri": "spotify:album:34RRJGyPmQcrRts5vdcPXs",
                           "release_date": "2024-10-17",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02fe9cc6e0607e0788e33b6c96.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851fe9cc6e0607e0788e33b6c96.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4ynlh5jcBUkhAvoBeICuDG",
                             "name": "Melanie Herrera",
                             "uri": "spotify:artist:4ynlh5jcBUkhAvoBeICuDG"
                           }
                         ]
                       },
                       {
                         "duration_ms": 198960,
                         "id": "6T8cJz5lAqGer9GUHGyelE",
                         "name": "God's Plan",
                         "popularity": 1,
                         "uri": "spotify:track:6T8cJz5lAqGer9GUHGyelE",
                         "album": {
                           "id": "2YDNDwQvsU0njt7Kq0xNRY",
                           "name": "Scary Hours",
                           "uri": "spotify:album:2YDNDwQvsU0njt7Kq0xNRY",
                           "release_date": "2018-01-20",
                           "total_tracks": 2,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02689b62c26ee70aa450cae449.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851689b62c26ee70aa450cae449.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "3TVXtAsR1Inumwj472S9r4",
                             "name": "Drake",
                             "uri": "spotify:artist:3TVXtAsR1Inumwj472S9r4"
                           }
                         ]
                       },
                       {
                         "duration_ms": 354320,
                         "id": "4u7EnebtmKWzUH433cf5Qv",
                         "name": "Bohemian Rhapsody - Remastered 2011",
                         "popularity": 78,
                         "uri": "spotify:track:4u7EnebtmKWzUH433cf5Qv",
                         "album": {
                           "id": "1GbtB4zTqAsyfZEsm1RZfx",
                           "name": "A Night At The Opera (2011 Remaster)",
                           "uri": "spotify:album:1GbtB4zTqAsyfZEsm1RZfx",
                           "release_date": "1975-11-21",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02e319baafd16e84f0408af2a0.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851e319baafd16e84f0408af2a0.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "1dfeR4HaWDbWqFHLkxsg1d",
                             "name": "Queen",
                             "uri": "spotify:artist:1dfeR4HaWDbWqFHLkxsg1d"
                           }
                         ]
                       },
                       {
                         "duration_ms": 194087,
                         "id": "2Fxmhks0bxGSBdJ92vM42m",
                         "name": "bad guy",
                         "popularity": 81,
                         "uri": "spotify:track:2Fxmhks0bxGSBdJ92vM42m",
                         "album": {
                           "id": "0S0KGZnfBGSIssfF54WSJh",
                           "name": "WHEN WE ALL FALL ASLEEP, WHERE DO WE GO?",
                           "uri": "spotify:album:0S0KGZnfBGSIssfF54WSJh",
                           "release_date": "2019-03-29",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0250a3147b4edd7701a876c6ce.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485150a3147b4edd7701a876c6ce.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6qqNVTkY8uBg9cP3Jd7DAH",
                             "name": "Billie Eilish",
                             "uri": "spotify:artist:6qqNVTkY8uBg9cP3Jd7DAH"
                           }
                         ]
                       },
                       {
                         "duration_ms": 281560,
                         "id": "34gCuhDGsG4bRPIf9bb02f",
                         "name": "Thinking out Loud",
                         "popularity": 81,
                         "uri": "spotify:track:34gCuhDGsG4bRPIf9bb02f",
                         "album": {
                           "id": "1xn54DMo2qIqBuMqHtUsFd",
                           "name": "x (Deluxe Edition)",
                           "uri": "spotify:album:1xn54DMo2qIqBuMqHtUsFd",
                           "release_date": "2014-06-21",
                           "total_tracks": 16,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0213b3e37318a0c247b550bccd.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485113b3e37318a0c247b550bccd.jpeg",
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
                         "duration_ms": 258342,
                         "id": "3bidbhpOYeV4knp8AIu8Xn",
                         "name": "Can't Hold Us (feat. Ray Dalton)",
                         "popularity": 76,
                         "uri": "spotify:track:3bidbhpOYeV4knp8AIu8Xn",
                         "album": {
                           "id": "76FXHQhTuT4QMIxfL09gX8",
                           "name": "The Heist",
                           "uri": "spotify:album:76FXHQhTuT4QMIxfL09gX8",
                           "release_date": "2012-10-09",
                           "total_tracks": 15,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0298a02fef3a8b1d80a0f164ec.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485198a02fef3a8b1d80a0f164ec.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "3JhNCzhSMTxs9WLGJJxWOY",
                             "name": "Macklemore",
                             "uri": "spotify:artist:3JhNCzhSMTxs9WLGJJxWOY"
                           },
                           {
                             "id": "4myTppRgh0rojLxx8RycOp",
                             "name": "Ryan Lewis",
                             "uri": "spotify:artist:4myTppRgh0rojLxx8RycOp"
                           },
                           {
                             "id": "6WLvgbfYXQPO396oJEYCsi",
                             "name": "Macklemore & Ryan Lewis",
                             "uri": "spotify:artist:6WLvgbfYXQPO396oJEYCsi"
                           },
                           {
                             "id": "4e0nWw2r4BoQSKPQ2zpU13",
                             "name": "Ray Dalton",
                             "uri": "spotify:artist:4e0nWw2r4BoQSKPQ2zpU13"
                           }
                         ]
                       },
                       {
                         "duration_ms": 203005,
                         "id": "3AMftFgLBMpeMnRZZ0hTF4",
                         "name": "Here and Now",
                         "popularity": 21,
                         "uri": "spotify:track:3AMftFgLBMpeMnRZZ0hTF4",
                         "album": {
                           "id": "2miDKWrLnn89yLOnyTDJwP",
                           "name": "Here and Now",
                           "uri": "spotify:album:2miDKWrLnn89yLOnyTDJwP",
                           "release_date": "2024-11-08",
                           "total_tracks": 5,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02d8226c986e613db18dc1846e.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851d8226c986e613db18dc1846e.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "1wt5n6vpRwMFEkRke3yow1",
                             "name": "What Matters The Most",
                             "uri": "spotify:artist:1wt5n6vpRwMFEkRke3yow1"
                           }
                         ]
                       },
                       {
                         "duration_ms": 266773,
                         "id": "3AJwUDP919kvQ9QcozQPxg",
                         "name": "Yellow",
                         "popularity": 89,
                         "uri": "spotify:track:3AJwUDP919kvQ9QcozQPxg",
                         "album": {
                           "id": "6ZG5lRT77aJ3btmArcykra",
                           "name": "Parachutes",
                           "uri": "spotify:album:6ZG5lRT77aJ3btmArcykra",
                           "release_date": "2000-07-10",
                           "total_tracks": 10,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e029164bafe9aaa168d93f4816a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048519164bafe9aaa168d93f4816a.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4gzpq5DPGxSnKTe4SA8HAU",
                             "name": "Coldplay",
                             "uri": "spotify:artist:4gzpq5DPGxSnKTe4SA8HAU"
                           }
                         ]
                       },
                       {
                         "duration_ms": 243836,
                         "id": "6gBFPUFcJLzWGx4lenP6h2",
                         "name": "goosebumps",
                         "popularity": 85,
                         "uri": "spotify:track:6gBFPUFcJLzWGx4lenP6h2",
                         "album": {
                           "id": "42WVQWuf1teDysXiOupIZt",
                           "name": "Birds In The Trap Sing McKnight",
                           "uri": "spotify:album:42WVQWuf1teDysXiOupIZt",
                           "release_date": "2016-09-16",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02f54b99bf27cda88f4a7403ce.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851f54b99bf27cda88f4a7403ce.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0Y5tJX1MQlPlqiwlOH1tJY",
                             "name": "Travis Scott",
                             "uri": "spotify:artist:0Y5tJX1MQlPlqiwlOH1tJY"
                           }
                         ]
                       },
                       {
                         "duration_ms": 215733,
                         "id": "2VxeLyX666F8uXCJ0dZF8B",
                         "name": "Shallow",
                         "popularity": 82,
                         "uri": "spotify:track:2VxeLyX666F8uXCJ0dZF8B",
                         "album": {
                           "id": "4sLtOBOzn4s3GDUv3c5oJD",
                           "name": "A Star Is Born Soundtrack",
                           "uri": "spotify:album:4sLtOBOzn4s3GDUv3c5oJD",
                           "release_date": "2018-10-05",
                           "total_tracks": 34,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02e2d156fdc691f57900134342.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851e2d156fdc691f57900134342.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "1HY2Jd0NmPuamShAr6KMms",
                             "name": "Lady Gaga",
                             "uri": "spotify:artist:1HY2Jd0NmPuamShAr6KMms"
                           },
                           {
                             "id": "4VIvfOurcf0vuLRxLkGnIG",
                             "name": "Bradley Cooper",
                             "uri": "spotify:artist:4VIvfOurcf0vuLRxLkGnIG"
                           }
                         ]
                       },
                       {
                         "duration_ms": 209565,
                         "id": "4iwLDA6XdWfkQnCzyqNjKq",
                         "name": "Imperfections",
                         "popularity": 37,
                         "uri": "spotify:track:4iwLDA6XdWfkQnCzyqNjKq",
                         "album": {
                           "id": "5xvrgR3xYOA7bmuS0NPhTf",
                           "name": "Because I'm Blonde",
                           "uri": "spotify:album:5xvrgR3xYOA7bmuS0NPhTf",
                           "release_date": "2024-11-15",
                           "total_tracks": 7,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0225cb922467100e5af7a3ac54.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485125cb922467100e5af7a3ac54.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7se3XiMaYONsvdAhOJczcV",
                             "name": "Izza",
                             "uri": "spotify:artist:7se3XiMaYONsvdAhOJczcV"
                           }
                         ]
                       },
                       {
                         "duration_ms": 178426,
                         "id": "1BxfuPKGuaTgP7aM0Bbdwr",
                         "name": "Cruel Summer",
                         "popularity": 89,
                         "uri": "spotify:track:1BxfuPKGuaTgP7aM0Bbdwr",
                         "album": {
                           "id": "1NAmidJlEaVgA3MpcPFYGq",
                           "name": "Lover",
                           "uri": "spotify:album:1NAmidJlEaVgA3MpcPFYGq",
                           "release_date": "2019-08-23",
                           "total_tracks": 18,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02e787cffec20aa2a396a61647.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851e787cffec20aa2a396a61647.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "06HL4z0CvFAxyc27GXpf02",
                             "name": "Taylor Swift",
                             "uri": "spotify:artist:06HL4z0CvFAxyc27GXpf02"
                           }
                         ]
                       },
                       {
                         "duration_ms": 233720,
                         "id": "3hB5DgAiMAQ4DzYbsMq1IT",
                         "name": "Love Yourself",
                         "popularity": 2,
                         "uri": "spotify:track:3hB5DgAiMAQ4DzYbsMq1IT",
                         "album": {
                           "id": "7fZH0aUAjY3ay25obOUf2a",
                           "name": "Purpose (Deluxe)",
                           "uri": "spotify:album:7fZH0aUAjY3ay25obOUf2a",
                           "release_date": "2015-11-13",
                           "total_tracks": 19,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02b6d9a4fbb0bd49f0f034aead.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851b6d9a4fbb0bd49f0f034aead.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "1uNFoZAHBGtllmzznpCI3s",
                             "name": "Justin Bieber",
                             "uri": "spotify:artist:1uNFoZAHBGtllmzznpCI3s"
                           }
                         ]
                       },
                       {
                         "duration_ms": 183956,
                         "id": "5XeFesFbtLpXzIVDNQP22n",
                         "name": "I Wanna Be Yours",
                         "popularity": 88,
                         "uri": "spotify:track:5XeFesFbtLpXzIVDNQP22n",
                         "album": {
                           "id": "78bpIziExqiI9qztvNFlQu",
                           "name": "AM",
                           "uri": "spotify:album:78bpIziExqiI9qztvNFlQu",
                           "release_date": "2013-09-09",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e024ae1c4c5c45aabe565499163.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048514ae1c4c5c45aabe565499163.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7Ln80lUS6He07XvHI8qqHH",
                             "name": "Arctic Monkeys",
                             "uri": "spotify:artist:7Ln80lUS6He07XvHI8qqHH"
                           }
                         ]
                       },
                       {
                         "duration_ms": 187146,
                         "id": "0tKcYR2II1VCQWT79i5NrW",
                         "name": "Thunder",
                         "popularity": 3,
                         "uri": "spotify:track:0tKcYR2II1VCQWT79i5NrW",
                         "album": {
                           "id": "5GlPAy2PRJW06GVFhKwGTl",
                           "name": "Evolve",
                           "uri": "spotify:album:5GlPAy2PRJW06GVFhKwGTl",
                           "release_date": "2017-06-23",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e027956bd9a3d7a15e4c2e37cc6.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048517956bd9a3d7a15e4c2e37cc6.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "53XhwfbYqKCa1cC15pYq2q",
                             "name": "Imagine Dragons",
                             "uri": "spotify:artist:53XhwfbYqKCa1cC15pYq2q"
                           }
                         ]
                       },
                       {
                         "duration_ms": 269560,
                         "id": "3U4isOIWM3VvDubwSI3y7a",
                         "name": "All of Me",
                         "popularity": 83,
                         "uri": "spotify:track:3U4isOIWM3VvDubwSI3y7a",
                         "album": {
                           "id": "4OTAx9un4e6NfoHuVRiOrC",
                           "name": "Love In The Future (Expanded Edition)",
                           "uri": "spotify:album:4OTAx9un4e6NfoHuVRiOrC",
                           "release_date": "2013-08-30",
                           "total_tracks": 20,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0294c9217a398f5174757c0c78.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485194c9217a398f5174757c0c78.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "5y2Xq6xcjJb2jVM54GHK3t",
                             "name": "John Legend",
                             "uri": "spotify:artist:5y2Xq6xcjJb2jVM54GHK3t"
                           }
                         ]
                       },
                       {
                         "duration_ms": 208211,
                         "id": "3hRV0jL3vUpRrcy398teAU",
                         "name": "The Night We Met",
                         "popularity": 88,
                         "uri": "spotify:track:3hRV0jL3vUpRrcy398teAU",
                         "album": {
                           "id": "4sD1qg4jwTZR4mvR4Iflk5",
                           "name": "Strange Trails",
                           "uri": "spotify:album:4sD1qg4jwTZR4mvR4Iflk5",
                           "release_date": "2015-04-06",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0217875a0610c23d8946454583.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485117875a0610c23d8946454583.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6ltzsmQQbmdoHHbLZ4ZN25",
                             "name": "Lord Huron",
                             "uri": "spotify:artist:6ltzsmQQbmdoHHbLZ4ZN25"
                           }
                         ]
                       },
                       {
                         "duration_ms": 216840,
                         "id": "3eoan9A3pDSzDZzDzh0Xrr",
                         "name": "On My Way",
                         "popularity": 42,
                         "uri": "spotify:track:3eoan9A3pDSzDZzDzh0Xrr",
                         "album": {
                           "id": "7hEWePP7uRtpwKbHFSr30N",
                           "name": "On My Way",
                           "uri": "spotify:album:7hEWePP7uRtpwKbHFSr30N",
                           "release_date": "2024-01-16",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e029f4b9e7a36aff40ce7fcc46a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048519f4b9e7a36aff40ce7fcc46a.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0XIjLSi3fr8On0Dwq208Lp",
                             "name": "Jay Putty",
                             "uri": "spotify:artist:0XIjLSi3fr8On0Dwq208Lp"
                           }
                         ]
                       },
                       {
                         "duration_ms": 260252,
                         "id": "2Ch7LmS7r2Gy2kc64wv3Bz",
                         "name": "Die For You",
                         "popularity": 78,
                         "uri": "spotify:track:2Ch7LmS7r2Gy2kc64wv3Bz",
                         "album": {
                           "id": "2ODvWsOgouMbaA5xf0RkJe",
                           "name": "Starboy",
                           "uri": "spotify:album:2ODvWsOgouMbaA5xf0RkJe",
                           "release_date": "2016-11-25",
                           "total_tracks": 18,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e024718e2b124f79258be7bc452.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048514718e2b124f79258be7bc452.jpeg",
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
                         "duration_ms": 247426,
                         "id": "6g1NlCpW7fgqDnWbCCDrHl",
                         "name": "Wake Me Up - Radio Edit",
                         "popularity": 4,
                         "uri": "spotify:track:6g1NlCpW7fgqDnWbCCDrHl",
                         "album": {
                           "id": "02h9kO2oLKnLtycgbElKsw",
                           "name": "True",
                           "uri": "spotify:album:02h9kO2oLKnLtycgbElKsw",
                           "release_date": "2013-01-01",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02d20bacc84d203cc330a5df75.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851d20bacc84d203cc330a5df75.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "1vCWHaC5f2uS3yhpwWbIA6",
                             "name": "Avicii",
                             "uri": "spotify:artist:1vCWHaC5f2uS3yhpwWbIA6"
                           }
                         ]
                       },
                       {
                         "duration_ms": 175200,
                         "id": "2Oehrcv4Kov0SuIgWyQY9e",
                         "name": "Demons",
                         "popularity": 20,
                         "uri": "spotify:track:2Oehrcv4Kov0SuIgWyQY9e",
                         "album": {
                           "id": "1vAEF8F0HoRFGiYOEeJXHW",
                           "name": "Night Visions (Deluxe)",
                           "uri": "spotify:album:1vAEF8F0HoRFGiYOEeJXHW",
                           "release_date": "2012-09-04",
                           "total_tracks": 20,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02dee648abe19dd6e10902c4ae.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851dee648abe19dd6e10902c4ae.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "53XhwfbYqKCa1cC15pYq2q",
                             "name": "Imagine Dragons",
                             "uri": "spotify:artist:53XhwfbYqKCa1cC15pYq2q"
                           }
                         ]
                       },
                       {
                         "duration_ms": 242253,
                         "id": "25khomWgBVamSdKw7hzm3l",
                         "name": "The Hills",
                         "popularity": 1,
                         "uri": "spotify:track:25khomWgBVamSdKw7hzm3l",
                         "album": {
                           "id": "28ZKQMoNBB0etKXZ97G2SN",
                           "name": "Beauty Behind The Madness",
                           "uri": "spotify:album:28ZKQMoNBB0etKXZ97G2SN",
                           "release_date": "2015-08-28",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02aac98daa18e4edf54d7a0a70.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851aac98daa18e4edf54d7a0a70.jpeg",
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
                         "duration_ms": 180142,
                         "id": "23ZFWDVtZgdKY2s509MR6Z",
                         "name": "Drive",
                         "popularity": 26,
                         "uri": "spotify:track:23ZFWDVtZgdKY2s509MR6Z",
                         "album": {
                           "id": "0zdbWPEhZGNBpCuaMijGc2",
                           "name": "Drive",
                           "uri": "spotify:album:0zdbWPEhZGNBpCuaMijGc2",
                           "release_date": "2024-11-08",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02d5f4b0cbf9cb8e1e5d5e4f2a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851d5f4b0cbf9cb8e1e5d5e4f2a.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "5cGGK7iteXrsLm0fPX7gfb",
                             "name": "Andrew Mijares",
                             "uri": "spotify:artist:5cGGK7iteXrsLm0fPX7gfb"
                           }
                         ]
                       },
                       {
                         "duration_ms": 202333,
                         "id": "3CRDbSIZ4r5MsZ0YwxuEkn",
                         "name": "Stressed Out",
                         "popularity": 83,
                         "uri": "spotify:track:3CRDbSIZ4r5MsZ0YwxuEkn",
                         "album": {
                           "id": "3cQO7jp5S9qLBoIVtbkSM1",
                           "name": "Blurryface",
                           "uri": "spotify:album:3cQO7jp5S9qLBoIVtbkSM1",
                           "release_date": "2015-05-15",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e022df0d98a423025032d0db1f7.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048512df0d98a423025032d0db1f7.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "3YQKmKGau1PzlVlkL1iodx",
                             "name": "Twenty One Pilots",
                             "uri": "spotify:artist:3YQKmKGau1PzlVlkL1iodx"
                           }
                         ]
                       },
                       {
                         "duration_ms": 242373,
                         "id": "1mea3bSkSGXuIRvnydlB5b",
                         "name": "Viva La Vida",
                         "popularity": 84,
                         "uri": "spotify:track:1mea3bSkSGXuIRvnydlB5b",
                         "album": {
                           "id": "1CEODgTmTwLyabvwd7HBty",
                           "name": "Viva La Vida or Death and All His Friends",
                           "uri": "spotify:album:1CEODgTmTwLyabvwd7HBty",
                           "release_date": "2008-05-26",
                           "total_tracks": 10,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02e21cc1db05580b6f2d2a3b6e.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851e21cc1db05580b6f2d2a3b6e.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4gzpq5DPGxSnKTe4SA8HAU",
                             "name": "Coldplay",
                             "uri": "spotify:artist:4gzpq5DPGxSnKTe4SA8HAU"
                           }
                         ]
                       },
                       {
                         "duration_ms": 292799,
                         "id": "68Dni7IE4VyPkTOH9mRWHr",
                         "name": "No Role Modelz",
                         "popularity": 84,
                         "uri": "spotify:track:68Dni7IE4VyPkTOH9mRWHr",
                         "album": {
                           "id": "0UMMIkurRUmkruZ3KGBLtG",
                           "name": "2014 Forest Hills Drive",
                           "uri": "spotify:album:0UMMIkurRUmkruZ3KGBLtG",
                           "release_date": "2014-12-09",
                           "total_tracks": 13,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02c6e0948bbb0681ff29cdbae8.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851c6e0948bbb0681ff29cdbae8.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6l3HvQ5sa6mXTsMTB19rO5",
                             "name": "J. Cole",
                             "uri": "spotify:artist:6l3HvQ5sa6mXTsMTB19rO5"
                           }
                         ]
                       },
                       {
                         "duration_ms": 175573,
                         "id": "3JN0Zx6KfsqtMnTyvI0iDX",
                         "name": "FIRE",
                         "popularity": 29,
                         "uri": "spotify:track:3JN0Zx6KfsqtMnTyvI0iDX",
                         "album": {
                           "id": "36c0HgBjBDp55yJs2ZGQ1P",
                           "name": "FIRE",
                           "uri": "spotify:album:36c0HgBjBDp55yJs2ZGQ1P",
                           "release_date": "2023-02-20",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e021b1d5361ab0fdb71b2d823be.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048511b1d5361ab0fdb71b2d823be.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0N0lbSVgiewWYgrkej6IL3",
                             "name": "MELINDA LINDNER",
                             "uri": "spotify:artist:0N0lbSVgiewWYgrkej6IL3"
                           }
                         ]
                       },
                       {
                         "duration_ms": 177000,
                         "id": "7ujx3NYtwO2LkmKGz59mXp",
                         "name": "HUMBLE.",
                         "popularity": 1,
                         "uri": "spotify:track:7ujx3NYtwO2LkmKGz59mXp",
                         "album": {
                           "id": "7wbJhbCvhPfbK1CLAkpq25",
                           "name": "DAMN.",
                           "uri": "spotify:album:7wbJhbCvhPfbK1CLAkpq25",
                           "release_date": "2017-04-14",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e029e0062560d8bcccca15d412a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048519e0062560d8bcccca15d412a.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                             "name": "Kendrick Lamar",
                             "uri": "spotify:artist:2YZyLoL8N0Wb9xBt1NhZWg"
                           }
                         ]
                       },
                       {
                         "duration_ms": 252866,
                         "id": "7DFNE7NO0raLIUbgzY2rzm",
                         "name": "Let Her Go",
                         "popularity": 4,
                         "uri": "spotify:track:7DFNE7NO0raLIUbgzY2rzm",
                         "album": {
                           "id": "2mylGx7w2Q3yhUyN8iEWOF",
                           "name": "All The Little Lights",
                           "uri": "spotify:album:2mylGx7w2Q3yhUyN8iEWOF",
                           "release_date": "2012-11-05",
                           "total_tracks": 20,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02cd318f88c2016b08fa9834f9.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851cd318f88c2016b08fa9834f9.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0gadJ2b9A4SKsB1RFkBb66",
                             "name": "Passenger",
                             "uri": "spotify:artist:0gadJ2b9A4SKsB1RFkBb66"
                           }
                         ]
                       },
                       {
                         "duration_ms": 178626,
                         "id": "6ocbgoVGwYJhOv1GgI9NsF",
                         "name": "7 rings",
                         "popularity": 82,
                         "uri": "spotify:track:6ocbgoVGwYJhOv1GgI9NsF",
                         "album": {
                           "id": "2fYhqwDWXjbpjaIJPEfKFw",
                           "name": "thank u, next",
                           "uri": "spotify:album:2fYhqwDWXjbpjaIJPEfKFw",
                           "release_date": "2019-02-08",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0256ac7b86e090f307e218e9c8.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485156ac7b86e090f307e218e9c8.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "66CXWjxzNUsdJxJ2JdwvnR",
                             "name": "Ariana Grande",
                             "uri": "spotify:artist:66CXWjxzNUsdJxJ2JdwvnR"
                           }
                         ]
                       },
                       {
                         "duration_ms": 222973,
                         "id": "003vvx7Niy0yvhvHt4a68B",
                         "name": "Mr. Brightside",
                         "popularity": 85,
                         "uri": "spotify:track:003vvx7Niy0yvhvHt4a68B",
                         "album": {
                           "id": "4piJq7R3gjUOxnYs6lDCTg",
                           "name": "Hot Fuss",
                           "uri": "spotify:album:4piJq7R3gjUOxnYs6lDCTg",
                           "release_date": "2004",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02ccdddd46119a4ff53eaf1f5d.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851ccdddd46119a4ff53eaf1f5d.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0C0XlULifJtAgn6ZNCW2eu",
                             "name": "The Killers",
                             "uri": "spotify:artist:0C0XlULifJtAgn6ZNCW2eu"
                           }
                         ]
                       },
                       {
                         "duration_ms": 290320,
                         "id": "7lQ8MOhq6IN2w8EYcFNSUk",
                         "name": "Without Me",
                         "popularity": 86,
                         "uri": "spotify:track:7lQ8MOhq6IN2w8EYcFNSUk",
                         "album": {
                           "id": "2cWBwpqMsDJC1ZUwz813lo",
                           "name": "The Eminem Show",
                           "uri": "spotify:album:2cWBwpqMsDJC1ZUwz813lo",
                           "release_date": "2002-05-26",
                           "total_tracks": 20,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e026ca5c90113b30c3c43ffb8f4.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048516ca5c90113b30c3c43ffb8f4.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7dGJo4pcD2V6oG8kP0tJRR",
                             "name": "Eminem",
                             "uri": "spotify:artist:7dGJo4pcD2V6oG8kP0tJRR"
                           }
                         ]
                       },
                       {
                         "duration_ms": 320626,
                         "id": "7MJQ9Nfxzh8LPZ9e9u68Fq",
                         "name": "Lose Yourself",
                         "popularity": 69,
                         "uri": "spotify:track:7MJQ9Nfxzh8LPZ9e9u68Fq",
                         "album": {
                           "id": "3CjuTytLZz3G9znXt2rJgU",
                           "name": "SHADYXV",
                           "uri": "spotify:album:3CjuTytLZz3G9znXt2rJgU",
                           "release_date": "2014-11-24",
                           "total_tracks": 28,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e023f66b5b49ccea004a5ef0db2.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048513f66b5b49ccea004a5ef0db2.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7dGJo4pcD2V6oG8kP0tJRR",
                             "name": "Eminem",
                             "uri": "spotify:artist:7dGJo4pcD2V6oG8kP0tJRR"
                           }
                         ]
                       },
                       {
                         "duration_ms": 213826,
                         "id": "0B7wvvmu9EISAwZnOpjhNI",
                         "name": "When I Was Your Man",
                         "popularity": 51,
                         "uri": "spotify:track:0B7wvvmu9EISAwZnOpjhNI",
                         "album": {
                           "id": "4xWulj18AGahlyuZPulaGe",
                           "name": "Unorthodox Jukebox",
                           "uri": "spotify:album:4xWulj18AGahlyuZPulaGe",
                           "release_date": "2012-12-05",
                           "total_tracks": 10,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0249055dce3554e72e82082980.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485149055dce3554e72e82082980.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0du5cEVh5yTK9QJze8zA0C",
                             "name": "Bruno Mars",
                             "uri": "spotify:artist:0du5cEVh5yTK9QJze8zA0C"
                           }
                         ]
                       },
                       {
                         "duration_ms": 272394,
                         "id": "5FVd6KXrgO9B3JPmC8OPst",
                         "name": "Do I Wanna Know?",
                         "popularity": 84,
                         "uri": "spotify:track:5FVd6KXrgO9B3JPmC8OPst",
                         "album": {
                           "id": "78bpIziExqiI9qztvNFlQu",
                           "name": "AM",
                           "uri": "spotify:album:78bpIziExqiI9qztvNFlQu",
                           "release_date": "2013-09-09",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e024ae1c4c5c45aabe565499163.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048514ae1c4c5c45aabe565499163.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7Ln80lUS6He07XvHI8qqHH",
                             "name": "Arctic Monkeys",
                             "uri": "spotify:artist:7Ln80lUS6He07XvHI8qqHH"
                           }
                         ]
                       },
                       {
                         "duration_ms": 220734,
                         "id": "47Slg6LuqLaX0VodpSCvPt",
                         "name": "Just the Way You Are",
                         "popularity": 81,
                         "uri": "spotify:track:47Slg6LuqLaX0VodpSCvPt",
                         "album": {
                           "id": "6J84szYCnMfzEcvIcfWMFL",
                           "name": "Doo-Wops & Hooligans",
                           "uri": "spotify:album:6J84szYCnMfzEcvIcfWMFL",
                           "release_date": "2010-05-11",
                           "total_tracks": 12,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02f60070dce96a2c1b70cf6ff0.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851f60070dce96a2c1b70cf6ff0.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0du5cEVh5yTK9QJze8zA0C",
                             "name": "Bruno Mars",
                             "uri": "spotify:artist:0du5cEVh5yTK9QJze8zA0C"
                           }
                         ]
                       },
                       {
                         "duration_ms": 242013,
                         "id": "5wANPM4fQCJwkGd4rN57mH",
                         "name": "drivers license",
                         "popularity": 82,
                         "uri": "spotify:track:5wANPM4fQCJwkGd4rN57mH",
                         "album": {
                           "id": "6s84u2TUpR3wdUv4NgKA2j",
                           "name": "SOUR",
                           "uri": "spotify:album:6s84u2TUpR3wdUv4NgKA2j",
                           "release_date": "2021-05-21",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02a91c10fe9472d9bd89802e5a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851a91c10fe9472d9bd89802e5a.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "1McMsnEElThX1knmY4oliG",
                             "name": "Olivia Rodrigo",
                             "uri": "spotify:artist:1McMsnEElThX1knmY4oliG"
                           }
                         ]
                       },
                       {
                         "duration_ms": 330000,
                         "id": "4c9ihNMojL2wOGglzQ2aCG",
                         "name": "How Many Times",
                         "popularity": 34,
                         "uri": "spotify:track:4c9ihNMojL2wOGglzQ2aCG",
                         "album": {
                           "id": "0C9bCu5Hi5vyEwPGUVeR7u",
                           "name": "How Many Times",
                           "uri": "spotify:album:0C9bCu5Hi5vyEwPGUVeR7u",
                           "release_date": "2021-02-10",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02a49b8680b90461d1639c147a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851a49b8680b90461d1639c147a.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6hejDZ8r5VhdPAdaQ3gNRf",
                             "name": "JJ & The Mood",
                             "uri": "spotify:artist:6hejDZ8r5VhdPAdaQ3gNRf"
                           }
                         ]
                       },
                       {
                         "duration_ms": 214846,
                         "id": "7ef4DlsgrMEH11cDZd32M6",
                         "name": "One Kiss (with Dua Lipa)",
                         "popularity": 78,
                         "uri": "spotify:track:7ef4DlsgrMEH11cDZd32M6",
                         "album": {
                           "id": "7GEzhoTiqcPYkOprWQu581",
                           "name": "One Kiss (with Dua Lipa)",
                           "uri": "spotify:album:7GEzhoTiqcPYkOprWQu581",
                           "release_date": "2018-04-06",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02d09f96d82310d4d77c14c108.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851d09f96d82310d4d77c14c108.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7CajNmpbOovFoOoasH2HaY",
                             "name": "Calvin Harris",
                             "uri": "spotify:artist:7CajNmpbOovFoOoasH2HaY"
                           },
                           {
                             "id": "6M2wZ9GZgrQXHCFfjv46we",
                             "name": "Dua Lipa",
                             "uri": "spotify:artist:6M2wZ9GZgrQXHCFfjv46we"
                           }
                         ]
                       },
                       {
                         "duration_ms": 187973,
                         "id": "48DKpTEVJ2pAjxQbWTad3q",
                         "name": "Treat You Better",
                         "popularity": 54,
                         "uri": "spotify:track:48DKpTEVJ2pAjxQbWTad3q",
                         "album": {
                           "id": "3wBabo4pmzsYjALMSKY7Iq",
                           "name": "Illuminate (Deluxe)",
                           "uri": "spotify:album:3wBabo4pmzsYjALMSKY7Iq",
                           "release_date": "2017-04-20",
                           "total_tracks": 16,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02ea3ef7697cfd5705b8f47521.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851ea3ef7697cfd5705b8f47521.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7n2wHs1TKAczGzO7Dd2rGr",
                             "name": "Shawn Mendes",
                             "uri": "spotify:artist:7n2wHs1TKAczGzO7Dd2rGr"
                           }
                         ]
                       },
                       {
                         "duration_ms": 178146,
                         "id": "4ZtFanR9U6ndgddUvNcjcG",
                         "name": "good 4 u",
                         "popularity": 82,
                         "uri": "spotify:track:4ZtFanR9U6ndgddUvNcjcG",
                         "album": {
                           "id": "6s84u2TUpR3wdUv4NgKA2j",
                           "name": "SOUR",
                           "uri": "spotify:album:6s84u2TUpR3wdUv4NgKA2j",
                           "release_date": "2021-05-21",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02a91c10fe9472d9bd89802e5a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851a91c10fe9472d9bd89802e5a.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "1McMsnEElThX1knmY4oliG",
                             "name": "Olivia Rodrigo",
                             "uri": "spotify:artist:1McMsnEElThX1knmY4oliG"
                           }
                         ]
                       },
                       {
                         "duration_ms": 166605,
                         "id": "3ee8Jmje8o58CHK66QrVC2",
                         "name": "SAD!",
                         "popularity": 79,
                         "uri": "spotify:track:3ee8Jmje8o58CHK66QrVC2",
                         "album": {
                           "id": "2Ti79nwTsont5ZHfdxIzAm",
                           "name": "?",
                           "uri": "spotify:album:2Ti79nwTsont5ZHfdxIzAm",
                           "release_date": "2018-03-16",
                           "total_tracks": 18,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02806c160566580d6335d1f16c.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851806c160566580d6335d1f16c.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "15UsOTVnJzReFVN1VCnxy4",
                             "name": "XXXTENTACION",
                             "uri": "spotify:artist:15UsOTVnJzReFVN1VCnxy4"
                           }
                         ]
                       },
                       {
                         "duration_ms": 205946,
                         "id": "0lYBSQXN6rCTvUZvg9S0lU",
                         "name": "Let Me Love You",
                         "popularity": 83,
                         "uri": "spotify:track:0lYBSQXN6rCTvUZvg9S0lU",
                         "album": {
                           "id": "02sEJTj1sye1JaqxqpcSCp",
                           "name": "Encore",
                           "uri": "spotify:album:02sEJTj1sye1JaqxqpcSCp",
                           "release_date": "2016-08-05",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02212d776c31027c511f0ee3bc.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851212d776c31027c511f0ee3bc.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "540vIaP2JwjQb9dm3aArA4",
                             "name": "DJ Snake",
                             "uri": "spotify:artist:540vIaP2JwjQb9dm3aArA4"
                           },
                           {
                             "id": "1uNFoZAHBGtllmzznpCI3s",
                             "name": "Justin Bieber",
                             "uri": "spotify:artist:1uNFoZAHBGtllmzznpCI3s"
                           }
                         ]
                       },
                       {
                         "duration_ms": 233901,
                         "id": "3B54sVLJ402zGa6Xm4YGNe",
                         "name": "Unforgettable",
                         "popularity": 84,
                         "uri": "spotify:track:3B54sVLJ402zGa6Xm4YGNe",
                         "album": {
                           "id": "4c2p3TdN7NcQfCXyueCNnC",
                           "name": "Jungle Rules",
                           "uri": "spotify:album:4c2p3TdN7NcQfCXyueCNnC",
                           "release_date": "2017-07-14",
                           "total_tracks": 18,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e028a31195a371b2233456f6c07.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048518a31195a371b2233456f6c07.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6vXTefBL93Dj5IqAWq6OTv",
                             "name": "French Montana",
                             "uri": "spotify:artist:6vXTefBL93Dj5IqAWq6OTv"
                           },
                           {
                             "id": "1zNqQNIdeOUZHb8zbZRFMX",
                             "name": "Swae Lee",
                             "uri": "spotify:artist:1zNqQNIdeOUZHb8zbZRFMX"
                           }
                         ]
                       },
                       {
                         "duration_ms": 200600,
                         "id": "7DSAEUvxU8FajXtRloy8M0",
                         "name": "Flowers",
                         "popularity": 87,
                         "uri": "spotify:track:7DSAEUvxU8FajXtRloy8M0",
                         "album": {
                           "id": "5DvJgsMLbaR1HmAI6VhfcQ",
                           "name": "Endless Summer Vacation",
                           "uri": "spotify:album:5DvJgsMLbaR1HmAI6VhfcQ",
                           "release_date": "2023-08-18",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02cd222052a2594be29a6616b5.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851cd222052a2594be29a6616b5.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "5YGY8feqx7naU7z4HrwZM6",
                             "name": "Miley Cyrus",
                             "uri": "spotify:artist:5YGY8feqx7naU7z4HrwZM6"
                           }
                         ]
                       },
                       {
                         "duration_ms": 216880,
                         "id": "60a0Rd6pjrkxjPbaKzXjfq",
                         "name": "In the End",
                         "popularity": 88,
                         "uri": "spotify:track:60a0Rd6pjrkxjPbaKzXjfq",
                         "album": {
                           "id": "6hPkbAV3ZXpGZBGUvL6jVM",
                           "name": "Hybrid Theory (Bonus Edition)",
                           "uri": "spotify:album:6hPkbAV3ZXpGZBGUvL6jVM",
                           "release_date": "2000",
                           "total_tracks": 15,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02e2f039481babe23658fc719a.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851e2f039481babe23658fc719a.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6XyY86QOPPrYVGvF9ch6wz",
                             "name": "Linkin Park",
                             "uri": "spotify:artist:6XyY86QOPPrYVGvF9ch6wz"
                           }
                         ]
                       },
                       {
                         "duration_ms": 119133,
                         "id": "7m9OqQk4RVRkw9JJdeAw96",
                         "name": "Jocelyn Flores",
                         "popularity": 80,
                         "uri": "spotify:track:7m9OqQk4RVRkw9JJdeAw96",
                         "album": {
                           "id": "5VdyJkLe3yvOs0l4xXbWp0",
                           "name": "17",
                           "uri": "spotify:album:5VdyJkLe3yvOs0l4xXbWp0",
                           "release_date": "2017-08-25",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02203c89bd4391468eea4cc3f5.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851203c89bd4391468eea4cc3f5.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "15UsOTVnJzReFVN1VCnxy4",
                             "name": "XXXTENTACION",
                             "uri": "spotify:artist:15UsOTVnJzReFVN1VCnxy4"
                           }
                         ]
                       },
                       {
                         "duration_ms": 253920,
                         "id": "1JSTJqkT5qHq8MDJnJbRE1",
                         "name": "Every Breath You Take",
                         "popularity": 86,
                         "uri": "spotify:track:1JSTJqkT5qHq8MDJnJbRE1",
                         "album": {
                           "id": "5W9OT0a5iZlBr83a9WMKFY",
                           "name": "Synchronicity (Remastered 2003)",
                           "uri": "spotify:album:5W9OT0a5iZlBr83a9WMKFY",
                           "release_date": "1983-06-17",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02c8e97cafeb2acb85b21a777e.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851c8e97cafeb2acb85b21a777e.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "5NGO30tJxFlKixkPSgXcFE",
                             "name": "The Police",
                             "uri": "spotify:artist:5NGO30tJxFlKixkPSgXcFE"
                           }
                         ]
                       },
                       {
                         "duration_ms": 206693,
                         "id": "0KKkJNfGyhkQ5aFogxQAPU",
                         "name": "That's What I Like",
                         "popularity": 89,
                         "uri": "spotify:track:0KKkJNfGyhkQ5aFogxQAPU",
                         "album": {
                           "id": "4PgleR09JVnm3zY1fW3XBA",
                           "name": "24K Magic",
                           "uri": "spotify:album:4PgleR09JVnm3zY1fW3XBA",
                           "release_date": "2016-11-17",
                           "total_tracks": 9,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02232711f7d66a1e19e89e28c5.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851232711f7d66a1e19e89e28c5.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0du5cEVh5yTK9QJze8zA0C",
                             "name": "Bruno Mars",
                             "uri": "spotify:artist:0du5cEVh5yTK9QJze8zA0C"
                           }
                         ]
                       },
                       {
                         "duration_ms": 214289,
                         "id": "2dpaYNEQHiRxtZbfNsse99",
                         "name": "Happier",
                         "popularity": 12,
                         "uri": "spotify:track:2dpaYNEQHiRxtZbfNsse99",
                         "album": {
                           "id": "78EicdHZr5XBWD7llEZ1Jh",
                           "name": "Happier",
                           "uri": "spotify:album:78EicdHZr5XBWD7llEZ1Jh",
                           "release_date": "2018-08-17",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0204bfd5a5fd5aa6ca648f66aa.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485104bfd5a5fd5aa6ca648f66aa.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "64KEffDW9EtZ1y2vBYgq8T",
                             "name": "Marshmello",
                             "uri": "spotify:artist:64KEffDW9EtZ1y2vBYgq8T"
                           },
                           {
                             "id": "7EQ0qTo7fWT7DPxmxtSYEc",
                             "name": "Bastille",
                             "uri": "spotify:artist:7EQ0qTo7fWT7DPxmxtSYEc"
                           }
                         ]
                       },
                       {
                         "duration_ms": 233653,
                         "id": "5g7sDjBhZ4I3gcFIpkrLuI",
                         "name": "Locked out of Heaven",
                         "popularity": 52,
                         "uri": "spotify:track:5g7sDjBhZ4I3gcFIpkrLuI",
                         "album": {
                           "id": "4xWulj18AGahlyuZPulaGe",
                           "name": "Unorthodox Jukebox",
                           "uri": "spotify:album:4xWulj18AGahlyuZPulaGe",
                           "release_date": "2012-12-05",
                           "total_tracks": 10,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0249055dce3554e72e82082980.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485149055dce3554e72e82082980.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0du5cEVh5yTK9QJze8zA0C",
                             "name": "Bruno Mars",
                             "uri": "spotify:artist:0du5cEVh5yTK9QJze8zA0C"
                           }
                         ]
                       },
                       {
                         "duration_ms": 182706,
                         "id": "7GX5flRQZVHRAGd6B4TmDO",
                         "name": "XO Tour Llif3",
                         "popularity": 81,
                         "uri": "spotify:track:7GX5flRQZVHRAGd6B4TmDO",
                         "album": {
                           "id": "733e1ZfktLSwj96X5rsMeE",
                           "name": "Luv Is Rage 2",
                           "uri": "spotify:album:733e1ZfktLSwj96X5rsMeE",
                           "release_date": "2017-08-25",
                           "total_tracks": 16,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02aab4824c720639a6a2d7d932.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851aab4824c720639a6a2d7d932.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4O15NlyKLIASxsJ0PrXPfz",
                             "name": "Lil Uzi Vert",
                             "uri": "spotify:artist:4O15NlyKLIASxsJ0PrXPfz"
                           }
                         ]
                       },
                       {
                         "duration_ms": 312820,
                         "id": "2xLMifQCjDGFmkHkpNLD9h",
                         "name": "SICKO MODE",
                         "popularity": 81,
                         "uri": "spotify:track:2xLMifQCjDGFmkHkpNLD9h",
                         "album": {
                           "id": "41GuZcammIkupMPKH2OJ6I",
                           "name": "ASTROWORLD",
                           "uri": "spotify:album:41GuZcammIkupMPKH2OJ6I",
                           "release_date": "2018-08-03",
                           "total_tracks": 17,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02072e9faef2ef7b6db63834a3.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851072e9faef2ef7b6db63834a3.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "0Y5tJX1MQlPlqiwlOH1tJY",
                             "name": "Travis Scott",
                             "uri": "spotify:artist:0Y5tJX1MQlPlqiwlOH1tJY"
                           }
                         ]
                       },
                       {
                         "duration_ms": 200786,
                         "id": "69bp2EbF7Q2rqc5N3ylezZ",
                         "name": "Sorry",
                         "popularity": 2,
                         "uri": "spotify:track:69bp2EbF7Q2rqc5N3ylezZ",
                         "album": {
                           "id": "7fZH0aUAjY3ay25obOUf2a",
                           "name": "Purpose (Deluxe)",
                           "uri": "spotify:album:7fZH0aUAjY3ay25obOUf2a",
                           "release_date": "2015-11-13",
                           "total_tracks": 19,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02b6d9a4fbb0bd49f0f034aead.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851b6d9a4fbb0bd49f0f034aead.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "1uNFoZAHBGtllmzznpCI3s",
                             "name": "Justin Bieber",
                             "uri": "spotify:artist:1uNFoZAHBGtllmzznpCI3s"
                           }
                         ]
                       },
                       {
                         "duration_ms": 176561,
                         "id": "4WjH9Bzt3kx7z8kl0awxh4",
                         "name": "Lean On (feat. M\\u00d8 & DJ Snake)",
                         "popularity": 1,
                         "uri": "spotify:track:4WjH9Bzt3kx7z8kl0awxh4",
                         "album": {
                           "id": "2XBnxKeRZi76u2iyGcMych",
                           "name": "Peace Is The Mission",
                           "uri": "spotify:album:2XBnxKeRZi76u2iyGcMych",
                           "release_date": "2015-05-29",
                           "total_tracks": 9,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e029f55dad3a63f72f1bc8d6f59.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048519f55dad3a63f72f1bc8d6f59.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "738wLrAtLtCtFOLvQBXOXp",
                             "name": "Major Lazer",
                             "uri": "spotify:artist:738wLrAtLtCtFOLvQBXOXp"
                           },
                           {
                             "id": "0bdfiayQAKewqEvaU6rXCv",
                             "name": "M\\u00d8",
                             "uri": "spotify:artist:0bdfiayQAKewqEvaU6rXCv"
                           },
                           {
                             "id": "540vIaP2JwjQb9dm3aArA4",
                             "name": "DJ Snake",
                             "uri": "spotify:artist:540vIaP2JwjQb9dm3aArA4"
                           }
                         ]
                       },
                       {
                         "duration_ms": 209320,
                         "id": "2ekn2ttSfGqwhhate0LSR0",
                         "name": "New Rules",
                         "popularity": 78,
                         "uri": "spotify:track:2ekn2ttSfGqwhhate0LSR0",
                         "album": {
                           "id": "01sfgrNbnnPUEyz6GZYlt9",
                           "name": "Dua Lipa (Deluxe)",
                           "uri": "spotify:album:01sfgrNbnnPUEyz6GZYlt9",
                           "release_date": "2017-06-02",
                           "total_tracks": 17,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02838698485511bd9108fadadc.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851838698485511bd9108fadadc.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6M2wZ9GZgrQXHCFfjv46we",
                             "name": "Dua Lipa",
                             "uri": "spotify:artist:6M2wZ9GZgrQXHCFfjv46we"
                           }
                         ]
                       },
                       {
                         "duration_ms": 150976,
                         "id": "2tfZPbEIwU2TdlRdtCiFoF",
                         "name": "Heartless",
                         "popularity": 34,
                         "uri": "spotify:track:2tfZPbEIwU2TdlRdtCiFoF",
                         "album": {
                           "id": "07WHsmKTEi9hZ7TVREUjCP",
                           "name": "Heartless",
                           "uri": "spotify:album:07WHsmKTEi9hZ7TVREUjCP",
                           "release_date": "2024-10-25",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02492086089a1cfa930c43db3d.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851492086089a1cfa930c43db3d.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2tDA2vvMMRpi5GZL0hzqqj",
                             "name": "Alexcis",
                             "uri": "spotify:artist:2tDA2vvMMRpi5GZL0hzqqj"
                           },
                           {
                             "id": "6cUpFVxDYWed9WxtC4QgC5",
                             "name": "Wallie the Sensei",
                             "uri": "spotify:artist:6cUpFVxDYWed9WxtC4QgC5"
                           },
                           {
                             "id": "2nmXKh1URuUD1DaIoWJ9ww",
                             "name": "TK RUN IT UP",
                             "uri": "spotify:artist:2nmXKh1URuUD1DaIoWJ9ww"
                           }
                         ]
                       },
                       {
                         "duration_ms": 199440,
                         "id": "7JJmb5XwzOO8jgpou264Ml",
                         "name": "There's Nothing Holdin' Me Back",
                         "popularity": 84,
                         "uri": "spotify:track:7JJmb5XwzOO8jgpou264Ml",
                         "album": {
                           "id": "3wBabo4pmzsYjALMSKY7Iq",
                           "name": "Illuminate (Deluxe)",
                           "uri": "spotify:album:3wBabo4pmzsYjALMSKY7Iq",
                           "release_date": "2017-04-20",
                           "total_tracks": 16,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02ea3ef7697cfd5705b8f47521.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851ea3ef7697cfd5705b8f47521.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7n2wHs1TKAczGzO7Dd2rGr",
                             "name": "Shawn Mendes",
                             "uri": "spotify:artist:7n2wHs1TKAczGzO7Dd2rGr"
                           }
                         ]
                       },
                       {
                         "duration_ms": 203064,
                         "id": "463CkQjx2Zk1yXoBuierM9",
                         "name": "Levitating (feat. DaBaby)",
                         "popularity": 66,
                         "uri": "spotify:track:463CkQjx2Zk1yXoBuierM9",
                         "album": {
                           "id": "04m06KhJUuwe1Q487puIud",
                           "name": "Levitating (feat. DaBaby)",
                           "uri": "spotify:album:04m06KhJUuwe1Q487puIud",
                           "release_date": "2020-10-01",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02c5e7aa639b2920099f8c3b94.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851c5e7aa639b2920099f8c3b94.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6M2wZ9GZgrQXHCFfjv46we",
                             "name": "Dua Lipa",
                             "uri": "spotify:artist:6M2wZ9GZgrQXHCFfjv46we"
                           },
                           {
                             "id": "4r63FhuTkUYltbVAg5TQnk",
                             "name": "DaBaby",
                             "uri": "spotify:artist:4r63FhuTkUYltbVAg5TQnk"
                           }
                         ]
                       },
                       {
                         "duration_ms": 301920,
                         "id": "4CeeEOM32jQcH3eN9Q2dGj",
                         "name": "Smells Like Teen Spirit",
                         "popularity": 81,
                         "uri": "spotify:track:4CeeEOM32jQcH3eN9Q2dGj",
                         "album": {
                           "id": "2UJcKiJxNryhL050F5Z1Fk",
                           "name": "Nevermind (Remastered)",
                           "uri": "spotify:album:2UJcKiJxNryhL050F5Z1Fk",
                           "release_date": "1991-09-26",
                           "total_tracks": 13,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02fbc71c99f9c1296c56dd51b6.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851fbc71c99f9c1296c56dd51b6.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6olE6TJLqED3rqDCT0FyPh",
                             "name": "Nirvana",
                             "uri": "spotify:artist:6olE6TJLqED3rqDCT0FyPh"
                           }
                         ]
                       },
                       {
                         "duration_ms": 202735,
                         "id": "6zSpb8dQRaw0M1dK8PBwQz",
                         "name": "Cold Heart - PNAU Remix",
                         "popularity": 79,
                         "uri": "spotify:track:6zSpb8dQRaw0M1dK8PBwQz",
                         "album": {
                           "id": "5D8Rdb09BkmHscEGSWAlA6",
                           "name": "Cold Heart (PNAU Remix)",
                           "uri": "spotify:album:5D8Rdb09BkmHscEGSWAlA6",
                           "release_date": "2021-08-13",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e029f5cce8304c42d3a5463fd23.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048519f5cce8304c42d3a5463fd23.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "3PhoLpVuITZKcymswpck5b",
                             "name": "Elton John",
                             "uri": "spotify:artist:3PhoLpVuITZKcymswpck5b"
                           },
                           {
                             "id": "6M2wZ9GZgrQXHCFfjv46we",
                             "name": "Dua Lipa",
                             "uri": "spotify:artist:6M2wZ9GZgrQXHCFfjv46we"
                           },
                           {
                             "id": "6n28c9qs9hNGriNa72b26u",
                             "name": "PNAU",
                             "uri": "spotify:artist:6n28c9qs9hNGriNa72b26u"
                           }
                         ]
                       },
                       {
                         "duration_ms": 172500,
                         "id": "2yRiJCQXmFu5gqY00gmQhQ",
                         "name": "Not Okay",
                         "popularity": 22,
                         "uri": "spotify:track:2yRiJCQXmFu5gqY00gmQhQ",
                         "album": {
                           "id": "24Vgo0VRPgVcXH8JFm2Ipt",
                           "name": "Not Okay",
                           "uri": "spotify:album:24Vgo0VRPgVcXH8JFm2Ipt",
                           "release_date": "2024-11-22",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02d66cd3607ec4b200081dad00.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851d66cd3607ec4b200081dad00.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "6Jsh7nTKdhKsVHP5zHooI0",
                             "name": "Cwby",
                             "uri": "spotify:artist:6Jsh7nTKdhKsVHP5zHooI0"
                           },
                           {
                             "id": "1QQxgPT4qgPLps6RFTLCjJ",
                             "name": "Rowlan",
                             "uri": "spotify:artist:1QQxgPT4qgPLps6RFTLCjJ"
                           }
                         ]
                       },
                       {
                         "duration_ms": 201000,
                         "id": "1mXVgsBdtIVeCLJnSnmtdV",
                         "name": "Too Good At Goodbyes",
                         "popularity": 81,
                         "uri": "spotify:track:1mXVgsBdtIVeCLJnSnmtdV",
                         "album": {
                           "id": "3TJz2UBNYJtlEly0sPeNrQ",
                           "name": "The Thrill Of It All (Special Edition)",
                           "uri": "spotify:album:3TJz2UBNYJtlEly0sPeNrQ",
                           "release_date": "2017-11-03",
                           "total_tracks": 14,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02005cd7d0ae87b081601f6cca.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851005cd7d0ae87b081601f6cca.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2wY79sveU1sp5g7SokKOiI",
                             "name": "Sam Smith",
                             "uri": "spotify:artist:2wY79sveU1sp5g7SokKOiI"
                           }
                         ]
                       },
                       {
                         "duration_ms": 172723,
                         "id": "2whoe0pPKh4JRn6eWSIqTV",
                         "name": "Stay With Me",
                         "popularity": 1,
                         "uri": "spotify:track:2whoe0pPKh4JRn6eWSIqTV",
                         "album": {
                           "id": "1tA4JdOpNQUyutfQtmMesJ",
                           "name": "In The Lonely Hour",
                           "uri": "spotify:album:1tA4JdOpNQUyutfQtmMesJ",
                           "release_date": "2014-01-01",
                           "total_tracks": 10,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e02c1c177e193b3ebf1a16e158f.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d00004851c1c177e193b3ebf1a16e158f.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "2wY79sveU1sp5g7SokKOiI",
                             "name": "Sam Smith",
                             "uri": "spotify:artist:2wY79sveU1sp5g7SokKOiI"
                           }
                         ]
                       },
                       {
                         "duration_ms": 153529,
                         "id": "6QkEV7Krf7WV2UdILlgN4Z",
                         "name": "Daydreaming",
                         "popularity": 46,
                         "uri": "spotify:track:6QkEV7Krf7WV2UdILlgN4Z",
                         "album": {
                           "id": "2s5mdldGq2eNpv5Ma9eUwu",
                           "name": "Daydreaming",
                           "uri": "spotify:album:2s5mdldGq2eNpv5Ma9eUwu",
                           "release_date": "2024-10-16",
                           "total_tracks": 1,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e020d407bcc0bb19834af3883d8.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048510d407bcc0bb19834af3883d8.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4fjAtF6VmMxQHxKI5C3HPO",
                             "name": "Bruklin",
                             "uri": "spotify:artist:4fjAtF6VmMxQHxKI5C3HPO"
                           }
                         ]
                       },
                       {
                         "duration_ms": 153946,
                         "id": "3OHfY25tqY28d16oZczHc8",
                         "name": "Kill Bill",
                         "popularity": 77,
                         "uri": "spotify:track:3OHfY25tqY28d16oZczHc8",
                         "album": {
                           "id": "07w0rG5TETcyihsEIZR3qG",
                           "name": "SOS",
                           "uri": "spotify:album:07w0rG5TETcyihsEIZR3qG",
                           "release_date": "2022-12-09",
                           "total_tracks": 23,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e0270dbc9f47669d120ad874ec1.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d0000485170dbc9f47669d120ad874ec1.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "7tYKF4w9nC0nq9CsPZTHyP",
                             "name": "SZA",
                             "uri": "spotify:artist:7tYKF4w9nC0nq9CsPZTHyP"
                           }
                         ]
                       },
                       {
                         "duration_ms": 217306,
                         "id": "1rfofaqEpACxVEHIZBJe6W",
                         "name": "Havana (feat. Young Thug)",
                         "popularity": 80,
                         "uri": "spotify:track:1rfofaqEpACxVEHIZBJe6W",
                         "album": {
                           "id": "2vD3zSQr8hNlg0obNel4TE",
                           "name": "Camila",
                           "uri": "spotify:album:2vD3zSQr8hNlg0obNel4TE",
                           "release_date": "2018-01-12",
                           "total_tracks": 11,
                           "images": [
                             {
                               "width": 640,
                               "height": 640,
                               "url": null
                             },
                             {
                               "url": "ab67616d00001e026eb0b9e73adcf04e4ed3eca4.jpeg",
                               "width": 300,
                               "height": 300
                             },
                             {
                               "url": "ab67616d000048516eb0b9e73adcf04e4ed3eca4.jpeg",
                               "width": 64,
                               "height": 64
                             }
                           ]
                         },
                         "artists": [
                           {
                             "id": "4nDoRrQiYLoBzwC5BhVJzF",
                             "name": "Camila Cabello",
                             "uri": "spotify:artist:4nDoRrQiYLoBzwC5BhVJzF"
                           },
                           {
                             "id": "50co4Is1HCEo8bhOyUWKpn",
                             "name": "Young Thug",
                             "uri": "spotify:artist:50co4Is1HCEo8bhOyUWKpn"
                           }
                         ]
                       }
                     ]
                """;
        songs = new ArrayList<>();
        objectMapper.readTree(jsonData).forEach(songs::add);
    }
}