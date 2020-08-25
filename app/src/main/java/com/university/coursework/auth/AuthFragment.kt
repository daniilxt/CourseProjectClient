package com.university.coursework.auth

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
import timber.log.Timber

class AuthFragment : Fragment() {

    private lateinit var buttonEnter: Button
    private lateinit var imm: InputMethodManager

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.auth_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("ON VIEW CREATED")
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        buttonEnter = requireActivity().findViewById(R.id.auth_frg__btn_sign_in) as Button

        bindProgressButton(buttonEnter)
        buttonEnter.isEnabled = false
        buttonEnter.attachTextChangeAnimator {
            fadeInMills = 150
            fadeOutMills = 150
        }
    }
}
