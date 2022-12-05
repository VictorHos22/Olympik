package com.example.olympik.register

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.olympik.R

class RegisterChooseAccountFragment : Fragment(R.layout.fragment_register_choose_account) {
    private var fragmentAttachListener : FragmentAttachListener? = null



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }
}