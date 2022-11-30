package com.example.movilmisodreamteam2022


import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.movilmisodreamteam2022.ui.CrearArtistaActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.movilmisodreamteam2022.ui.MainActivity
import org.hamcrest.CoreMatchers.containsString

@LargeTest
@RunWith(AndroidJUnit4::class)
class testCrearArtista {
    //@get:Rule
    //val activityRuleCrearArtista = ActivityTestRule(CrearArtistaActivity::class.java)

    @get:Rule
    val activityRuleMain = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testCreateArtista() {

        var nombre = "Juanse Artista"
        var year = "1991"
        var song = "esto si es un tema"
        var des = "Descripcion"
        var sello = "Classical"

        Thread.sleep(3000)
        onView(withId(R.id.button_artist_m)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.fab_add_artist)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.EDNombre)).perform(ViewActions.typeText(nombre.toString()))
        onView(withId(R.id.EDYear)).perform(ViewActions.typeText(year.toString()))
        onView(withId(R.id.EDSong)).perform(ViewActions.typeText(song.toString()))
        onView(withId(R.id.EDDesc)).perform(ViewActions.typeText(des.toString()))
        onView(withId(R.id.spinnerArtistaGenero)).check(matches(withSpinnerText(containsString(sello))))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.BtnCrearArtista)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.lblRequestCrearArtista))
            .check(matches(withText("Artista creado correctamente")))
        Thread.sleep(5000)
    }




}