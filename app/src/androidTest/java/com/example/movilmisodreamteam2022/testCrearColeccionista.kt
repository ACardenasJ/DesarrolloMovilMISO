package com.example.movilmisodreamteam2022

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.movilmisodreamteam2022.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class testCrearColeccionista {
    //@get:Rule
    //val activityRuleCrearColeccionista = ActivityTestRule(CrearColeccionistaActivity::class.java)

    @get:Rule
    val activityRuleMain = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testCreateColeccionista() {

        var coleccionistanombre = "juanse"
        var email = "juanse@outlook.com"
        var telefono = "3205446987"

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.button_coleccionista_m)).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.fab_add_coleccionista)).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.EDNombreColeccionista)).perform(ViewActions.typeText(coleccionistanombre.toString()))
        Espresso.onView(ViewMatchers.withId(R.id.EDEmail)).perform(ViewActions.typeText(email.toString()))
        Espresso.onView(ViewMatchers.withId(R.id.EDCellphoneColeccionista)).perform(ViewActions.typeText(telefono.toString()))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.BtnCrearColeccionista)).perform(ViewActions.click())
        Thread.sleep(500)
        Espresso.onView(ViewMatchers.withId(R.id.lblRequestCrearColeccinista))
            .check(matches(ViewMatchers.withText("Coleccionista creado correctamente")))
        Thread.sleep(5000)

    }
}