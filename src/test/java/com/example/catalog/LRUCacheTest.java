package com.example.catalog;

import com.example.catalog.utils.LRUCache;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;



class LRUCacheTest {

    LRUCache<String, String> cache;

    @Nested
    @DisplayName("when instantiated with capacity 3")
    class WhenInstantiated {

        @BeforeEach
        void createNewCache() {
            cache = new LRUCache<>(3);
        }

        @Test
        @DisplayName("cache is initially empty")
        void isEmpty() {
            // TODO assert cache is empty
            assertTrue(cache.isEmpty());
        }

        @Test
        @DisplayName("throws NullPointerException when getting a null key")
        void throwsExceptionWhenGettingNullKey() {
            // TODO assert NullPointerException thrown on `cache.get(null)`
            assertThrows(NullPointerException.class,() -> cache.get(null));
        }
        @Test
        @DisplayName("throws NullPointerException when setting a null key")
        void throwsExceptionWhenSettingNullKey() {
            // TODO assert NullPointerException thrown on `cache.get(null)`
            assertThrows(NullPointerException.class,() -> cache.set(null,"val"));
        }
        @Nested
        @DisplayName("after adding 2 elements")
        class AfterAdding2Elements {

            @BeforeEach
            void addElements() {
                // TODO add 2 elements
                cache.set("k1","v1");
                cache.set("k2","v2");
            }

            @Test
            @DisplayName("cache contains the added elements")
            void containsAddedElements() {
                // TODO assert the added 2 elements are available
                assertEquals(2,cache.size());
                assertTrue(cache.containsKey("k1"));
                assertTrue(cache.containsKey("k2"));
                assertEquals("v1",cache.get("k1"));
                assertEquals("v2",cache.get("k2"));
                assertFalse(cache.containsKey("k3"));
            }

            @Test
            @DisplayName("cache can the add one more element")
            void AddOneMoreElement() {
                cache.set("k3","v3");
                assertEquals(3,cache.size());
                assertTrue(cache.containsKey("k3"));
                assertEquals("v3",cache.get("k3"));
            }

        }

        @Nested
        @DisplayName("after adding 3 elements")
        class AfterAdding3Elements {

            @BeforeEach
            void addElements() {
                // TODO add 3 elements
                cache.set("k1","v1");
                cache.set("k2","v2");
                cache.set("k3","v3");
            }

            @Test
            @DisplayName("cache add element and delete the lru element")
            void AddOneMoreElement() {
                cache.set("k4","v4");
                assertEquals(3,cache.size()); // validate the size is 3
                assertTrue(cache.containsKey("k4"));
                assertEquals("v4",cache.get("k4"));
                assertFalse(cache.containsKey("k1"));
            }



            @Nested
            @DisplayName("when cleared")
            class WhenCleared {

                // addElements (in AfterAdding3Elements) is executed and then clearCache
                // before EACH test case in WhenCleared


                @BeforeEach
                void clearCache() {
                    // TODO clear the cache after
                    cache.clear();
                }

                @Test
                @DisplayName("cache is empty")
                void CacheIsEmpty() {
                    assertTrue(cache.isEmpty());
                }

                @Test
                @DisplayName("cache size is one after clear and addind one element")
                void AddElementFail() {
                    cache.set("k4","v4");
                    assertEquals(1,cache.size());
                }
            }
        }

    }
}
