package com.jbc7ag.cryptso.ui.currencieslist

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import com.jbc7ag.cryptso.MainActivity
import com.jbc7ag.cryptso.launchFragmentInHiltContainer
import com.jbc7ag.cryptso.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@ExperimentalCoroutinesApi
class CurrenciesFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun screen_is_shown() = runBlockingTest {

        onView(withText("Cryptso")).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.filter_list), isDisplayed()))
        onView(allOf(withId(R.id.currencies_list), isDisplayed()))
    }

    @Test
    fun click_currency_navigateToDetailsFragment() {

        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<CurrenciesFragment>(
            Bundle(),
            R.style.Theme_AppCompat_DayNight_NoActionBar
        ) {
            Navigation.setViewNavController(view!!, navController)
        }

        onView(allOf(withId(R.id.currencies_list), isDisplayed()))
           /* .perform(
                actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Bitcoin")), ViewActions.click()
                )
            )

        verify(navController).navigate(
            CurrenciesFragmentDirections.actionCurrenciesFragmentToCurrencyDetailFragment("btc_mxn")
        )*/
    }
}
