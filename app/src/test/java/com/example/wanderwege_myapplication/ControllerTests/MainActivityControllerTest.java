package com.example.wanderwege_myapplication.ControllerTests;

import com.example.wanderwege_myapplication.WanderwegeModel.ISightseeingModel;
import com.example.wanderwege_myapplication.WanderwegeModel.WanderwegModel;
import com.example.wanderwege_myapplication.WanderwegeController.MainActivityController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class MainActivityControllerTest {

    @Mock
    private AppDataBase mockDb;
    @Mock
    private ISightseeingModel mockSightseeingModel;
    @Mock
    private WanderwegModel mockWanderwegModel;
    @Mock
    private ExecutorService mockExecutorService;

    private MainActivityController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new MainActivityController(mockDb);
    }

    @Disabled
    @Test
    public void testLoadGeoPointsForSightseeings() {
        controller.loadGeoPointsForSightseeings();
        verify(mockSightseeingModel).addGeoPointsForSightseeings();
    }

    @Disabled
    @Test
    public void testLoadGeoPointsForWanderwegs() {
        controller.loadGeoPointsForWanderwegs();
        verify(mockWanderwegModel).addGeoPointsForWanderwegs();
    }

    @Disabled
    @Test
    public void testOnDestroy() {
        controller.onDestroy();
        verify(mockExecutorService).shutdown();
    }
}

