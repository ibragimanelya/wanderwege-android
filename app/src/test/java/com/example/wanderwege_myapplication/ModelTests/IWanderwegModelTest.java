package com.example.wanderwege_myapplication.ModelTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.wanderwege_myapplication.WanderwegeModel.WanderwegModel;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderweg;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderwegDao;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.SightseeingDao;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.WanderwegDao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class IWanderwegModelTest {

    @Mock
    private AppDataBase mockDb;
    @Mock
    private WanderwegDao mockWanderwegDao;
    @Mock
    private SightseeingDao mockSightseeingDao;
    @Mock
    private MarkOfWanderwegDao mockMarkDao;
    @Mock
    private ExecutorService mockExecutorService;
    private WanderwegModel modelUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockDb.wanderwegDao()).thenReturn(mockWanderwegDao);
        when(mockDb.sightseeingDao()).thenReturn(mockSightseeingDao);
        when(mockDb.markOfWanderwegDao()).thenReturn(mockMarkDao);

        modelUnderTest = new WanderwegModel(mockDb);
    }

    @Test
    public void testGetAllWanderwegs() {
        Sightseeing startPunkt = new Sightseeing("Sightseeing1", "2023", "history", 1, "image1");
        Sightseeing endPunkt = new Sightseeing("Sightseeing2", "2023", "history", 2, "image2");

        List<Wanderweg> expectedWanderwegs = Arrays.asList(
                new Wanderweg("Wanderweg 1", "Distance 1", Collections.emptyList(), startPunkt, endPunkt, "Duration 1", "Image 1", 1, Collections.emptyList()),
                new Wanderweg("Wanderweg 2", "Distance 2", Collections.emptyList(), startPunkt, endPunkt, "Duration 2", "Image 2", 2, Collections.emptyList())
        );

        when(mockWanderwegDao.getAllWanderwegs()).thenReturn(expectedWanderwegs);

        List<Wanderweg> result = modelUnderTest.getAllWanderwegs();

        assertEquals(expectedWanderwegs, result);
    }

    @Test
    public void testGetWanderwegById() {
        int wanderwegId = 1;
        List<Integer> sightseeingIdsList = Arrays.asList(1,2,3,4);
        Sightseeing startPunkt = new Sightseeing("Sightseeing1", "2023", "history", 1, "image1");
        Sightseeing endPunkt = new Sightseeing("Sightseeing2", "2023", "history", 2, "image2");
        List<Integer> markOfSightseeingIdsList = Arrays.asList(1,2,3,4);

        Wanderweg expectedWanderweg = new Wanderweg("Test Wanderweg", "Test Distance",sightseeingIdsList, startPunkt, endPunkt, "Time Duration", "Test Image", 1,markOfSightseeingIdsList);

        when(mockWanderwegDao.getWanderwegById(wanderwegId)).thenReturn(expectedWanderweg);

        Wanderweg result = modelUnderTest.getWanderwegById(wanderwegId);

        assertEquals(expectedWanderweg, result);
    }

    @Test
    public void testGetSightseeingIdsByWanderwegId() {
        int wanderwegId = 1;
        List<Integer> expectedIds = Arrays.asList(1, 2, 3);

        when(mockWanderwegDao.getSSingIdsByWanderwegId(wanderwegId)).thenReturn("1,2,3");

        List<Integer> result = modelUnderTest.getSightseeingIdsByWanderwegId(wanderwegId);

        assertEquals(expectedIds, result);
    }

    @Test
    public void testAddGeoPointsForWanderwegs() throws InterruptedException {
        // Use CountDownLatch to wait for the asynchronous task to complete
        CountDownLatch latch = new CountDownLatch(1);

        // Execute the task synchronously for testing
        doAnswer(invocation -> {
            invocation.getArgument(0, Runnable.class).run();
            latch.countDown();
            return null;
        }).when(mockExecutorService).execute(any(Runnable.class));

        modelUnderTest.addGeoPointsForWanderwegs();

        // Wait for the task to complete or timeout
        latch.await(1, TimeUnit.SECONDS);

        // Verify that insert was called the correct number of times with the correct parameters
        verify(mockMarkDao, times(4)).insert(any(MarkOfWanderweg.class)); // Adjust as needed for each GeoPoint
        // Repeat the above line for each GeoPoint you are testing
    }

}
