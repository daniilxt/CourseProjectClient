package com.university.coursework.main.support_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.university.coursework.R
import com.university.coursework.adapters.MarksRecyclerAdapter
import com.university.coursework.api.marks.MarksApi
import com.university.coursework.app.App
import com.university.coursework.helper.SpinnerTag
import com.university.coursework.models.dto.*
import kotlinx.android.synthetic.main.people_info_fragment.*
import timber.log.Timber
import java.util.stream.Collectors


class PeopleInfoFragment : Fragment() {
    private var userMarks: ArrayList<Mark> = ArrayList()
    private lateinit var person: Person
    lateinit var itemAdapter: MarksRecyclerAdapter
    private var TOKEN = ""
    private var postData = PostMark()

    private inner class PostMark {
        var teacherData: Person? = null
        var subjectData: Subject? = null
        var teacher: String = ""
            set(value) {
                field = value
            }
        var mark: String = ""
            set(value) {
                field = value
            }
        var subject: String = ""
            set(value) {
                field = value
            }

        fun clearFields() {
            teacherData = null
            subjectData = null
            teacher = ""
            mark = ""
            subject = ""
        }

        fun allFilled(): Boolean {
            if (teacherData != null && subjectData != null && mark.isNotEmpty()) {
                return true
            }
            return false
        }

        override fun toString(): String {
            return "PostMark(teacher='$teacher', mark='$mark', subject='$subject')"
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
            person = bundle.getSerializable("item") as Person
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
            if (postData.allFilled()) {
                clearAllSpinners()
                var mark =
                    userMarks.find { item ->
                        postData.teacherData == item.teacher && postData.subjectData == item.subject
                    }
                mark?.value = postData.mark
                if (mark == null) {
                    mark = Mark(
                        null,
                        person,
                        postData.subjectData!!,
                        postData.teacherData!!,
                        postData.mark
                    )
                }
                Timber.i("MARK IS: $mark")
                MarksApi.setMark(TOKEN, mark) {
                    createList()
                }
                postData.clearFields()

                // createList()
                people_info_frg__recycler.visibility = View.VISIBLE
                people_info_frg__constraint_container.visibility = View.GONE
                PostMark().clearFields()
            }
        }
    }

    private fun clearAllSpinners() {
        people_info_frg__list_marks.setSelection(0)
        people_info_frg__list_subjects.setSelection(0)
        people_info_frg__list_teachers.setSelection(0)
    }

    private fun initSpinners() {
        val spinner: Spinner = requireActivity().findViewById(R.id.people_info_frg__list_teachers)
        val spinner2: Spinner = requireActivity().findViewById(R.id.people_info_frg__list_subjects)
        val spinnerMark: Spinner = requireActivity().findViewById(R.id.people_info_frg__list_marks)
        val teachers = App.instance.TEACHERS.stream().map { item ->
            "${item.lastName} ${item.firstName} ${item.middleName}"
        }.sorted().collect(Collectors.toList()).apply { add(0, "-") }

        val subjects = App.instance.SUBJECTS.stream().map { item -> item.name }.sorted()
            .collect(Collectors.toList()).apply { add(0, "-") }

        val marks: List<String> = listOf("-", "5", "4", "3", "2", "1")

        spinnerList(spinner, teachers, SpinnerTag.TEACHER)
        spinnerList(spinner2, subjects, SpinnerTag.SUBJECT)
        spinnerList(spinnerMark, marks, SpinnerTag.MARK)
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
                Timber.i(str)
                postData.teacherData = null
                postData.teacherData = App.instance.TEACHERS.find { item ->
                    str == "${item.lastName} ${item.firstName} ${item.middleName}"
                }
                Timber.i("MARK $str  ${postData.teacherData}")

            }
            SpinnerTag.SUBJECT -> {
                postData.subjectData = null
                postData.subjectData = App.instance.SUBJECTS.find { item ->
                    str == item.name
                }
                Timber.i("MARK $str  ${postData.subjectData}")
            }
            SpinnerTag.MARK -> {
                postData.mark = ""
                postData.mark = str
                Timber.i("MARK $str  ${postData.mark}")

            }
            else -> {

            }
        }
    }

    private fun createList() {
        MarksApi.getPersonMarks(TOKEN, 6) {
            Timber.i("????? $it")
            if (it != null) {
                userMarks = it
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

    private fun createMark(item: Mark) {

    }
}