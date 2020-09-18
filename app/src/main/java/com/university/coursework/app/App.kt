package com.university.coursework.app

import android.app.Application
import com.university.coursework.BuildConfig
import com.university.coursework.helper.CiceroneHandler
import com.university.coursework.timber.ReleaseTree
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


class App : Application(), CiceroneHandler {
    private lateinit var cicerone: Cicerone<Router>
    public var SUBJECTS_ARRAY:ArrayList<String> = ArrayList(Arrays.asList(""));
    public var TEACHERS_ARRAY:ArrayList<String> = ArrayList(Arrays.asList(""));

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
}
