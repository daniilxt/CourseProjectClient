package com.university.coursework.app

import android.app.Application
import android.app.Person
import com.university.coursework.BuildConfig
import com.university.coursework.helper.CiceroneHandler
import com.university.coursework.helper.Role
import com.university.coursework.models.dto.Subject
import com.university.coursework.timber.ReleaseTree
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.security.acl.Group
import java.util.*
import kotlin.collections.ArrayList


class App : Application(), CiceroneHandler {
    private lateinit var cicerone: Cicerone<Router>
    var SUBJECTS: ArrayList<Subject> = ArrayList()
    var TEACHERS_GROUP: com.university.coursework.models.dto.Group? = null
    var TEACHERS: ArrayList<com.university.coursework.models.dto.Person> = ArrayList()
    var GROUPS: ArrayList<com.university.coursework.models.dto.Group> = ArrayList()
    var TOKEN = ""
    var CLIENT_ROLE = Role.USER
    //bad
    var groupData: com.university.coursework.models.dto.Group? = null

    companion object {
        @JvmStatic
        lateinit var instance: App
        // private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        createCicerone()
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "Class:%s: Line: %s, Method: %s",
                        super.createStackElementTag(element),
                        element.lineNumber,
                        element.methodName
                    )
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    override fun createCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    override fun getCicerone(): Cicerone<Router> {
        return cicerone
    }

    fun getRouter(): Router {
        return cicerone.router
    }

    fun setRole(name: String) {
        CLIENT_ROLE = when (name) {
            "admin" -> {
                Role.ADMIN
            }
            "teacher" -> {
                Role.TEACHER
            }
            else -> {
                Role.USER
            }
        }
    }
}
