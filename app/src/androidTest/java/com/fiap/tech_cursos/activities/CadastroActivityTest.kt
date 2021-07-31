package com.fiap.tech_cursos.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.fiap.tech_cursos.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CadastroActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun cadastroActivityTest() {
        Thread.sleep(1000)

        onView(withId(R.id.textView_cadastre)).perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.editText_usuario_cadastro)).perform(replaceText("Henrique Vergara"))

        onView(withId(R.id.editText_email_cadastro)).perform(replaceText("henrique@gmail.com"))

        onView(withId(R.id.editText_senha_cadastro)).perform(replaceText("123"))

        onView(withId(R.id.editText_confSenha_cadastro)).perform(replaceText("123"))

        onView(withId(R.id.button_cadastro)).perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.recyclerViewContainer)).check(ViewAssertions.matches(isDisplayed()))
    }
}