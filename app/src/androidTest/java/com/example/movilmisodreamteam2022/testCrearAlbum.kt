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
import com.example.movilmisodreamteam2022.ui.MainActivity
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class testCrearAlbum {

    @get:Rule
    val activityRuleMain = ActivityTestRule(MainActivity::class.java)

    //@get:Rule
    //val activityRuleCrearAlbum = ActivityTestRule(CrearAlbumActivity::class.java)

    @Test
    fun testCreateAlbum() {

        var nombreAlbun = "Andres Album"
        var albumYear = "1991"
        var des = "Descripcion"
        var selectionText = "Classical"
        var Sello = "Sony Music"
        Thread.sleep(3000)
        onView(withId(R.id.button_album_m)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.fab_add_album)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.EDNombreAlbum)).perform(ViewActions.typeText(nombreAlbun.toString()))
        onView(withId(R.id.EDYearAlbum)).perform(ViewActions.typeText(albumYear.toString()))
        onView(withId(R.id.spinnerGenero)).check(matches(withSpinnerText(containsString(selectionText))))
        onView(withId(R.id.EDDescrip)).perform(ViewActions.typeText(des.toString()))
        onView(withId(R.id.spinnerSello)).check(matches(withSpinnerText(containsString(Sello))))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.BtnCrearAlbum)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.lblRequestCrearAlbum)).check(matches(withText("Album creado correctamente")))
        Thread.sleep(5000)
    }


}