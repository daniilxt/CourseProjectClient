package com.university.coursework.toolbar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fintech.giflab.bus.EventBus
import com.university.coursework.R
import io.reactivex.disposables.Disposable
import timber.log.Timber


class PeopleFragment : Fragment() {
    private var disposablePeople: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.people_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        disposablePeople = EventBus.get().subscribe { obj ->
            when (obj) {
                //todo
            }
        }
    }
}
