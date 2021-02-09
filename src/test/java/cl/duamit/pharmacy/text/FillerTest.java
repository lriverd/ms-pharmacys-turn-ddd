package cl.duamit.pharmacy.text;

import cl.duamit.pharmacy.type.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is a container for unit tests for individual methods of the Filler class
 * @version 1.0.0 - 19 Oct 2020
 * @since 1.0.0 - 19 Oct 2020
 */
public class FillerTest {

    @Test
    public void testFiller() {
        assertEquals("abcd", Filler.build().fill("abcde", 'd', 4, Direction.LEFT));
        assertEquals("abcd", Filler.build().fill("abcde", 'd', 4, Direction.RIGHT));

        assertEquals("dabc", Filler.build().fill("abc", 'd', 4, Direction.LEFT));
        assertEquals("abcd", Filler.build().fill("abc", 'd', 4, Direction.RIGHT));
        assertEquals("dddd", Filler.build().fill("", 'd', 4, Direction.LEFT));
        assertEquals("dddd", Filler.build().fill("", 'd', 4, Direction.RIGHT));
        assertEquals(null, Filler.build().fill(null, 'd', 4, Direction.LEFT));
        assertEquals(null, Filler.build().fill(null, 'd', 4, Direction.RIGHT));
        assertEquals("dddd", Filler.build().fillCollect("", "d", "4", "RIGHT"));
    }
}
