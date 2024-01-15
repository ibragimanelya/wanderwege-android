package com.example.wanderwege_myapplication;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.Converters;

public class ConvertersTest {

    @Test
    public void testToIdsListFromStrings() {
        String idsString = "1,2,3,4";
        List<Integer> expectedList = Arrays.asList(1, 2, 3, 4);

        List<Integer> result = Converters.toIdsListFromStrings(idsString);

        assertEquals(expectedList, result);
    }

    @Test
    public void testToIdsListFromEmptyString() {
        String idsString = "";

        List<Integer> result = Converters.toIdsListFromStrings(idsString);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testToIdsListFromNullString() {
        List<Integer> result = Converters.toIdsListFromStrings(null);

        assertTrue(result.isEmpty());
    }
}

