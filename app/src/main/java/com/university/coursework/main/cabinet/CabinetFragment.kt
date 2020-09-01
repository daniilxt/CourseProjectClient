package com.university.coursework.main.cabinet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.Fragment
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.university.coursework.R
import com.university.coursework.api.validate_user.ValidateUserApi
import com.university.coursework.api.validate_user.ValidateUserService
import kotlinx.android.synthetic.main.auth_fragment.*
import timber.log.Timber

class CabinetFragment : Fragment() {

    private lateinit var imm: InputMethodManager

    companion object {
        fun newInstance() = CabinetFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.cabinet_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)


    }
}
