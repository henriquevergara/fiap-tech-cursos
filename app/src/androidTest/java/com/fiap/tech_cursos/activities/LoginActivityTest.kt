package com.fiap.tech_cursos.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.fiap.tech_cursos.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginActivityTest() {
        Thread.sleep(1000)

        onView(withId(R.id.editText_usuario_login)).perform(replaceText("henrique@email.com"))

        onView(withId(R.id.editText_senha_login)).perform(replaceText("123"), closeSoftKeyboard())

        val materialButton = onView(withId(R.id.button_login))
        materialButton.perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.recyclerViewContainer)).check(ViewAssertions.matches(isDisplayed()))
    }
}