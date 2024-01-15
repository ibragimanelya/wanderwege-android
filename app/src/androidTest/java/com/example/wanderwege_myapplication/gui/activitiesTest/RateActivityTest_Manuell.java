package com.example.wanderwege_myapplication.gui.activitiesTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

import android.content.Intent;
import android.os.Bundle;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.gui.RateActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RateActivityTest_Manuell {

    static Intent intent;
    static {
        intent = new Intent(ApplicationProvider.getApplicationContext(), RateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("WANDERWEG_ID", 1);
        intent.putExtras(bundle);
    }

    @Rule
    public ActivityScenarioRule<RateActivity> activityScenarioRule = new ActivityScenarioRule<>(intent);

    @Test
    public void testActivityLaunch() {
        onView(withId(R.id.rate_page_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void testUIElementsVisibility() {
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
        onView(withId(R.id.button5)).check(matches(isDisplayed()));
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.subtitle)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonBack)).check(matches(isDisplayed()));
        onView(withId(R.id.name_rating)).check(matches(isDisplayed()));
    }

    @Test
    public void testBackButton() {
        try (ActivityScenario<RateActivity> scenario = ActivityScenario.launch(RateActivity.class)) {
            onView(withId(R.id.buttonBack)).perform(click());
            scenario.onActivity(activity -> assertTrue(activity.isFinishing()));
        }
    }

    @Test
    public void testEditTextInputs() {
        String name = "John Doe";

        onView(withId(R.id.name_rating)).perform(typeText(name), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.name_rating)).check(matches(withText(name)));
    }

    @Test
    public void testSubmitButtonClickWithValidInput() {
        onView(withId(R.id.name_rating)).perform(typeText("John Doe"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button1)).perform(click());

        onView(withText("Success")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));
        onView(withText("Your rating has been saved successfully!")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void testSubmitButtonClickWithInvalidInput() {
        onView(withId(R.id.button1)).perform(click());
        onView(withText("Incomplete Information")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));
        onView(withText("Please enter both your name and your comment.")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void testDisplayOfInformation() {
        String expectedTextForRatingButton1 = "1 out of 5";
        String expectedTextForRatingButton2 = "2 out of 5";
        String expectedTextForRatingButton3 = "3 out of 5";
        String expectedTextForRatingButton4 = "4 out of 5";
        String expectedTextForRatingButton5 = "5 out of 5";

        String title = "Rating and comment";
        String subtitle = "Rating";

        onView(withId(R.id.button1)).check(matches(withText(expectedTextForRatingButton1)));
        onView(withId(R.id.button2)).check(matches(withText(expectedTextForRatingButton2)));
        onView(withId(R.id.button3)).check(matches(withText(expectedTextForRatingButton3)));
        onView(withId(R.id.button4)).check(matches(withText(expectedTextForRatingButton4)));
        onView(withId(R.id.button5)).check(matches(withText(expectedTextForRatingButton5)));

        onView(withId(R.id.title)).check(matches(withText(title)));
        onView(withId(R.id.subtitle)).check(matches(withText(subtitle)));
        onView(withHint("Enter Name and Surname")).check(matches(isFocusable()));
    }

}
