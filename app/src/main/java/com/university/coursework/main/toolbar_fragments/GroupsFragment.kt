package com.university.coursework.main.toolbar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.university.coursework.bus.EventBus
import com.university.coursework.R
import com.university.coursework.api.group.GroupApi
import com.university.coursework.api.person.PersonApi
import com.university.coursework.app.App
import com.university.coursework.models.dto.Group
import com.university.coursework.models.dto.Person
import io.reactivex.disposables.Disposable
import timber.log.Timber


class GroupsFragment : Fragment() {
    private var disposableGroups: Disposable? = null
    private var TOKEN = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.groups_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        val bundle = arguments
        if (bundle != null) {
            TOKEN = bundle.getString("token").toString()
        }
        disposableGroups = EventBus.get().subscribe { obj ->
            when (obj) {
//todo
            }
        }
        initComponents()
    }

    private fun initComponents() {

        GroupApi.getAllGroups(TOKEN) { it ->
            if (it != null) {
                App.instance.GROUPS = it as ArrayList<Group>
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.i("ON RESUME")
    }
}