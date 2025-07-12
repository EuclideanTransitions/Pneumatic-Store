package com.example.pneumaticstorezavrsni2;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testEmailInputFieldIsVisible() {
        onView(withId(R.id.emailInput))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoginFlow_success() {

        // Typing
        onView(withId(R.id.emailInput))
                .perform(typeText("tester@pneumatic.com"), closeSoftKeyboard());

        onView(withId(R.id.passwordInput))
                .perform(typeText("password123"), closeSoftKeyboard());

        // Button clicking
        onView(withId(R.id.loginButton)).perform(click());

    }
}
