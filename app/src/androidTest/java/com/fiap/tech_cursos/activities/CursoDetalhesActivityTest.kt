package com.fiap.tech_cursos.activities


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.fiap.tech_cursos.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CursoDetalhesActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun cursoDetalhesActivityTest() {
        Thread.sleep(1000)

        onView(withId(R.id.editText_usuario_login)).perform(replaceText("henrique@gmail.com"))

        onView(withId(R.id.editText_senha_login)).perform(replaceText("123"))

        onView(withId(R.id.button_login)).perform(click())

        Thread.sleep(3000)

        onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withId(R.id.recyclerViewContainer), 1
                )
            )
        ).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Thread.sleep(1000)

        onView(withId(R.id.detalhesCurso)).check(ViewAssertions.matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}