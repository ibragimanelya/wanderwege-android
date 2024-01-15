package com.example.wanderwege_myapplication.ControllerTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.example.wanderwege_myapplication.WanderwegeModel.IRatingModel;
import com.example.wanderwege_myapplication.WanderwegeController.RateActivityController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Rating;

public class RateActivityControllerTest {

    @Mock
    private IRatingModel mockRatingModel;
    private RateActivityController controllerUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controllerUnderTest = new RateActivityController(mockRatingModel);
    }

    @Test
    public void testSubmitRating_Success() {
        String name = "John";
        int ratingValue = 5;
        int wanderwegId = 1;

        // Capture the onSuccess Runnable passed to insertRating
        ArgumentCaptor<Runnable> onSuccessCaptor = ArgumentCaptor.forClass(Runnable.class);

        Runnable onFailure = mock(Runnable.class);

        controllerUnderTest.submitRating(name, ratingValue, wanderwegId, mock(Runnable.class), onFailure);

        verify(mockRatingModel, times(1)).insertRating(any(Rating.class), onSuccessCaptor.capture(), eq(onFailure));

        // Manually invoke the captured onSuccess Runnable
        onSuccessCaptor.getValue().run();

        // Verify that calculateAndUpdateAverageRating is now invoked
        verify(mockRatingModel, times(1)).calculateAndUpdateAverageRating(eq(wanderwegId));
    }

    @Test
    public void testSubmitRating_Failure() {
        String name = "";
        int ratingValue = 5;
        int wanderwegId = -1;

        Runnable onSuccess = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        controllerUnderTest.submitRating(name, ratingValue, wanderwegId, onSuccess, onFailure);

        verify(mockRatingModel, never()).insertRating(any(Rating.class), any(Runnable.class), any(Runnable.class));
        verify(mockRatingModel, never()).calculateAndUpdateAverageRating(anyInt());
        verify(onSuccess, never()).run();
        verify(onFailure, times(1)).run();
    }
}
