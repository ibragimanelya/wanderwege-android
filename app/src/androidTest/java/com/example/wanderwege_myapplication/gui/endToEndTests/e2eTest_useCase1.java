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
public class e2eTest_useCase1 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void e2eTest_useCase1() {
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
                                5)))
                .atPosition(0);
        appCompatTextView.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.buttonBack),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.ss_guide_activity),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageView.perform(click());

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listOfSSeings),
                        childAtPosition(
                                withId(R.id.guide_linear_layout),
                                5)))
                .atPosition(1);
        appCompatTextView2.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.buttonBack),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.ss_guide_activity),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        DataInteraction appCompatTextView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listOfSSeings),
                        childAtPosition(
                                withId(R.id.guide_linear_layout),
                                5)))
                .atPosition(2);
        appCompatTextView3.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.buttonBack),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.ss_guide_activity),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        DataInteraction appCompatTextView4 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listOfSSeings),
                        childAtPosition(
                                withId(R.id.guide_linear_layout),
                                5)))
                .atPosition(3);
        appCompatTextView4.perform(click());

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.buttonBack),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.ss_guide_activity),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageView4.perform(click());
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
