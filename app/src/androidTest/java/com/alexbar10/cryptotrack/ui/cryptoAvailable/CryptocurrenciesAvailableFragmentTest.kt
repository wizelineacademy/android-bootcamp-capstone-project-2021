package com.alexbar10.cryptotrack.ui.cryptoAvailable

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alexbar10.cryptotrack.MainActivity
import com.alexbar10.cryptotrack.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CryptocurrenciesAvailableFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun activityIsLoaded() {
        onView(withText("Markets")).check(matches(isDisplayed()))
        onView(withText("Cryptocurrencies")).check(matches(isDisplayed()))
        onView(withId(R.id.cryptocurrencies_recycler_view)).check(matches(isDisplayed()))
        onView(withId(R.id.market_arg_checkbox)).check(matches(isDisplayed()))
        onView(withId(R.id.market_bitcoin_checkbox)).check(matches(isDisplayed()))
        onView(withId(R.id.market_brl_checkbox)).check(matches(isDisplayed()))
        onView(withId(R.id.market_dai_checkbox)).check(matches(isDisplayed()))
        onView(withId(R.id.market_mxn_checkbox)).check(matches(isDisplayed()))
        onView(withId(R.id.market_usd_checkbox)).check(matches(isDisplayed()))
    }
}
