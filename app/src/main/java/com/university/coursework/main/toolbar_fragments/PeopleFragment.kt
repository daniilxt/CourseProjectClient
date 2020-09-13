package com.university.coursework.main.toolbar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fintech.giflab.bus.EventBus
import com.university.coursework.R
import com.university.coursework.adapters.OnItemClickListener
import com.university.coursework.adapters.RecyclerAdapter
import com.university.coursework.api.person.PersonApi
import com.university.coursework.extensions.showChildFragment
import com.university.coursework.helper.CiceroneHelper
import com.university.coursework.models.dto.Group
import com.university.coursework.screens.InfoScreen
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.people_fragment.*
import timber.log.Timber


class PeopleFragment : Fragment(), OnItemClickListener {
    private var disposablePeople: Disposable? = null
    private val dataItems = ArrayList<com.university.coursework.models.dto.Person>()
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
        dataItems.add(
            com.university.coursework.models.dto.Person(
                3, "Alex", "Muza", "Igorevich",
                Group(3, "TEACHER", "T")
            )
        )
        dataItems.add(
            com.university.coursework.models.dto.Person(
                4, "Valentin", "Obj", "Alex",
                Group(3, "TEACHER", "T")
            )
        )
        createList()

        disposablePeople = EventBus.get().subscribe { obj ->
            when (obj) {
                //todo
            }
        }
    }

    private fun createList() {
        PersonApi.getAllPerson(TOKEN) {
            if (it != null) {
                itemAdapter = RecyclerAdapter(it, this)
                people_frg__recycler.layoutManager = LinearLayoutManager(requireContext())
                people_frg__recycler.adapter = itemAdapter
                itemAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.i("ON RESUME")
    }

    override fun onItemClicked(position: Int) {
        Toast.makeText(requireContext(), "Position is $position", Toast.LENGTH_SHORT).show()
        CiceroneHelper.router().navigateTo(InfoScreen())
    }

    private fun showAction(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("fragment", "top")
        fragment.arguments = bundle
        showChildFragment(fragment, R.id.people_frg__container)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataItems.clear()
    }
}
