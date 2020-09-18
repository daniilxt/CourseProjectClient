package com.university.coursework.main.support_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.university.coursework.R
import com.university.coursework.adapters.MarksRecyclerAdapter
import com.university.coursework.api.marks.MarksApi
import com.university.coursework.app.App
import com.university.coursework.helper.SpinnerTag
import com.university.coursework.models.dto.Person
import kotlinx.android.synthetic.main.people_info_fragment.*
import timber.log.Timber


class PeopleInfoFragment : Fragment() {
    lateinit var itemAdapter: MarksRecyclerAdapter
    private var TOKEN = ""

    private inner class PostMark() {
        var teacher: String = ""
        var mark: String = ""
        var subject: String = ""
        fun clearFields() {
            teacher = ""
            mark = ""
            subject = ""
        }

        fun allFilled(): Boolean {
            if (teacher.isNotEmpty() && subject.isNotEmpty() && mark.isNotEmpty()) {
                return true
            }
            return false
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PeopleInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.people_info_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        val bundle = arguments
        if (bundle != null) {
            //todo
            TOKEN = bundle.getString("token").toString()
            val person: Person = bundle.getSerializable("item") as Person
            people_info_frg__nick_name.text =
                "${person.lastName} ${person.firstName} ${person.middleName}"
            people_info_frg__status.text = person.group.name
        }
        createList()
        initSpinners()
        initButtons()
    }

    private fun initButtons() {
        people_info_frg__btn_edit.setOnClickListener {
            people_info_frg__recycler.visibility = View.GONE
            people_info_frg__constraint_container.visibility = View.VISIBLE
        }
        people_info_frg__btn_save.setOnClickListener {
            //itemAdapter.notifyDataSetChanged()
            createList()
            people_info_frg__recycler.visibility = View.VISIBLE
            people_info_frg__constraint_container.visibility = View.GONE
            PostMark().clearFields()
        }
    }

    private fun initSpinners() {
        val spinner: Spinner = requireActivity().findViewById(R.id.people_info_frg__list_teachers)
        val spinner2: Spinner = requireActivity().findViewById(R.id.people_info_frg__list_subjects)
        val subjects = App.instance.SUBJECTS_ARRAY.apply { add(0, "-") }
        val teachers = App.instance.TEACHERS_ARRAY.apply { add(0, "-") }

        spinnerList(spinner, teachers, SpinnerTag.TEACHER)
        spinnerList(spinner2, subjects, SpinnerTag.SUBJECT)

    }

    private fun spinnerList(spinner: Spinner, list: List<String>, tag: SpinnerTag) {
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Display the selected item text on text view
                setTextFromSpinner(parent.getItemAtPosition(position).toString(), tag)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    private fun setTextFromSpinner(str: String, tag: SpinnerTag) {
        when (tag) {
            SpinnerTag.TEACHER -> {
                PostMark().teacher = str
            }
            SpinnerTag.SUBJECT -> {
                PostMark().subject = str
            }
            SpinnerTag.MARK -> {
                PostMark().mark = str
            }
            else -> {

            }
        }
    }

    private fun createList() {
        MarksApi.getPersonMarks(TOKEN, 6) {
            Timber.i("????? $it")
            if (it != null) {
                itemAdapter = MarksRecyclerAdapter(it)
                people_info_frg__recycler.layoutManager = LinearLayoutManager(requireContext())
                people_info_frg__recycler.adapter = itemAdapter
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