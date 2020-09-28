package com.university.coursework.main.toolbar_fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.university.coursework.R
import com.university.coursework.adapters.OnItemClickListener
import com.university.coursework.adapters.RecyclerAdapter
import com.university.coursework.adapters.SwipeToDeleteCallback
import com.university.coursework.api.person.PersonApi
import com.university.coursework.api.subject.SubjectApi
import com.university.coursework.app.App
import com.university.coursework.bus.EventBus
import com.university.coursework.helper.CiceroneHelper
import com.university.coursework.helper.Role
import com.university.coursework.extensions.showCreateHumanDialog
import com.university.coursework.models.dto.Person
import com.university.coursework.models.dto.Subject
import com.university.coursework.screens.AuthScreen
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
        delItemFunction()
        initButtons()

        disposablePeople = EventBus.get().subscribe { obj ->
            when (obj) {
                //todo
            }
        }
        if (App.instance.CLIENT_ROLE == Role.ADMIN) {
           people_frg__btn_add.visibility = View.VISIBLE
        }
    }

    private fun delItemFunction() {
        val item =
            object : SwipeToDeleteCallback(requireContext(), 0, ItemTouchHelper.LEFT) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    itemAdapter.del(viewHolder.absoluteAdapterPosition)
                }
            }
        if (App.instance.CLIENT_ROLE == Role.ADMIN) {
            val itemTouchHelper = ItemTouchHelper(item)
            itemTouchHelper.attachToRecyclerView(people_frg__recycler)
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
        people_frg__wr_search_tv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun filterList(str: String) {
        val sortedList: MutableList<Person> = ArrayList()
        for (item in App.instance.PERSONS) {
            if (str.replace(
                    " ",
                    ""
                ) in (item.lastName + item.firstName + item.firstName).toLowerCase()
            ) {
                sortedList.add(item)
            }
        }
        itemAdapter.update(sortedList)
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
                App.instance.PERSONS = it
                itemAdapter = RecyclerAdapter(it, this)
                people_frg__recycler.layoutManager = LinearLayoutManager(requireContext())
                people_frg__recycler.adapter = itemAdapter
                itemAdapter.notifyDataSetChanged()
            } else {
                CiceroneHelper.router().navigateTo(AuthScreen())
                //CiceroneHelper.router().navigateTo(InfoScreen())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        people_frg__wr_search_tv.text.clear()
        Timber.i("ON RESUME")
    }

    override fun onItemClicked(position: Int, item: Person) {
        if (item.type == 'S') {
            val bundle = Bundle()
            bundle.putString("token", TOKEN)
            bundle.putSerializable("item", item)
            CiceroneHelper.router().navigateTo(InfoScreen(bundle))
            return
        }
        Toast.makeText(
            requireContext(),
            "Посмотреть оценки можно только у студента",
            Toast.LENGTH_SHORT
        ).show()
    }
}
