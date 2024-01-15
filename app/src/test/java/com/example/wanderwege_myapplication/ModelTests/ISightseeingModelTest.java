package com.example.wanderwege_myapplication.ModelTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.wanderwege_myapplication.WanderwegeModel.SightseeingModel;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeingDao;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.SightseeingDao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class ISightseeingModelTest {
    @Mock
    private AppDataBase mockDb;
    @Mock
    private SightseeingDao mockSightseeingDao;
    @Mock
    private MarkOfSightseeingDao mockMarkDao;
    private SightseeingModel modelUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockDb.sightseeingDao()).thenReturn(mockSightseeingDao);
        when(mockDb.markOfSightseeingDao()).thenReturn(mockMarkDao);
        modelUnderTest = new SightseeingModel(mockDb);
    }

    @Test
    public void testGetSightseeingById() {
        int sightseeingId = 1;
        Sightseeing expectedSightseeing = new Sightseeing("Test Sightseeing", "Test Year", "Test Description", sightseeingId, "testImage");

        when(mockSightseeingDao.getSightseeingById(sightseeingId)).thenReturn(expectedSightseeing);

        Sightseeing result = modelUnderTest.getSightseeingById(sightseeingId);

        assertEquals(expectedSightseeing, result);
    }

    @Test
    public void testGetSightseeingNameById() {
        int sightseeingId = 1;
        String expectedName = "Test Sightseeing Name";

        when(mockSightseeingDao.getSightseeingNameById(sightseeingId)).thenReturn(expectedName);

        String result = modelUnderTest.getSightseeingNameById(sightseeingId);

        assertEquals(expectedName, result);
    }

    @Test
    public void testGetSightseeingNames() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        List<String> expectedNames = Arrays.asList("Sightseeing 1", "Sightseeing 2", "Sightseeing 3");

        when(mockSightseeingDao.getSightseeingNameById(1)).thenReturn("Sightseeing 1");
        when(mockSightseeingDao.getSightseeingNameById(2)).thenReturn("Sightseeing 2");
        when(mockSightseeingDao.getSightseeingNameById(3)).thenReturn("Sightseeing 3");

        List<String> result = modelUnderTest.getSightseeingNames(ids);

        assertEquals(expectedNames, result);
    }

    @Test
    public void testGetMarkOfSightseeingById() {
        int markId = 1;
        MarkOfSightseeing expectedMark = new MarkOfSightseeing(123.456, 789.123);

        when(mockMarkDao.getMarkOfSightseeingById(markId)).thenReturn(expectedMark);

        MarkOfSightseeing result = modelUnderTest.getMarkOfSightseeingById(markId);

        assertEquals(expectedMark, result);
    }

}
