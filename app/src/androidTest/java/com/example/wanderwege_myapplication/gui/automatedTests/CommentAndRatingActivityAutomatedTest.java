package com.example.wanderwege_myapplication.gui.automatedTests;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.GrantPermissionRule;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.gui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CommentAndRatingActivityAutomatedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void rateActivityAutomatedTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonList), withText("Choose from list"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view_wanderweg_card),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.listOfSSeings),
                        childAtPosition(
                                withId(R.id.guide_linear_layout),
                                3)))
                .atPosition(0);
        appCompatTextView.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.buttonBack), withContentDescription("description\n"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.comrat_page_activity)))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.pageName), withText("Rating and comment"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.comrat_page_activity)))),
                        isDisplayed()));
        textView.check(matches(withText("Rating and comment")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.pageName), withText("Rating and comment"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.comrat_page_activity)))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.rateExperience), withText("RATE YOUR EXPERIENCE!\n"),
                        withParent(withParent(withId(R.id.comrat_page_activity))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.leaveComment), withText("LEAVE A COMMENT!"),
                        withParent(withParent(withId(R.id.comrat_page_activity))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
