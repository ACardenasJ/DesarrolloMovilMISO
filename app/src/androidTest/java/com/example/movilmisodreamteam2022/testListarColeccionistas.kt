package com.example.movilmisodreamteam2022

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.rule.ActivityTestRule
import com.example.movilmisodreamteam2022.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class testListarColeccionistas {
    @get:Rule
    val activityRuleMain = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testListarColeccionistas() {

        Thread.sleep(3000)
        onView(withId(R.id.button_coleccionista_m)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.coleccionistasRv)).check(matches(isDisplayed()));
        onView(withId(R.id.coleccionistasRv))
        val recyclerView =
            activityRuleMain.activity.findViewById<RecyclerView>(R.id.coleccionistasRv)
        val itemCount = recyclerView.adapter?.itemCount
        Thread.sleep(3000)
        check(itemCount!! > 0)
        runOnUiThread {
            // Stuff that updates the UI
            val item1_view = recyclerView.findViewHolderForAdapterPosition(1)?.itemView
            val it1_Titulo = item1_view?.findViewById<TextView>(R.id.tv_collec_title)
            //val it1_Desc = item1_view?.findViewById<TextView>(R.id.tv_collec_desc)
            //val it1_Tel =  item1_view?.findViewById<TextView>(R.id.tv_collec_phone)
            check(!it1_Titulo?.text.isNullOrEmpty())
            //check(!it1_Desc?.text.isNullOrEmpty())
            //check(!it1_Tel?.text.isNullOrEmpty())
            recyclerView.findViewHolderForAdapterPosition(1)?.itemView?.performClick()
        }
        Thread.sleep(5000)
    }
}