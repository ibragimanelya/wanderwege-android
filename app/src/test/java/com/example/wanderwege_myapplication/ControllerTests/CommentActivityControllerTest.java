package com.example.wanderwege_myapplication.ControllerTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.example.wanderwege_myapplication.WanderwegeModel.ICommentModel;
import com.example.wanderwege_myapplication.WanderwegeController.CommentActivityController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Comment;

public class CommentActivityControllerTest {

    @Mock
    private ICommentModel mockModel;
    private CommentActivityController controllerUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controllerUnderTest = new CommentActivityController(mockModel);
    }

    @Test
    public void testSubmitComment_Success() {
        String name = "John";
        String commentText = "This is a test comment";

        Runnable onSuccess = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        controllerUnderTest.submitComment(name, commentText, onSuccess, onFailure);

        verify(mockModel, times(1)).insertComment(any(Comment.class), eq(onSuccess), eq(onFailure));
        verify(onSuccess, never()).run();
        verify(onFailure, never()).run();
    }

    @Test
    public void testSubmitComment_Failure() {
        String name = "";
        String commentText = "";

        Runnable onSuccess = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        controllerUnderTest.submitComment(name, commentText, onSuccess, onFailure);

        verify(mockModel, never()).insertComment(any(Comment.class), any(Runnable.class), any(Runnable.class));
        verify(onSuccess, never()).run();
        verify(onFailure, times(1)).run();
    }
}
