package com.ebnrdwan.task.ui.articles

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.ebnrdwan.task.R
import com.ebnrdwan.task.presentation.articles.ArticlesFragment
import com.ebnrdwan.task.presentation.main.MainActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4ClassRunner::class)
class ArticlesFragmentTest {
    @Rule
    lateinit var activity: ActivityTestRule<MainActivity>
    lateinit var navController: NavController

    @Before
    fun setUp() {
        navController = Mockito.mock(NavController::class.java)
        launchFragment()
        activity = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)


    }

    private fun launchFragment() {
        val scenario = launchFragmentInContainer<ArticlesFragment>()
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @Test
    fun testScroll() {
//        launchFragment()
        Espresso.onView(ViewMatchers.withId(R.id.rvArticles))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )


        Espresso.onView(ViewMatchers.withId(R.id.swipe_refresh)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.tv_description)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }


    @Test
    fun testRefresh() { //Before refresh there is a list .
        Espresso.onView(ViewMatchers.withId(R.id.rvArticles)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.loading_layout)).check(
            ViewAssertions.matches(
                Matchers.not(
                    ViewMatchers.isDisplayed()
                )
            )
        )
        // do refresh .
        Espresso.onView(ViewMatchers.withId(R.id.swipe_refresh)).perform(ViewActions.click())
        //after refresh there is a list.
        Espresso.onView(ViewMatchers.withId(R.id.rvArticles)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.loading_layout)).check(
            ViewAssertions.matches(
                Matchers.not(
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }

    @Test
    fun displayNewsData() {
        Espresso.onView(ViewMatchers.withId(R.id.rvArticles)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.loading_layout)).check(
            ViewAssertions.matches(
                Matchers.not(
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }


}