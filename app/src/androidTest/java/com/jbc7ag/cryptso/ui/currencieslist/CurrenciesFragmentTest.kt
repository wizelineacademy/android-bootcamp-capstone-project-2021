package com.jbc7ag.cryptso.ui.currencieslist

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import com.jbc7ag.cryptso.MainActivity
import com.jbc7ag.cryptso.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

@MediumTest
@ExperimentalCoroutinesApi
class CurrenciesFragmentTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun screen_is_shown() = runBlockingTest {
        onView(withText("Cryptso")).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.filter_list), isDisplayed()))
        onView(allOf(withId(R.id.currencies_list), isDisplayed()))
    }
}
