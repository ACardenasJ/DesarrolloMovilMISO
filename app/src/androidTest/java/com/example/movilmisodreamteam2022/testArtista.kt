package com.example.movilmisodreamteam2022

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule

import com.example.movilmisodreamteam2022.ui.CrearArtistaActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

@LargeTest
@RunWith(AndroidJUnit4::class)
class testArtista {
    @get:Rule
    val activityRule = ActivityTestRule(CrearArtistaActivity::class.java)

    @Test
    fun testCreateAlbum() {

        var nombreAlbun = "AndresAlbum"
        var albumYear = "1991"
        var nombreArtista = "Andres Cardenas"
        var cancion  = "animals"
        var des = "Descripcion"

        //Cp01 73
        //CP09 76 82
        //CP16

        onView(withId(R.id.EDNombre)).perform(ViewActions.typeText(nombreAlbun.toString()))
        onView(withId(R.id.EDYear)).perform(ViewActions.typeText(albumYear.toString()))
        onView(withId(R.id.EDSong)).perform(ViewActions.typeText(nombreArtista.toString()))
        onView(withId(R.id.EDGenero)).perform(ViewActions.typeText(cancion.toString()))
        onView(withId(R.id.EDDesc)).perform(ViewActions.typeText(des.toString()))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.BtnCrearArtista)).perform(click())

//        onView(withText("Album creado correctamente")).check(matches(isDisplayed()))
//
        //onView(withId(R.id.EDNombreAlbum)).check(ViewAssertions.matches(withText(nombreAlbun)))
    }
}