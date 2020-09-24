package com.university.coursework.helper

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.university.coursework.R
import com.university.coursework.app.App
import com.university.coursework.models.dto.Group
import com.university.coursework.models.dto.Person
import com.university.coursework.models.dto.Subject
import kotlinx.android.synthetic.main.dialog_create_human.*
import timber.log.Timber
import java.util.stream.Collectors

fun Fragment.showCreateHumanDialog(onComplete: (Boolean) -> Unit) {
    Dialog(requireContext(), android.R.style.Theme_Translucent).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_create_human)
        val spinnerMark: Spinner = findViewById(R.id.dlg_human_frg__list_group)

        val marks: List<String> =
            App.instance.GROUPS.stream().map { item -> item.name }.collect(Collectors.toList())
                .apply { add(0, "-") }

        spinnerList(spinnerMark, marks, SpinnerTag.MARK, context)
        dlg_human__ok.setOnClickListener {

            if (!TextUtils.isEmpty(dlg_human__name.text) && !TextUtils.isEmpty(
                    dlg_human__second_name.text
                ) && !TextUtils.isEmpty(dlg_human__middle_name.text) && App.instance.groupData != null
            ) {
                Timber.i(
                    "PERSSSSON ${Person(
                        null,
                        dlg_human__name.text.toString(),
                        dlg_human__second_name.text.toString(),
                        dlg_human__middle_name.text.toString(),
                        App.instance.groupData!!,
                        'S'
                    )}"
                )
                val diagnosisName = dlg_human__name.text.toString()
                // onComplete(Diagnosis(-1, diagnosisName, -1))
                dismiss()
            } else {
            }
        }
        dlg_human__back.setOnClickListener {
            dismiss()
        }
    }.show()
}

private fun spinnerList(spinner: Spinner, list: List<String>, tag: SpinnerTag, context: Context) {
    // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
    val adapter: ArrayAdapter<String> =
        ArrayAdapter(context, android.R.layout.simple_spinner_item, list);
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

        private fun setTextFromSpinner(str: String, tag: SpinnerTag) {

            App.instance.groupData = App.instance.GROUPS.find { item ->
                str == item.name
            }
            Timber.i("MARK $str  ${App.instance.groupData}")
        }


        override fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }
    }
}