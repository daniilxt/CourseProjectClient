package com.university.coursework.helper

import com.university.coursework.app.App

object CiceroneHelper {
    fun router() = App.instance.router()
    fun navHolder() = App.instance.navHolder()
}
