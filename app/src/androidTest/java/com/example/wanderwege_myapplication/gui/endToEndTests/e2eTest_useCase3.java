package com.example.wanderwege_myapplication.gui.endToEndTests;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.gui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class e2eTest_useCase3 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void e2eTest_useCase3() {
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

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.addComRatButton),
                        childAtPosition(
                                allOf(withId(R.id.guide_activity),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.rateExperience), withText("Rate your experience!\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comrat_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.name_rating),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rate_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("sdfghme"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.name_rating), withText("sdfghme"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rate_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.name_rating), withText("sdfghme"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rate_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.name_rating), withText("sdfghme"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rate_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.name_rating), withText("sdfghme"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rate_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.name_rating), withText("sdfghme"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rate_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("me"));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.name_rating), withText("me"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rate_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText7.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.name_rating), withText("me"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rate_page_activity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText8.perform(pressImeActionButton());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button5), withText("5 out of 5"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(androidx.appcompat.R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton4.perform(scrollTo(), click());
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
