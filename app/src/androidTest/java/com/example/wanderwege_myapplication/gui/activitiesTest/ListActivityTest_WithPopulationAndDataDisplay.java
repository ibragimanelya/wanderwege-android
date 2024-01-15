package com.example.wanderwege_myapplication.gui.activitiesTest;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.WanderwegeController.IWanderwegController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;
import com.example.wanderwege_myapplication.gui.ListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@RunWith(AndroidJUnit4.class)
public class ListActivityTest_WithPopulationAndDataDisplay {
    @Mock
    private IWanderwegController mockController;

    List<Wanderweg> wanderwegList;

    Wanderweg wanderweg1;

    public ListActivityTest_WithPopulationAndDataDisplay() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wanderwegList = new ArrayList<>();
        wanderweg1 = Mockito.mock(Wanderweg.class);

        when(wanderweg1.getName()).thenReturn("Mock Name"); // Mock the getName() method to return a specific name
        when(wanderweg1.getDistance()).thenReturn("5 km");
        when(wanderweg1.getImageIdentifier()).thenReturn("mock_image"); // Ensure this corresponds to a valid drawable
        when(wanderweg1.getStartpunkt()).thenReturn(0);
        when(wanderweg1.getEndpunkt()).thenReturn(1);
        when(wanderweg1.getSightseeingIds()).thenReturn("1,2,3");
        wanderwegList.add(wanderweg1);

        when(mockController.getWanderwegDetails(wanderweg1.getId())).thenReturn(wanderweg1);
        doNothing().when(mockController).loadWanderwegsData();
        when(mockController.getWanderwegList()).thenReturn(wanderwegList);


        activityRule.getScenario().onActivity(activity -> {
            activity.setMockController(mockController);
        });

    }

    @Rule
    public ActivityScenarioRule<ListActivity> activityRule = new ActivityScenarioRule<>(ListActivity.class);

    @Test
    public void testRecyclerViewPopulationAndDataDisplay() {
        activityRule.getScenario().onActivity(activity -> {
            activity.setMockController(mockController);

            activity.updateWanderwegList(wanderwegList);
        });

        onView(ViewMatchers.withId(R.id.recycler_view_wanderweg_card)).check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view_wanderweg_card)).perform(RecyclerViewActions.scrollToPosition(0));

        onView(withId(R.id.text_guide_name)).check(matches(withText(wanderweg1.getName())));
        onView(withId(R.id.distance_in_km)).check(matches(withText(wanderweg1.getDistance())));
        onView(withId(R.id.startPunkt)).check(matches(withText(wanderweg1.getStartpunkt())));
        onView(withId(R.id.endPunkt)).check(matches(withText(wanderweg1.getEndpunkt())));
        onView(withId(R.id.image_wanderweg)).check(matches(withText(wanderweg1.getImageIdentifier())));
        onView(withId(R.id.text_sightseeing)).check(matches(withText(wanderweg1.getSightseeingIds())));
    }

}

