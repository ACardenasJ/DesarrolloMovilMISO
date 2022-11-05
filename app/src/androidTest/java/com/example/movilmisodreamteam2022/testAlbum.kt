package com.example.movilmisodreamteam2022


import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.movilmisodreamteam2022.ui.CrearAlbumActivity
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class testAlbum {

    private lateinit var scenario: ActivityScenario<CrearAlbumActivity>

   @Before
   fun setUp(){
       scenario = ActivityScenario.launch(CrearAlbumActivity::class.java)
       scenario.moveToState(Lifecycle.State.STARTED)
   }

    @Test
    fun testCreateAlbum() {

        var nombreAlbun = "AndresAlbum"
        var albumYear = "1991"
        var nombreArtista = "Andres Cardenas"
        var cancion  = "animals"
        var genero  = "salsa"
        var des = "Descripcion"
        var sello = "soni"


        onView(withId(R.id.EDNombreAlbum)).perform(ViewActions.typeText(nombreAlbun.toString()))
        onView(withId(R.id.EDYearAlbum)).perform(ViewActions.typeText(albumYear.toString()))
        onView(withId(R.id.EDArtista)).perform(ViewActions.typeText(nombreArtista.toString()))
        onView(withId(R.id.EDCancionPreferida)).perform(ViewActions.typeText(cancion.toString()))
        onView(withId(R.id.lblGenero)).perform(ViewActions.typeText(genero.toString()))
        onView(withId(R.id.lblDescrip)).perform(ViewActions.typeText(des.toString()))
        onView(withId(R.id.EDRLabel)).perform(ViewActions.typeText(sello.toString()))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.BtnCrearAlbum)).perform(click())

        //onView(withText(R.string.toast)).inRoot(ToastMatcher())
         //   .check(matches(withText("Invalid Name"))

    }


}