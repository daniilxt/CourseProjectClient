package com.university.coursework.main.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.university.coursework.R
import com.university.coursework.api.validate_user.ValidateUserApi
import com.university.coursework.api.validate_user.ValidateUserService
import com.university.coursework.helper.CiceroneHelper
import com.university.coursework.screens.CabinetScreen
import kotlinx.android.synthetic.main.auth_fragment.*
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
        buttonEnter.isEnabled = true
        buttonEnter.attachTextChangeAnimator {
            fadeInMills = 150
            fadeOutMills = 150
        }

        buttonEnter.setOnClickListener {
            if (auth_frg__login_input.text.toString()
                    .isNotEmpty() && auth_frg__password_input.text.toString().isNotEmpty()
            ) {
                validateUser(
                    auth_frg__login_input.text.toString(),
                    auth_frg__password_input.text.toString()
                )
            }
        }
    }

    private fun validateUser(login: String, password: String) {
        Timber.i("LOGIN + PASS $login  $password")
        ValidateUserApi.authUser(
            ValidateUserService.PostCheckUserDto(
                login,
                password
            )
        ) {
            onResponse(it)
        }
    }

    private fun onResponse(it: ValidateUserService.CheckUserDto?) {
        Timber.i("DATA IS ${it.toString()}")
        if (it != null) {
            CiceroneHelper.router().navigateTo(CabinetScreen())
        } else {
            Toast.makeText(requireContext(), "Проверьте логин/пароль", Toast.LENGTH_SHORT).show()
        }
    }
}
