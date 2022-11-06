package com.example.movilmisodreamteam2022

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.movilmisodreamteam2022.ui.CrearColeccionistaActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class testColeccinista {
    @get:Rule
    val activityRule = ActivityTestRule(CrearColeccionistaActivity::class.java)

    @Test
    fun testCreateColeccinista() {

        var coleccionistanombre = "Andres"
        var email = "andres@hotmail.com"
        var telefono = "3146692641"


        Espresso.onView(ViewMatchers.withId(R.id.EDNombreColeccionista)).perform(ViewActions.typeText(coleccionistanombre.toString()))
        Espresso.onView(ViewMatchers.withId(R.id.EDEmail)).perform(ViewActions.typeText(email.toString()))
        Espresso.onView(ViewMatchers.withId(R.id.EDCellphoneColeccionista)).perform(ViewActions.typeText(telefono.toString()))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.BtnCrearColeccionista)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.lblRequestCrearColeccinista))
            .check(matches(ViewMatchers.withText("Coleccionista creado correctamente")))

    }
}