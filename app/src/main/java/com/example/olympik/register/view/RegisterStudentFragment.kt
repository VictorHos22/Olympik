package com.example.olympik.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.olympik.R
import com.example.olympik.common.base.DependencyInjector
import com.example.olympik.common.util.TxtWatcher
import com.example.olympik.register.RegisterStudent
import com.example.olympik.register.presentation.RegisterStudentPresenter
import kotlinx.android.synthetic.main.fragment_register_student.*

class   RegisterStudentFragment : Fragment(R.layout.fragment_register_student), RegisterStudent.View{
    private var fragmentAttachListener : FragmentAttachListener? = null

    override lateinit var presenter: RegisterStudent.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = DependencyInjector.registerStudentRepository()
        presenter = RegisterStudentPresenter(this, repository)

        register_student_btn_register.setOnClickListener {
            presenter.create(
                register_student_full_name.text.toString(),
                register_student_spinner_sex.toString(),
                register_student_birth_date.text.toString(),
                register_student_cpf.text.toString(),
                register_student_phone_number.text.toString(),
                register_student_email.text.toString(),
                register_student_password.text.toString(),
                register_student_repassword.text.toString()
            )
        }

        register_student_full_name.addTextChangedListener(watcher)
        register_student_birth_date.addTextChangedListener(watcher)
        register_student_cpf.addTextChangedListener(watcher)
        register_student_phone_number.addTextChangedListener(watcher)
        register_student_email.addTextChangedListener(watcher)
        register_student_password.addTextChangedListener(watcher)
        register_student_repassword.addTextChangedListener(watcher)

        register_student_full_name.addTextChangedListener(TxtWatcher{
            displayNameFailure(null)
        })
        //falta spinner
        register_student_birth_date.addTextChangedListener(TxtWatcher{
            displayBirthDateFailure(null)
        })
        register_student_cpf.addTextChangedListener(TxtWatcher{
            displayCpfFailure(null)
        })
        register_student_phone_number.addTextChangedListener(TxtWatcher{
            displayPhoneNumberFailure(null)
        })
        register_student_email.addTextChangedListener(TxtWatcher{
            displayEmailFailure(null)
        })
        register_student_password.addTextChangedListener(TxtWatcher{
            displayPasswordFailure(null)
        })
        register_student_repassword.addTextChangedListener(TxtWatcher{
            displayRePasswordFailure(null)
        })

        val spinner: Spinner = register_student_spinner_sex
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sex_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun showProgress(enabled: Boolean) {
        register_student_btn_register.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        register_student_edit_input_full_name.error = nameError?.let { getString(it) }
    }

    override fun displaySexFailure(sexError: Int?) {
        register_student_edit_input_sex.error = sexError?.let { getString(it) }
    }

    override fun displayBirthDateFailure(birthError: Int?) {
        register_student_edit_input_birth_date.error = birthError?.let { getString(it) }
    }

    override fun displayCpfFailure(cpfError: Int?) {
        register_student_edit_input_cpf.error = cpfError?.let { getString(it) }
    }

    override fun displayPhoneNumberFailure(phoneNumberError: Int?) {
        register_student_edit_input_phone_number.error = phoneNumberError?.let { getString(it) }
    }

    override fun displayEmailFailure(emailError: Int?) {
        register_student_edit_input_email.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        register_student_edit_input_password.error = passwordError?.let { getString(it) }
    }

    override fun displayRePasswordFailure(rePasswordError: Int?) {
        register_student_edit_input_repassword.error = rePasswordError?.let { getString(it) }
    }

    override fun onCreateSuccess() {
        fragmentAttachListener?.goToRegisterFirstMeasurements()
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private val watcher = TxtWatcher{
        register_student_btn_register.isEnabled = register_student_full_name.text.toString().isNotEmpty()
            //falta spinner
            && register_student_birth_date.text.toString().isNotEmpty()
            && register_student_cpf.text.toString().isNotEmpty()
            && register_student_phone_number.text.toString().isNotEmpty()
            && register_student_email.text.toString().isNotEmpty()
            && register_student_password.text.toString().isNotEmpty()
            && register_student_repassword.text.toString().isNotEmpty()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}