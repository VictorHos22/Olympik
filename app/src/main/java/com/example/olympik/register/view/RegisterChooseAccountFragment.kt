package com.example.olympik.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.olympik.R
import kotlinx.android.synthetic.main.fragment_register_choose_account.*


class RegisterChooseAccountFragment : Fragment(R.layout.fragment_register_choose_account) {
    private var fragmentAttachListener : FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_btn_am_aluno.setOnClickListener {
            fragmentAttachListener?.goToRegisterStudent()
        }
        register_btn_am_adm.setOnClickListener {
            fragmentAttachListener?.goToRegisterAdm()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }
}