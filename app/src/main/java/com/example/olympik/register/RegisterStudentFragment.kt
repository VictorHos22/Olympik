package com.example.olympik.register

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.olympik.R
import com.example.olympik.common.util.TxtWatcher
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register_student.*

class   RegisterStudentFragment : Fragment(R.layout.fragment_register_student), RegisterStudent{

    private lateinit var auth : FirebaseAuth
    private var fragmentAttachListener : FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        register_student_btn_register.setOnClickListener{
            validateData()
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

    private fun validateData(){
        val name = register_student_full_name.text.toString()
        val sex = register_student_spinner_sex.toString()
        val birthDate = register_student_birth_date.text.toString()
        val cpf = register_student_cpf.text.toString()
        val phoneNumber = register_student_phone_number.text.toString()
        val email = register_student_email.text.toString()
        val password = register_student_password.text.toString()
        val confirmPass = register_student_repassword.text.toString()

        val isNameValid = name.length >= 10
        val isSexValid = sex != "Sexo"
        val isBirthDateValid = birthDate.isNotEmpty()
        val isCpfValid = cpf.length == 11
        val isPhoneNumberValid = phoneNumber.length == 11
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >=6
        val isConfirmValid = password == confirmPass

        if (!isNameValid){
            displayNameFailure(R.string.invalid_name)
        } else {
            displayNameFailure(null)
        }

        if (!isSexValid){
            displaySexFailure(R.string.invalid_sex)
        } else {
            displaySexFailure(null)
        }

        if (!isBirthDateValid){
            displayBirthDateFailure(R.string.invalid_birth)
        } else {
            displayBirthDateFailure(null)
        }

        if (!isCpfValid){
            displayCpfFailure(R.string.invalid_cpf)
        } else {
            displayCpfFailure(null)
        }

        if (!isPhoneNumberValid){
            displayPhoneNumberFailure(R.string.invalid_phone)
        } else {
            displayPhoneNumberFailure(null)
        }

        if(!isEmailValid){
            displayEmailFailure(R.string.invalid_email)
        } else {
            displayEmailFailure(null)
        }

        if (!isConfirmValid){
            displayRePasswordFailure(R.string.invalid_confirm)
        } else {
            if (!isPasswordValid){
               displayPasswordFailure(R.string.invalid_password)
            } else {
                displayPasswordFailure(null)
            }
        }

        if (isNameValid && isSexValid && isBirthDateValid && isCpfValid && isPhoneNumberValid &&
            isEmailValid && isPasswordValid && isConfirmValid){

            showProgress(true)
            createUser(email, password)
        }
    }

    private fun createUser(email: String, password: String){
        val email = register_student_email.text.toString()
        val password = register_student_password.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    onCreateSuccess()
                } else {
                    showProgress(false)
                }
            }

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
}