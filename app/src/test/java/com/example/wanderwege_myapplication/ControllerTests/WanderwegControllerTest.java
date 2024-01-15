package com.example.wanderwege_myapplication.ControllerTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import android.content.Context;

import com.example.wanderwege_myapplication.WanderwegeModel.ISightseeingModel;
import com.example.wanderwege_myapplication.WanderwegeModel.IWanderwegModel;
import com.example.wanderwege_myapplication.WanderwegeController.WanderwegController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderwegDao;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.SightseeingDao;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.WanderwegDao;
import com.example.wanderwege_myapplication.gui.ListActivity;


public class WanderwegControllerTest {
    @Mock
    private Context mockContext;
    @Mock
    private ListActivity listActivity;
    @Mock
    private AppDataBase mockDb;
    @Mock
    private IWanderwegModel mockWanderwegModel;
    @Mock
    private WanderwegDao mockWanderwegDao;
    @Mock
    private SightseeingDao mockSightseeingDao;
    @Mock
    private MarkOfWanderwegDao mockMarkDao;
    @Mock
    private ISightseeingModel mockSightseeingModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    public void testGetSightseeingNameById() {
        when(mockDb.wanderwegDao()).thenReturn(mockWanderwegDao);
        when(mockDb.sightseeingDao()).thenReturn(mockSightseeingDao);
        when(mockDb.markOfWanderwegDao()).thenReturn(mockMarkDao);

        WanderwegController controller = new WanderwegController(listActivity, mockDb);

        int id = 1;
        String expectedName = "Random name";
        when(mockSightseeingModel.getSightseeingNameById(id)).thenReturn(expectedName);

        String result = controller.getSightseeingNameById(id);

        assertEquals(expectedName,result);
        verify(mockSightseeingModel).getSightseeingById(id);
    }

    @Test
    public void testGetSightseeingDetails() {
        // Arrange
        WanderwegController controller = new WanderwegController(mockWanderwegModel, mockSightseeingModel);

        int sightseeingId = 1;
        Sightseeing sightseeing = new Sightseeing("", "2023", "history", 1, "image" );
        when(mockSightseeingModel.getSightseeingById(sightseeingId)).thenReturn(sightseeing);

        // Act
        Sightseeing result = controller.getSightseeingDetails(sightseeingId);

        // Assert
        assertEquals(sightseeing, result);
        verify(mockSightseeingModel).getSightseeingById(sightseeingId);
    }

}
