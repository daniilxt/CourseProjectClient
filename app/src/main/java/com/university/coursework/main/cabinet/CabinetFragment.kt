package com.university.coursework.main.cabinet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.fintech.giflab.bus.EventBus
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.university.coursework.R
import com.university.coursework.adapters.ViewPagerAdapter
import com.university.coursework.bus.Event
import io.reactivex.disposables.Disposable
import timber.log.Timber

class CabinetFragment : Fragment() {

    private lateinit var imm: InputMethodManager
    private var TOKEN = ""
    private var disposableCabinet: Disposable? = null


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
        disposableCabinet = EventBus.get().subscribe { obj ->
            when (obj) {
                Event.SHOW_INFO -> {
                    //CiceroneHelper.router().newChain(InfoScreen())
                }
            }
        }
    }

    private fun initTabs() {
        val viewPager: ViewPager2 = requireActivity().findViewById(R.id.main_activity__view_pager)
        var bundle = Bundle()
        bundle.putString("token", TOKEN)
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle,bundle)
        viewPager.adapter = adapter

        val tabs: TabLayout = requireActivity().findViewById(R.id.main_activity__tabs)
        val titles = listOf("Группы", "Люди", "Предметы")
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}
