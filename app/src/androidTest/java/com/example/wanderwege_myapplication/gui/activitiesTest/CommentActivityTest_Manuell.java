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

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.gui.CommentActivity;
import com.example.wanderwege_myapplication.gui.RateActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CommentActivityTest_Manuell {

    @Rule
    public ActivityScenarioRule<CommentActivity> activityRule = new ActivityScenarioRule<>(CommentActivity.class);

    @Test
    public void testActivityLaunch() {
        onView(ViewMatchers.withId(R.id.comment_page_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void testUIElementsVisibility() {
        onView(withId(R.id.buttonSubmit)).check(matches(isDisplayed()));
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.subtitle)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonBack)).check(matches(isDisplayed()));
        onView(withId(R.id.enter_name_in_comment_page)).check(matches(isDisplayed()));
        onView(withId(R.id.enter_comment_in_comment_page)).check(matches(isDisplayed()));

    }

    @Test
    public void testBackButton() {
        try (ActivityScenario<RateActivity> scenario = ActivityScenario.launch(RateActivity.class)) {
            onView(withId(R.id.buttonBack)).perform(click());
            scenario.onActivity(activity -> {
                assertTrue(activity.isFinishing());
            });
        }
    }

    @Test
    public void testEditTextInputs() {
        String name = "John Doe";
        String comment = "This is a test comment";

        onView(withId(R.id.enter_name_in_comment_page)).perform(typeText(name), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enter_comment_in_comment_page)).perform(typeText(comment), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.enter_name_in_comment_page)).check(matches(withText(name)));
        onView(withId(R.id.enter_comment_in_comment_page)).check(matches(withText(comment)));
    }

    @Test
    public void testSubmitButtonClickWithValidInput() {
        onView(withId(R.id.enter_name_in_comment_page)).perform(typeText("John Doe"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enter_comment_in_comment_page)).perform(typeText("Great experience!"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.buttonSubmit)).perform(click());

        onView(withText("Success")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));
        onView(withText("Your comment has been saved successfully!")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void testSubmitButtonClickWithInvalidInput() {
        onView(withId(R.id.enter_name_in_comment_page)).perform(typeText(""), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enter_comment_in_comment_page)).perform(typeText(""), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.buttonSubmit)).perform(click());

        onView(withText("Incomplete Information")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));
        onView(withText("Please enter both your name and your comment.")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void testDisplayOfInformation() {
        String expectedTextForSubmitButton = "Submit";
        String title = "Rating and comment";
        String subtitle = "Comment";
        String hintTextName = "Enter Name and Surname";
        String hintTextComment = "Leave your comment!";

        onView(withId(R.id.buttonSubmit)).check(matches(withText(expectedTextForSubmitButton)));
        onView(withId(R.id.title)).check(matches(withText(title)));
        onView(withId(R.id.subtitle)).check(matches(withText(subtitle)));
        onView(withHint(hintTextName)).check(matches(isFocusable()));
        onView(withHint(hintTextComment)).check(matches(isFocusable()));

    }
}