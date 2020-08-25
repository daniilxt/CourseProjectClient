package com.university.coursework.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.university.coursework.R
import com.university.coursework.helper.CiceroneHelper
import com.university.coursework.screens.AuthScreen
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val navigator = SupportAppNavigator(
            this, supportFragmentManager, R.id.main_frg__container
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
    }

    override fun onResume() {
        super.onResume()
        Timber.i("ON RESUME")
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        CiceroneHelper.navHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        CiceroneHelper.navHolder().removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initFragments() {
        CiceroneHelper.router().newRootScreen(AuthScreen())
    }
}