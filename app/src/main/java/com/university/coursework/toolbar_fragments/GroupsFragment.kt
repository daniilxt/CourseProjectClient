package com.university.coursework.toolbar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fintech.giflab.bus.EventBus
import com.university.coursework.R
import io.reactivex.disposables.Disposable
import timber.log.Timber


class GroupsFragment : Fragment() {
    private var disposableGroups: Disposable? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.groups_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        disposableGroups = EventBus.get().subscribe { obj ->
            when (obj) {
//todo
            }
        }
        initComponents()
    }

    private fun initComponents() {
        Timber.i("ON INIT COMPONENTS")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("ON RESUME")
    }
}