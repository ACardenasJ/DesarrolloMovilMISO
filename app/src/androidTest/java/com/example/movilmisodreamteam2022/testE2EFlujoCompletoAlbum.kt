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
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@LargeTest
@RunWith(AndroidJUnit4::class)
class testE2EFlujoCompletoAlbum {

    @get:Rule
    val activityRuleMain = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testE2EFlujoCompletoAlbum(){
        var number: Number = (0..100).shuffled().last()
        var item_rv: Int = 0
        var nombreAlbun = "Album " + number.toString()
        var albumYear = "1991"
        var des = "Descripcion Test"
        var selectionText = "Classical"
        var Sello = "Sony Music"
        Thread.sleep(3000)
        onView(withId(R.id.button_album_m)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.fab_add_album)).perform(click())
        Thread.sleep(3000)
        // CREAR ALBUM
        onView(withId(R.id.EDNombreAlbum)).perform(ViewActions.typeText(nombreAlbun.toString()))
        onView(withId(R.id.EDYearAlbum)).perform(ViewActions.typeText(albumYear.toString()))
        onView(withId(R.id.spinnerGenero)).check(matches(withSpinnerText(
            Matchers.containsString(
                selectionText
            )
        )))
        onView(withId(R.id.EDDescrip)).perform(ViewActions.typeText(des.toString()))
        onView(withId(R.id.spinnerSello)).check(matches(withSpinnerText(
            Matchers.containsString(
                Sello
            )
        )))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.BtnCrearAlbum)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.lblRequestCrearAlbum)).check(matches(withText("Album creado correctamente")))
        Thread.sleep(4000)
        // REVISAR ALBUM CREADO
        onView(withId(R.id.albumsRv)).check(matches(isDisplayed()));
        onView(withId(R.id.albumsRv))
        val recyclerView =
            activityRuleMain.activity.findViewById<RecyclerView>(R.id.albumsRv)
        recyclerView.setItemViewCacheSize(0)
        val itemCount = recyclerView.adapter?.itemCount
        Thread.sleep(4000)
        check(itemCount!! > 0)
        //BUSCAR ITEM CREADO
        item_rv = -1

        for (i in 0..itemCount){
            var itemx_view = recyclerView.findViewHolderForAdapterPosition(i)?.itemView
            var itx_Titulo = itemx_view?.findViewById<TextView>(R.id.tv_album_titulo)
            if(itx_Titulo == null){
                var pos = i + 5
                if(pos > itemCount){
                    pos = itemCount
                }
                onView(withId(R.id.albumsRv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(pos))
                itemx_view = recyclerView.findViewHolderForAdapterPosition(i)?.itemView
                itx_Titulo = itemx_view?.findViewById<TextView>(R.id.tv_album_titulo)

            }
            val str_tit = itx_Titulo?.text
            if(str_tit!!.equals(nombreAlbun)){
                item_rv = i
                break
            }
            Thread.sleep(50)
        }
        var Titulo_test = ""
        var Desc_test = ""
        runOnUiThread {
            // Stuff that updates the UI
            val item1_view = recyclerView.findViewHolderForAdapterPosition(item_rv)?.itemView
            val it1_Titulo = item1_view?.findViewById<TextView>(R.id.tv_album_titulo)
            val it1_Desc = item1_view?.findViewById<TextView>(R.id.tv_album_desc)
            check(!it1_Titulo?.text.isNullOrEmpty())
            check(!it1_Desc?.text.isNullOrEmpty())
            Titulo_test = it1_Titulo?.text.toString()
            Desc_test = it1_Desc?.text.toString()
            recyclerView.findViewHolderForAdapterPosition(item_rv)?.itemView?.performClick()
        }
        // REVISAR EL DETALLE DE ALBUM CREADO
        Thread.sleep(3000)
        //onView(withId(R.id.lblDetalleNombreTxt)).check(matches(withText(Titulo_test.toString())))
        //onView(withId(R.id.lblDetalleDescripcionTxt)).check(matches(withText(Desc_test.toString())))
        Thread.sleep(5000)
    }
}

