package com.university.coursework.main.cabinet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.university.coursework.R
import com.university.coursework.adapters.ViewPagerAdapter
import com.university.coursework.api.person.PersonApi
import com.university.coursework.api.validate_user.ValidateUserApi
import com.university.coursework.api.validate_user.ValidateUserService
import com.university.coursework.models.dto.Person
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.android.synthetic.main.cabinet_fragment.*
import timber.log.Timber

class CabinetFragment : Fragment() {

    private lateinit var imm: InputMethodManager
    private var TOKEN = ""

    companion object {
        fun newInstance() = CabinetFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.cabinet_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        val bundle = arguments
        if (bundle != null) {
            TOKEN = bundle.getString("token").toString()
        }
        initTabs()
/*        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        PersonApi.getAllPerson(TOKEN) {
            onResponse(it)
        }*/


    }

    private fun initTabs() {
        val viewPager: ViewPager2 = requireActivity().findViewById(R.id.main_activity__view_pager)
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        val tabs: TabLayout = requireActivity().findViewById(R.id.main_activity__tabs)
        val titles = listOf("Группы", "Люди", "Предметы")
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}
/*
    private fun onResponse(it: ArrayList<Person?>?) {
        it?.forEach {
            if (it != null) {
                println("Name: ${it.firstName} , Surname: ${it.lastName}")
            }
        }
    }*/