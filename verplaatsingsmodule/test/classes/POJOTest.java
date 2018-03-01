package classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class POJOTest
{

    @Test
    public void testMethode()
    {
        int a = 234;
        int b = 42346;
        int expected = a - b;
        POJO pojo = new POJO();

        assertEquals(expected, pojo.TestMethode(a, b));
    }
}