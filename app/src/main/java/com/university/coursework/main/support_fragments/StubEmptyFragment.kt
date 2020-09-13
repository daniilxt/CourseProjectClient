package com.university.coursework.main.support_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.university.coursework.R
import timber.log.Timber


class StubEmptyFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = StubEmptyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.stub_empty_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        val bundle = arguments
        if (bundle != null) {
            //todo
        }
    }


    override fun onResume() {
        super.onResume()
        Timber.i("ON RESUME")
    }
}