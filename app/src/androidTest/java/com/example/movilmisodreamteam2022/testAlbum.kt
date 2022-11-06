package com.example.movilmisodreamteam2022



import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.movilmisodreamteam2022.ui.CrearAlbumActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class testAlbum {


    @get:Rule
    val activityRule = ActivityTestRule(CrearAlbumActivity::class.java)


    @Test
    fun testCreateAlbum() {

        var nombreAlbun = "AndresAlbum"
        var albumYear = "1991"
        var nombreArtista = "Andres Cardenas"
        var cancion  = "animals"
        var genero  = "Salsa"
        var des = "Descripcion"
        var sello = "EMI"

        onView(withId(R.id.EDNombreAlbum)).perform(ViewActions.typeText(nombreAlbun.toString()))
        onView(withId(R.id.EDYearAlbum)).perform(ViewActions.typeText(albumYear.toString()))
        onView(withId(R.id.EDArtista)).perform(ViewActions.typeText(nombreArtista.toString()))
        onView(withId(R.id.EDCancionPreferida)).perform(ViewActions.typeText(cancion.toString()))
        onView(withId(R.id.EDGenero)).perform(ViewActions.typeText(genero.toString()))
        onView(withId(R.id.EDDescrip)).perform(ViewActions.typeText(des.toString()))
        onView(withId(R.id.EDRLabel)).perform(ViewActions.typeText(sello.toString()))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.BtnCrearAlbum)).perform(click())
        onView(withId(R.id.lblRequestCrearAlbum))
            .check(matches(withText("Album creado correctamente")))
    }


}