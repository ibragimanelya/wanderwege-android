package com.example.wanderwege_myapplication.ModelTests;

import com.example.wanderwege_myapplication.WanderwegeModel.RatingModel;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Rating;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.RatingDao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;

public class IRatingModelTest {

    @Mock
    private AppDataBase mockDb;
    @Mock
    private RatingDao mockDao;
    private RatingModel modelUnderTest;
    private ExecutorService synchronousExecutorService;
    @Mock
    private ExecutorService mockExecutorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockDb.ratingDao()).thenReturn(mockDao);

        modelUnderTest = new RatingModel(mockDb);
        synchronousExecutorService = Executors.newSingleThreadExecutor();
        modelUnderTest.setExecutorService(synchronousExecutorService);
    }

    @Test
    public void testInsertRating_Success() {
        Rating mockRating = new Rating();
        Runnable onSuccess = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        modelUnderTest.insertRating(mockRating, onSuccess, onFailure);

        verify(mockDao, times(1)).insertRating(mockRating);
        verify(onSuccess, times(1)).run();
        verify(onFailure, never()).run();
    }

    @Test
    public void testInsertRating_Failure() {
        Rating mockRating = new Rating();
        Runnable onSuccess = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        doThrow(new RuntimeException()).when(mockDao).insertRating(any(Rating.class));

        try {
            modelUnderTest.insertRating(mockRating, onSuccess, onFailure);
        } catch (RuntimeException e) {
            // Expected exception
        }

        verify(mockDao, times(1)).insertRating(mockRating);
        verify(onSuccess, never()).run();
        verify(onFailure, times(1)).run();
    }

    @Test
    public void testCalculateAndUpdateAverageRating() {
        int wanderwegId = 1;
        List<Rating> ratings = Arrays.asList(new Rating("xyz", 5), new Rating("zyx", 3));
        double expectedAverage = ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);

        // Mock the behavior of getRatingsForWanderweg
        when(mockDao.getRatingsForWanderweg(wanderwegId)).thenReturn(ratings);

        // Call the method under test
        modelUnderTest.calculateAndUpdateAverageRating(wanderwegId);

        // Verify interactions
        verify(mockDao, times(1)).getRatingsForWanderweg(wanderwegId);
        verify(mockDao, times(1)).updateAverageRating(eq(wanderwegId), eq(expectedAverage));
    }

}
