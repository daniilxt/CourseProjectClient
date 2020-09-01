package com.university.coursework.screens

import androidx.fragment.app.Fragment
import com.university.coursework.main.auth.AuthFragment
import com.university.coursework.main.cabinet.CabinetFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class AuthScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return AuthFragment.newInstance()
    }
}

class CabinetScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return CabinetFragment.newInstance()
    }
}
