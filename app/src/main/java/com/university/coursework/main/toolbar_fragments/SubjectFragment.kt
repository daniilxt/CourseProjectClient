package com.university.coursework.main.toolbar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.university.coursework.R
import com.university.coursework.adapters.SubjectAdapter
import com.university.coursework.api.subject.SubjectApi
import com.university.coursework.bus.EventBus
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.subject_fragment.*
import timber.log.Timber


class SubjectFragment : Fragment() {
    private var disposableSubject: Disposable? = null
    lateinit var itemAdapter: SubjectAdapter
    private var TOKEN = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.subject_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        val bundle = arguments
        if (bundle != null) {
            TOKEN = bundle.getString("token").toString()
        }
        disposableSubject = EventBus.get().subscribe { obj ->
            when (obj) {
                //todo
            }
        }
        createList()
    }

    private fun createList() {

        SubjectApi.getAllSubjects(TOKEN) {
            if (it != null) {
                itemAdapter = SubjectAdapter(it)
                subject_frg__recycler.layoutManager = LinearLayoutManager(requireContext())
                subject_frg__recycler.adapter = itemAdapter
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
}
