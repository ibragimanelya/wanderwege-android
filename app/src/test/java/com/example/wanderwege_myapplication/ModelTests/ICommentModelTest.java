package com.example.wanderwege_myapplication.ModelTests;

import com.example.wanderwege_myapplication.WanderwegeModel.CommentModel;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Comment;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.CommentDao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ICommentModelTest {

    @Mock
    private AppDataBase mockAppDataBase;
    @Mock
    private CommentDao mockCommentDao;

    private CommentModel commentModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockAppDataBase.commentDao()).thenReturn(mockCommentDao);
        commentModel = new CommentModel(mockAppDataBase);
    }

    @Test
    public void insertComment_Success() throws InterruptedException {
        Comment comment = new Comment(); // Setup your comment
        doNothing().when(mockCommentDao).insertComment(comment);

        Runnable onSuccess = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        CountDownLatch latch = new CountDownLatch(1);
        Runnable wrappedOnSuccess = () -> {
            onSuccess.run();
            latch.countDown();
        };

        commentModel.insertComment(comment, wrappedOnSuccess, onFailure);

        latch.await(1, TimeUnit.SECONDS); // Wait for the executor to complete

        verify(onSuccess).run();
        verify(onFailure, never()).run();
    }


    @Test
    public void insertComment_Failure() throws InterruptedException {
        Comment comment = new Comment(); // Setup your comment
        doThrow(new RuntimeException()).when(mockCommentDao).insertComment(comment);

        Runnable onSuccess = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        CountDownLatch latch = new CountDownLatch(1);
        Runnable wrappedOnFailure = () -> {
            onFailure.run();
            latch.countDown();
        };

        commentModel.insertComment(comment, onSuccess, wrappedOnFailure);

        latch.await(1, TimeUnit.SECONDS); // Wait for the executor to complete

        verify(onFailure).run();
        verify(onSuccess, never()).run();
    }

}
