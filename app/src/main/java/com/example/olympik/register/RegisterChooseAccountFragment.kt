package com.example.olympik.register

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.olympik.R
import com.example.olympik.common.extension.replaceFragment
import kotlinx.android.synthetic.main.fragment_register_choose_account.*
import kotlinx.android.synthetic.main.fragment_register_student.*

class RegisterChooseAccountFragment : Fragment(R.layout.fragment_register_choose_account) {
    private var fragmentAttachListener : FragmentAttachListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        choose_account_student.setOnClickListener {
            fragmentAttachListener?.goToRegisterStudent()
        }
        choose_account_adm.setOnClickListener {
            fragmentAttachListener?.goToRegisterAdm()
        }
    }
}