package com.university.coursework.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.university.coursework.toolbar_fragments.GroupsFragment
import com.university.coursework.toolbar_fragments.PeopleFragment
import com.university.coursework.toolbar_fragments.SubjectFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    val fragmentList:ArrayList<Fragment> = arrayListOf(
        GroupsFragment(),
        PeopleFragment(),
        SubjectFragment()
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}