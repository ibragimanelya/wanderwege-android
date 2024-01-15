package com.example.wanderwege_myapplication.ModelTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.wanderwege_myapplication.WanderwegeModel.MarkOfWanderwegModel;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderweg;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderwegDao;

public class IMarkOfWanderwegModelTest {

    @Mock
    private AppDataBase mockDb;
    @Mock
    private MarkOfWanderwegDao mockDao;
    private MarkOfWanderwegModel modelUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockDb.markOfWanderwegDao()).thenReturn(mockDao);
        modelUnderTest = new MarkOfWanderwegModel(mockDb);
    }

    @Test
    public void testGetAllWanderwegsMarks() {
        List<MarkOfWanderweg> expectedMarks = new ArrayList<>();
        expectedMarks.add(new MarkOfWanderweg(1,2));
        expectedMarks.add(new MarkOfWanderweg(3,4));
        expectedMarks.add(new MarkOfWanderweg(5,6));

        when(mockDao.getAllWanderwegsMarks()).thenReturn(expectedMarks);

        List<MarkOfWanderweg> actualMarks = modelUnderTest.getAllWanderwegsMarks();

        assertEquals(expectedMarks, actualMarks);
    }
}
