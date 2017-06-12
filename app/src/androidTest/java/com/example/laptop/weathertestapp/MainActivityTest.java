package com.example.laptop.weathertestapp;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



@RunWith(AndroidJUnit4.class)

@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testRecyclerView() throws Exception{

        Thread.sleep(8000);
        onView(withId(R.id.rvWeather)).check(matches(isDisplayed()));
        Thread.sleep(2000);

        onView(withId(R.id.rvWeather)).perform(scrollToPosition(8));
        Thread.sleep(2000);
        onView(withId(R.id.rvWeather)).check(matches(isDisplayed()));
    }
}
