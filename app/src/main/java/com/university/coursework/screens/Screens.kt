package com.university.coursework.screens

import androidx.fragment.app.Fragment
import com.university.coursework.auth.AuthFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


class AuthScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return AuthFragment.newInstance()
    }
}
