package com.example.pneumaticstorezavrsni2;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class LoginEndToEndTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(LoginActivity.class);
    }

    @Test
    public void testFullAppStartNavigationEnd() throws InterruptedException {

        // Typing
        onView(withId(R.id.emailInput))
                .perform(typeText("tester@pneumatic.com"), closeSoftKeyboard());

        onView(withId(R.id.passwordInput))
                .perform(typeText("password123"), closeSoftKeyboard());

        // Button clicking
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.nav_library)).perform(click());

        onView(withId(R.id.nav_store)).perform(click());

        onView(withId(R.id.nav_settings)).perform(click());

        onView(withId(R.id.nav_profile)).perform(click());

        onView(withId(R.id.logout_button)).perform(click());
    }
}
