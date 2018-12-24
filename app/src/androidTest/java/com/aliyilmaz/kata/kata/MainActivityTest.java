package com.aliyilmaz.kata.kata;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.aliyilmaz.kata.kata.mainactivity.MainActivity;
import com.aliyilmaz.kata.kata.webview.WebViewActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(WebViewActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void TextViewHyperLinkClickTest() {
        // perform click on specific element in the cardview
        onView(allOf(withId(R.id.recyclerview), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.bottomDescription)));
        // get the activity
        Activity webViewActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        // check it
        assertNotNull(webViewActivity);
    }

    @Test
    public void recyclerViewEspressoTest() {
        // verify the visibility of recycler view on screen
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));
        // perform click on view at 3rd position in RecyclerView
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        // perform click on specific element in the cardview
        onView(allOf(withId(R.id.recyclerview), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.shopBtn1)));
    }

    @Test
    public void recyclerViewToWebViewTest() {
        // perform click on specific element in the cardview
        onView(allOf(withId(R.id.recyclerview), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.shopBtn1)));
        // get the activity
        Activity webViewActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        // check it
        assertNotNull(webViewActivity);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    // to get child view in cardview which is in recyclerview
    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}