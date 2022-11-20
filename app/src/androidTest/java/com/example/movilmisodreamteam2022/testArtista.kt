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
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

@LargeTest
@RunWith(AndroidJUnit4::class)
class testArtista {
    @get:Rule
    val activityRule = ActivityTestRule(CrearArtistaActivity::class.java)

    @Test
    fun testCreateArtista() {

        var nombre = "AndresAlbum"
        var year = "1991"
        var song = "otro dia para morir"
        var des = "Descripcion"

        onView(withId(R.id.EDNombre)).perform(ViewActions.typeText(nombre.toString()))
        onView(withId(R.id.EDYear)).perform(ViewActions.typeText(year.toString()))
        onView(withId(R.id.EDSong)).perform(ViewActions.typeText(song.toString()))
        onView(withId(R.id.EDDesc)).perform(ViewActions.typeText(des.toString()))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.BtnCrearArtista)).perform(click())
        onView(withId(R.id.lblRequestCrearArtista))
            .check(matches(withText("Artista creado correctamente")))
    }
}