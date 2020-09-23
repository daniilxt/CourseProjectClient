package com.university.coursework.helper

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.university.coursework.R
import kotlinx.android.synthetic.main.dialog_create_human.*

fun Fragment.showCreateHumanDialog(onComplete: (Boolean) -> Unit) {
    Dialog(requireContext(), android.R.style.Theme_Translucent).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_create_human)
        val spinnerMark: Spinner = findViewById(R.id.dlg_human_frg__list_group)

        val marks: List<String> = listOf("-", "5", "4", "3", "2", "1")
        spinnerList(spinnerMark, marks, SpinnerTag.MARK, context)
        dlg_human__ok.setOnClickListener {
/*            if (!TextUtils.isEmpty(dlg_diagnosis__name.text)) {
                val diagnosisName = dlg_diagnosis__name.text.toString()
                onComplete(Diagnosis(-1, diagnosisName, -1))
                dismiss()
            } else {
                Toast.makeText(requireContext(), R.string.wrong_data, Toast.LENGTH_LONG).show()
            }*/
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
        }


        override fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }
    }
}