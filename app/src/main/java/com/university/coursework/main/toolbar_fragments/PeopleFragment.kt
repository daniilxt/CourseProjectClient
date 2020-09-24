package com.university.coursework.main.toolbar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.university.coursework.R
import com.university.coursework.adapters.OnItemClickListener
import com.university.coursework.adapters.RecyclerAdapter
import com.university.coursework.api.person.PersonApi
import com.university.coursework.api.group.GroupApi
import com.university.coursework.api.subject.SubjectApi
import com.university.coursework.app.App
import com.university.coursework.bus.EventBus
import com.university.coursework.extensions.showChildFragment
import com.university.coursework.helper.CiceroneHelper
import com.university.coursework.helper.showCreateHumanDialog
import com.university.coursework.models.dto.Person
import com.university.coursework.models.dto.Subject
import com.university.coursework.screens.InfoScreen
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.people_fragment.*
import timber.log.Timber


class PeopleFragment : Fragment(), OnItemClickListener {
    private var disposablePeople: Disposable? = null
    lateinit var itemAdapter: RecyclerAdapter

    private var TOKEN = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.people_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        val bundle = arguments
        if (bundle != null) {
            TOKEN = bundle.getString("token").toString()
        }
        Timber.i("TOKEN IS $TOKEN")
        createList()
        initButtons()

        disposablePeople = EventBus.get().subscribe { obj ->
            when (obj) {
                //todo
            }
        }
    }

    private fun initButtons() {
        people_frg__btn_add.setOnClickListener {
            showCreateHumanDialog {
                PersonApi.createPerson(TOKEN, it) {
                    createPersons()
                }
            }
        }
    }

    private fun createList() {
        SubjectApi.getAllSubjects(TOKEN) {
            if (it != null) {
                App.instance.SUBJECTS = it as ArrayList<Subject>
            }
        }
        createPersons()
    }

    private fun createPersons() {
        PersonApi.getAllTeachers(TOKEN) { it ->
            if (it != null) {
                App.instance.TEACHERS_GROUP = it[0].group
                App.instance.TEACHERS = it as ArrayList<Person>
            }
        }

        PersonApi.getAllPerson(TOKEN) {
            if (it != null) {
                itemAdapter = RecyclerAdapter(it, this)
                people_frg__recycler.layoutManager = LinearLayoutManager(requireContext())
                people_frg__recycler.adapter = itemAdapter
                itemAdapter.notifyDataSetChanged()
            } else {
                //CiceroneHelper.router().navigateTo(InfoScreen())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.i("ON RESUME")
    }

    override fun onItemClicked(position: Int, item: Person) {
        val bundle = Bundle()
        bundle.putString("token", TOKEN)
        bundle.putSerializable("item", item)
        CiceroneHelper.router().navigateTo(InfoScreen(bundle))
    }

    private fun showAction(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("fragment", "top")
        fragment.arguments = bundle
        showChildFragment(fragment, R.id.people_frg__container)
    }
}
