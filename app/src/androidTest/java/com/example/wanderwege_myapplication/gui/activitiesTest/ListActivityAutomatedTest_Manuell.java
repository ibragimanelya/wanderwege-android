package com.example.wanderwege_myapplication.gui.activitiesTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.gui.ListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ListActivityAutomatedTest_Manuell {
    @Rule
    public ActivityScenarioRule<ListActivity> activityRule = new ActivityScenarioRule<>(ListActivity.class);

    @Test
    public void testActivityLaunch() {
        onView(ViewMatchers.withId(R.id.page_with_list_activity)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_wanderweg_card)).check(matches(isDisplayed()));
    }

    @Test
    public void testUIElementsVisibility() {
        onView(withId(R.id.recycler_view_wanderweg_card)).check(matches(isDisplayed()));

        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonBack)).check(matches(isDisplayed()));
        onView(withId(R.id.image_wanderweg)).check(matches(isDisplayed()));
        onView(withId(R.id.text_guide_name)).check(matches(isDisplayed()));
        onView(withId(R.id.distance_in_km)).check(matches(isDisplayed()));
        onView(withId(R.id.text_start_finish)).check(matches(isDisplayed()));
        onView(withId(R.id.text_sightseeing)).check(matches(isDisplayed()));
        onView(withId(R.id.ratingButton)).check(matches(isDisplayed()));
        onView(withId(R.id.ratingButton_text)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextViews() {
        onView(withId(R.id.recycler_view_wanderweg_card)).check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view_wanderweg_card))
                .perform(RecyclerViewActions.scrollToPosition(0));

        onView(withId(R.id.title)).check(matches(withText("Wanderwege")));

        onView(withId(R.id.text_guide_name)).check(matches(withText(R.string.name_of_the_guide)));
        onView(withId(R.id.distance_in_km)).check(matches(withText("X km")));
        onView(withId(R.id.text_start_finish)).check(matches(withText("A: Start, B: Finish")));
        onView(withId(R.id.text_sightseeing)).check(matches(withText("Sightseeing 1, Sightseeing 2...")));
        onView(withId(R.id.text_sightseeing)).check(matches(withText("0.0")));
    }

    @Test
    public void testBackButton() {
        try (ActivityScenario<ListActivity> scenario = ActivityScenario.launch(ListActivity.class)) {
            onView(withId(R.id.buttonBack)).perform(click());
            scenario.onActivity(activity -> assertTrue(activity.isFinishing()));
        }
    }

}