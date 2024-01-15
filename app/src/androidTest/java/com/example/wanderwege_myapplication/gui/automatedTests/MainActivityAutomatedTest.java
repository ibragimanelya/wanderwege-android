package com.example.wanderwege_myapplication.gui.automatedTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.gui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityAutomatedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.buttonList), withText("CHOOSE FROM LIST"),
                        withParent(withParent(withId(R.id.main_page_activity))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.buttonMap), withText("CHOOSE FROM MAP"),
                        withParent(withParent(withId(R.id.main_page_activity))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.pageName), withText("Wanderwege"),
                        withParent(allOf(withId(R.id.main_page_activity),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        textView.check(matches(withText("Wanderwege")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.pageName), withText("Wanderwege"),
                        withParent(allOf(withId(R.id.main_page_activity),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        textView2.check(matches(withText("Wanderwege")));
    }
}
