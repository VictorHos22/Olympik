package com.example.olympik.registerStudent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.olympik.FirstBodyMeasurementsActivity
import com.example.olympik.ChooseAccountActivity
import com.example.olympik.R
import com.example.olympik.common.base.DependencyInjector
import com.example.olympik.common.util.TxtWatcher
import com.example.olympik.main.MainActivity
import com.example.olympik.registerStudent.RegisterStudent
import com.example.olympik.registerStudent.presentation.RegisterStudentPresenter
import kotlinx.android.synthetic.main.activity_register_student.*


class RegisterStudentActivity : AppCompatActivity(), RegisterStudent.View {

    override lateinit var presenter: RegisterStudent.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_student)

        val repository = DependencyInjector.registerStudentRepository()
        presenter = RegisterStudentPresenter(this, repository)

        register_student_btn_register.setOnClickListener {
            presenter.create(
                register_student_full_name.toString(),
                register_student_spinner_sex.toString(),
                register_student_birth_date.toString(),
                register_student_cpf.toString(),
                register_student_phone_number.toString(),
                register_student_email.toString(),
                register_student_password.toString(),
                register_student_repassword.toString()
                )
        }

        register_student_full_name.addTextChangedListener(watcher)
        register_student_full_name.addTextChangedListener(TxtWatcher{
            displayNameFailure(null)
        })
        //falta spinner
        register_student_birth_date.addTextChangedListener(watcher)
        register_student_birth_date.addTextChangedListener(TxtWatcher{
            displayBirthDateFailure(null)
        })
        register_student_cpf.addTextChangedListener(watcher)
        register_student_cpf.addTextChangedListener(TxtWatcher{
            displayCpfFailure(null)
        })
        register_student_phone_number.addTextChangedListener(watcher)
        register_student_phone_number.addTextChangedListener(TxtWatcher{
            displayPhoneNumber(null)
        })
        register_student_email.addTextChangedListener(watcher)
        register_student_email.addTextChangedListener(TxtWatcher{
            displayEmailFailure(null)
        })
        register_student_password.addTextChangedListener(watcher)
        register_student_password.addTextChangedListener(TxtWatcher{
            displayPasswordFailure(null)
        })
        register_student_repassword.addTextChangedListener(watcher)
        register_student_repassword.addTextChangedListener(TxtWatcher{
            displayRePasswordFailure(null)
        })

        val spinner: Spinner = register_student_spinner_sex
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.sex_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
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

    override fun displayPhoneNumber(phoneNumberError: Int?) {
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
        goToMainScreen()
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun goToMainScreen(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
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