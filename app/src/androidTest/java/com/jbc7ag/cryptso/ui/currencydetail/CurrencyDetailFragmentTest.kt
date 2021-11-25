package com.jbc7ag.cryptso.ui.currencydetail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.jbc7ag.cryptso.MainActivity
import com.jbc7ag.cryptso.R
import com.jbc7ag.cryptso.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class CurrencyDetailFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun details_is_displayed() = runBlockingTest {

        val bundle = CurrencyDetailFragmentArgs("btc_mxn").toBundle()
        launchFragmentInHiltContainer<CurrencyDetailFragment>(
            bundle,
            R.style.Theme_AppCompat_DayNight_NoActionBar
        )

        // THEN - Task details are displayed on the screen
        onView(withId(R.id.detail_currency_label)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_currency_high_label)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_currency_low_label)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_currency_trades)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_currency_tab_bids)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_currency_tab_asks)).check(matches(isDisplayed()))
    }
}
