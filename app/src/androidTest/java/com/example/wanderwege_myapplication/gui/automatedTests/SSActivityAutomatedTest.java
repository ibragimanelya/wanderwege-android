package com.example.wanderwege_myapplication.gui.automatedTests;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SSActivityAutomatedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void sSActivityAutomatedTest() {
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

        ViewInteraction imageView = onView(
                allOf(withId(R.id.buttonBack),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.ss_guide_activity)))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.title_template), withText("Brandenburg Gate"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.ss_guide_activity)))),
                        isDisplayed()));
        textView.check(matches(withText("Brandenburg Gate")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.titleOfSSeing), withText("Brandenburg Gate"),
                        withParent(allOf(withId(R.id.linear_layout_ssguide),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Brandenburg Gate")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.buildYear), withText("Built in: Late 18th century"),
                        withParent(allOf(withId(R.id.linear_layout_ssguide),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        textView3.check(matches(withText("Built in: Late 18th century")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.briefHistory_title), withText("Brief History:\n"),
                        withParent(allOf(withId(R.id.linear_layout_ssguide),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        textView4.check(matches(withText("Brief History:\n")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.briefHistory_title), withText("Brief History:\n"),
                        withParent(allOf(withId(R.id.linear_layout_ssguide),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.briefHistory), withText("Symbolizes German unity and peace"),
                        withParent(allOf(withId(R.id.linear_layout_ssguide),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        textView6.check(matches(withText("Symbolizes German unity and peace")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.briefHistory), withText("Symbolizes German unity and peace"),
                        withParent(allOf(withId(R.id.linear_layout_ssguide),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        textView7.check(matches(isDisplayed()));

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
