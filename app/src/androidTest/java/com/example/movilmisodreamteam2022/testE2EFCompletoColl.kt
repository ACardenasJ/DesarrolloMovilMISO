package com.example.movilmisodreamteam2022

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
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
class testE2EFCompletoColl {

    @get:Rule
    val activityRuleMain = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testE2EFCompletoColl(){
        var number: Number = (0..100).shuffled().last()
        var item_rv: Int = 0
        var coleccionistanombre = "Coleccionista " + number.toString()
        var email = "juanse@outlook.com"
        var telefono = "3205446987"

        //CREAR COLLECCIONISTA
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

        //LISTAR COLLECCIONISTA

        Thread.sleep(3000)
        onView(withId(R.id.coleccionistasRv)).check(matches(isDisplayed()));
        onView(withId(R.id.coleccionistasRv))
        val recyclerView =  activityRuleMain.activity.findViewById<RecyclerView>(R.id.coleccionistasRv)
        val itemCount = recyclerView.adapter?.itemCount
        Thread.sleep(3000)
        check(itemCount!! > 0)
        item_rv = -1

        for (i in 0..itemCount){
            var itemx_view = recyclerView.findViewHolderForAdapterPosition(i)?.itemView
            var itx_Titulo = itemx_view?.findViewById<TextView>(R.id.tv_collec_title)
            if(itx_Titulo == null){
                var pos = i + 5
                if(pos > itemCount){
                    pos = itemCount
                }
                onView(withId(R.id.coleccionistasRv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(pos))
                itemx_view = recyclerView.findViewHolderForAdapterPosition(i)?.itemView
                itx_Titulo = itemx_view?.findViewById<TextView>(R.id.tv_collec_title)

            }
            val str_tit = itx_Titulo?.text
            if(str_tit!!.equals(coleccionistanombre)){
                item_rv = i
                break
            }
            Thread.sleep(50)
        }

        // DETALLE COLLECCIONISTA
        var Titulo_test = ""
        runOnUiThread {
            // Stuff that updates the UI
            val item1_view = recyclerView.findViewHolderForAdapterPosition(item_rv)?.itemView
            val it1_Titulo = item1_view?.findViewById<TextView>(R.id.tv_collec_title)
            //val it1_Desc = item1_view?.findViewById<TextView>(R.id.tv_collec_desc)
            //val it1_Tel =  item1_view?.findViewById<TextView>(R.id.tv_collec_phone)
            check(!it1_Titulo?.text.isNullOrEmpty())
            //check(!it1_Desc?.text.isNullOrEmpty())
            //check(!it1_Tel?.text.isNullOrEmpty())

            Titulo_test = it1_Titulo?.text.toString()
            recyclerView.findViewHolderForAdapterPosition(item_rv)?.itemView?.performClick()
        }
        Thread.sleep(4000)
        onView(withId(R.id.lblDetalleColeccinistaNombreTxt)).check(matches(withText(Titulo_test.toString())))
        Thread.sleep(5000)
    }
}