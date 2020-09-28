package com.university.coursework.main.toolbar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.university.coursework.bus.EventBus
import com.university.coursework.R
import com.university.coursework.adapters.GroupsAdapter
import com.university.coursework.api.group.GroupApi
import com.university.coursework.app.App
import com.university.coursework.extensions.showCreateGroupDialog
import com.university.coursework.helper.CiceroneHelper
import com.university.coursework.helper.Role
import com.university.coursework.models.dto.Group
import com.university.coursework.screens.AuthScreen
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.groups_fragment.*
import kotlinx.android.synthetic.main.people_fragment.*
import timber.log.Timber


class GroupsFragment : Fragment() {
    private var disposableGroups: Disposable? = null
    private var TOKEN = ""
    lateinit var itemAdapter: GroupsAdapter

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
        if (App.instance.CLIENT_ROLE == Role.ADMIN) {
            groups_frg__btn_add.visibility = View.VISIBLE
        }
        initComponents()
        createList()
        initButtons()
    }

    private fun initButtons() {
        groups_frg__btn_add.setOnClickListener {
            showCreateGroupDialog(true) {
                GroupApi.createGroup(TOKEN, it as Group) {
                    println("TOKEN IS: $TOKEN")
                    createList()
                }
            }
        }
    }

    private fun createList() {
        GroupApi.getAllGroups(TOKEN) {
            if (it != null) {
                println("TOKEN IS: $TOKEN")
                App.instance.GROUPS = it as ArrayList<Group>
                itemAdapter = GroupsAdapter(it)
                groups_frg__recycler.layoutManager = LinearLayoutManager(requireContext())
                groups_frg__recycler.adapter = itemAdapter
                itemAdapter.notifyDataSetChanged()
            } else {
                CiceroneHelper.router().navigateTo(AuthScreen())
                //CiceroneHelper.router().navigateTo(InfoScreen())
            }
        }
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