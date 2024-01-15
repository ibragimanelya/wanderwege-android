package com.example.wanderwege_myapplication.gui.activitiesTest;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertTrue;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.GrantPermissionRule;


import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.gui.GuideActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GuideActivityTest_Manuelll {

    @Rule
    public ActivityScenarioRule<GuideActivity> activityRule = new ActivityScenarioRule<>(GuideActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(ACCESS_FINE_LOCATION, WRITE_EXTERNAL_STORAGE);

    @Test
    public void testActivityLaunch() {
        onView(ViewMatchers.withId(R.id.guide_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void testUIElementsVisibility() {
        onView(withId(R.id.title_template)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonBack)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.guide_linear_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.titleOfGuide)).check(matches(isDisplayed()));
        onView(withId(R.id.distance)).check(matches(isDisplayed()));
        onView(withId(R.id.startPunkt)).check(matches(isDisplayed()));
        onView(withId(R.id.endPunkt)).check(matches(isDisplayed()));
        onView(withId(R.id.list_title)).check(matches(isDisplayed()));
        onView(withId(R.id.listOfSSeings)).check(matches(isDisplayed()));
        onView(withId(R.id.addComRatButton)).check(matches(isDisplayed()));
        onView(withId(R.id.mapView)).check(matches(isDisplayed()));

    }

    @Test
    public void testBackButton() {
        try (ActivityScenario<GuideActivity> scenario = ActivityScenario.launch(GuideActivity.class)) {
            onView(withId(R.id.buttonBack)).perform(click());
            scenario.onActivity(activity -> assertTrue(activity.isFinishing()));
        }
    }

    @Test
    public void testFloatingActionButtonClick() {
        onView(withId(R.id.addComRatButton)).perform(click());
        onView(withId(R.id.comrat_page_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextViews() {
        onView(withId(R.id.title_template)).check(matches(withText("Name of the Guide")));
        onView(withId(R.id.titleOfGuide)).check(matches(withText("SSing_name")));
        onView(withId(R.id.distance)).check(matches(withText("Distance: X km")));
        onView(withId(R.id.startPunkt)).check(matches(withText(R.string.startPunkt)));
        onView(withId(R.id.endPunkt)).check(matches(withText(R.string.endPunkt)));
        onView(withId(R.id.list_title)).check(matches(withText(R.string.listTitle)));
    }
}
