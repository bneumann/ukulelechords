package com.newbucket.ui;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.newbucket.ukulelechords.MainActivity;
import com.newbucket.ukulelechords.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class CheckVisibility
{

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void startScreenIsShowing()
    {
        onView(withId(R.id.button_chord)).check(matches(isDisplayed()));
        onView(withId(R.id.button_harm)).check(matches(isDisplayed()));
        onView(withId(R.id.button_up)).check(matches(isDisplayed()));
        onView(withId(R.id.button_down)).check(matches(isDisplayed()));
        onView(withId(R.id.selected_chord_text)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfSwitchingSelectorsWorks()
    {
        onView(withId(R.id.button_chord)).perform(click());
        onView(withId(R.id.selected_chord_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button_chord)).perform(click());
        onView(withId(R.id.selected_chord_text)).check(matches(isDisplayed()));
        onView(withId(R.id.button_harm)).perform(click());
        onView(withId(R.id.selected_chord_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button_chord)).perform(click());
        onView(withId(R.id.selected_chord_text)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button_chord)).perform(click());
        onView(withId(R.id.selected_chord_text)).check(matches(isDisplayed()));
    }


}