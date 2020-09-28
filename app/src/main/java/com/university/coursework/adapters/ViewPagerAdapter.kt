package com.university.coursework.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.university.coursework.main.toolbar_fragments.GroupsFragment
import com.university.coursework.main.toolbar_fragments.PeopleFragment
import com.university.coursework.main.toolbar_fragments.SubjectFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,bundle: Bundle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    val peopleFrg = PeopleFragment()
    val groupsFrg = GroupsFragment()
    val subjectsFrg = SubjectFragment()
    init {
        peopleFrg.arguments = bundle
        groupsFrg.arguments = bundle
        subjectsFrg.arguments = bundle
    }
    val fragmentList:ArrayList<Fragment> = arrayListOf(
        groupsFrg,
        peopleFrg,
        subjectsFrg
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}