package ch.timonhueppi.m335.bmicalc;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import ch.timonhueppi.m335.bmicalc.R;

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
public class CompleteTest1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void completeTest1() {
        ViewInteraction editText = onView(
allOf(withId(R.id.editWeightNumber),
childAtPosition(
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0),
4),
isDisplayed()));
        editText.perform(replaceText("65"), closeSoftKeyboard());
        
        ViewInteraction editText2 = onView(
allOf(withId(R.id.editHeightNumber),
childAtPosition(
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0),
7),
isDisplayed()));
        editText2.perform(replaceText("1.75"), closeSoftKeyboard());
        
        ViewInteraction button = onView(
allOf(withId(R.id.btnCalc), withText("Berechnen"),
childAtPosition(
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0),
9),
isDisplayed()));
        button.perform(click());
        
        ViewInteraction textView = onView(
allOf(withId(R.id.textViewBMI), withText("21.22449"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
isDisplayed()));
        textView.check(matches(withText("21.22449")));
        
        ViewInteraction textView2 = onView(
allOf(withId(R.id.textViewClassification), withText("Normalgewichtig"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
isDisplayed()));
        textView2.check(matches(withText("Normalgewichtig")));
        
        pressBack();
        
        ViewInteraction button2 = onView(
allOf(withId(R.id.btnCalc), withText("BERECHNEN"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
