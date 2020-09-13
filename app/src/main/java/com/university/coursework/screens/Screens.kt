package com.university.coursework.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.university.coursework.main.auth.AuthFragment
import com.university.coursework.main.cabinet.CabinetFragment
import com.university.coursework.main.support_fragments.StubEmptyFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class AuthScreen() : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return AuthFragment.newInstance()
    }
}

class CabinetScreen(str: String) : SupportAppScreen() {
    private val bundle = Bundle()
    private val token = str

    override fun getFragment(): Fragment? {
        val frg = CabinetFragment.newInstance()
        if (token.isNotEmpty()) {
            bundle.putString("token", token)
            frg.arguments = bundle
        }
        return frg
    }
}

class InfoScreen() : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return StubEmptyFragment.newInstance()
    }
}