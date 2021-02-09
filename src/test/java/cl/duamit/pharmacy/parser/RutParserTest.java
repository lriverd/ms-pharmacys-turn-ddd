package cl.duamit.pharmacy.parser;

import cl.duamit.shared.entitie.Rut;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RutParserTest {

    @Test
    public void testParseString() throws Exception {
        Rut rut = new Rut(11111111, '1');
        Assert.assertEquals(null, RutParser.build().parse(""));
        Assert.assertEquals(null, RutParser.build().parse(null));
        assertNotSame(rut, RutParser.build().parse("11.111.111-1"));
    }
}
