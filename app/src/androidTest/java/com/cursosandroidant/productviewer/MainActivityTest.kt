package com.cursosandroidant.productviewer

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun setNewQuantity_sum_increasesTextField() {
        onView(withId(R.id.etNewQuantity))
            .perform(ViewActions.replaceText("10"))

        onView(withId(R.id.ibSum))
            .perform(click())

        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("11")))
    }

    @Test
    fun setNewQuantity_sub_decreasesTextField() {
        onView(withId(R.id.etNewQuantity))
            .perform(ViewActions.replaceText("11"))

        onView(withId(R.id.ibSub))
            .perform(click())

        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("10")))
    }

    @Test
    fun setNewQuantity_sumLimit_noIncreasesTextField() {
        val scenario = activityRule.scenario
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->
            activity.selectedProduct.quantity = 1
        }

        onView(withId(R.id.ibSum))
            .perform(click())

        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))
    }

    @Test
    fun setNewQuantity_subLimit_noDecreasesTextField() {
        onView(withId(R.id.ibSub))
            .perform(click())

        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))
    }

}