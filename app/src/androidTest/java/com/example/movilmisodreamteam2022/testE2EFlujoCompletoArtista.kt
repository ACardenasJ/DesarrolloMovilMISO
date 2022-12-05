package com.example.movilmisodreamteam2022

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.rule.ActivityTestRule
import com.example.movilmisodreamteam2022.ui.MainActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@LargeTest
@RunWith(AndroidJUnit4::class)
class testE2EFlujoCompletoArtista {

    @get:Rule
    val activityRuleMain = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testE2EFlujoCompletoArtista(){

        //CREAR ARTISTA
        var number: Number = (0..100).shuffled().last()
        var item_rv: Int = 0
        var nombre = "Artista " + number.toString()
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
        onView(withId(R.id.spinnerArtistaGenero)).check(matches(withSpinnerText(
            CoreMatchers.containsString(
                sello
            )
        )))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.BtnCrearArtista)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.lblRequestCrearArtista))
            .check(matches(withText("Artista creado correctamente")))
        //REVISAR LISTA
        Thread.sleep(3000)
        onView(withId(R.id.artistasRv)).check(matches(isDisplayed()));
        onView(withId(R.id.artistasRv))
        val recyclerView = activityRuleMain.activity.findViewById<RecyclerView>(R.id.artistasRv)
        val itemCount = recyclerView.adapter?.itemCount
        Thread.sleep(3000)
        check(itemCount!! > 0)
        //BUSCAR ITEM CREADO
        item_rv = -1

        for (i in 0..itemCount){
            var itemx_view = recyclerView.findViewHolderForAdapterPosition(i)?.itemView
            var itx_Titulo = itemx_view?.findViewById<TextView>(R.id.tv_artist_title)
            if(itx_Titulo == null){
                var pos = i + 5
                if(pos > itemCount){
                    pos = itemCount
                }
                onView(withId(R.id.artistasRv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(pos))
                itemx_view = recyclerView.findViewHolderForAdapterPosition(i)?.itemView
                itx_Titulo = itemx_view?.findViewById<TextView>(R.id.tv_artist_title)

            }
            val str_tit = itx_Titulo?.text
            if(str_tit!!.equals(nombre)){
                item_rv = i
                break
            }
            Thread.sleep(50)
        }
        // REVISAR DETALLE
        var Titulo_test = ""
        var Desc_test = ""
        runOnUiThread {
            // Stuff that updates the UI
            val item1_view = recyclerView.findViewHolderForAdapterPosition(item_rv)?.itemView
            val it1_Titulo = item1_view?.findViewById<TextView>(R.id.tv_artist_title)
            val it1_Desc = item1_view?.findViewById<TextView>(R.id.tv_artist_desc)
            check(!it1_Titulo?.text.isNullOrEmpty())
            check(!it1_Desc?.text.isNullOrEmpty())

            Titulo_test = it1_Titulo?.text.toString()
            Desc_test = it1_Desc?.text.toString()
            recyclerView.findViewHolderForAdapterPosition(item_rv)?.itemView?.performClick()
        }
        Thread.sleep(3000)
        onView(withId(R.id.lblDetalleNombreTxt)).check(matches(withText(Titulo_test.toString())))
        onView(withId(R.id.lblDetalleDescripcionTxt)).check(matches(withText(Desc_test.toString())))
        Thread.sleep(5000)
    }
}