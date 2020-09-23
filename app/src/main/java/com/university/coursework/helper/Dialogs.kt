/*
package com.university.coursework.helper

import android.R
import android.app.Dialog
import android.text.TextUtils
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showCreateHumanDialog(onComplete: (Diagnosis) -> Unit) {
    Dialog(requireContext(), R.style.Theme_Translucent).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_create_diagnosis)

        dlg_diagnosis__ok.setOnClickListener {
            if (!TextUtils.isEmpty(dlg_diagnosis__name.text)) {
                val diagnosisName = dlg_diagnosis__name.text.toString()
                onComplete(Diagnosis(-1, diagnosisName, -1))
                dismiss()
            } else {
                Toast.makeText(requireContext(), R.string.wrong_data, Toast.LENGTH_LONG).show()
            }
        }
        dlg_diagnosis__back.setOnClickListener {
            dismiss()
        }
    }.show()
}*/
