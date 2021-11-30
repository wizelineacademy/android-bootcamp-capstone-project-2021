package com.example.bootcampproject.ui.availablebooks

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bootcampproject.MainActivity
import com.example.bootcampproject.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


/*@HiltAndroidTest
class AvailableBooksFragmentFragmentTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)



    @Before
    fun init() {
        hiltRule.inject()

    }

    @Test
    fun itIsVisibleListAvailableBooks() {
       *//* val scenario = launchFragmentInHiltContainer<AvailableBooksFragmentFragment>(
            factory = fragmentFactory
        )*//*
        onView(withId(R.id.available_books_list)).check(matches(isDisplayed()))
    }
}*/
