package com.example.olympik.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.olympik.R
import com.example.olympik.common.base.DependencyInjector
import com.example.olympik.common.util.TxtWatcher
import com.example.olympik.register.RegisterAdm
import com.example.olympik.register.presentation.RegisterAdmPresenter
import kotlinx.android.synthetic.main.fragment_register_adm.*

class RegisterAdmFragment : Fragment(R.layout.fragment_register_adm), RegisterAdm.View {

    private var fragmentAttachListener : FragmentAttachListener? = null

    override lateinit var presenter: RegisterAdm.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = DependencyInjector.registerAdmRepository()
        presenter = RegisterAdmPresenter(this, repository)

        register_adm_btn_next.setOnClickListener {
            presenter.create(
                register_adm_edit_name.text.toString(),
                register_adm_edit_cpf.text.toString(),
                register_adm_edit_phone_number.text.toString(),
                register_adm_edit_email.text.toString(),
                register_adm_edit_cnpj.text.toString(),
                register_adm_edit_business_name.text.toString(),
                register_adm_edit_fantasy_name.text.toString(),
                register_adm_edit_cep.text.toString(),
                register_adm_edit_address.text.toString(),
                register_adm_edit_state.text.toString(),
                register_adm_edit_city.text.toString(),
                register_adm_edit_password.text.toString(),
                register_adm_edit_repassword.text.toString()
            )
        }
        register_adm_edit_name.addTextChangedListener(watcher)
        register_adm_edit_cpf.addTextChangedListener(watcher)
        register_adm_edit_phone_number.addTextChangedListener(watcher)
        register_adm_edit_email.addTextChangedListener(watcher)
        register_adm_edit_cnpj.addTextChangedListener(watcher)
        register_adm_edit_business_name.addTextChangedListener(watcher)
        register_adm_edit_fantasy_name.addTextChangedListener(watcher)
        register_adm_edit_cep.addTextChangedListener(watcher)
        register_adm_edit_address.addTextChangedListener(watcher)
        register_adm_edit_state.addTextChangedListener(watcher)
        register_adm_edit_city.addTextChangedListener(watcher)
        register_adm_edit_password.addTextChangedListener(watcher)
        register_adm_edit_repassword.addTextChangedListener(watcher)

        register_adm_edit_name.addTextChangedListener(TxtWatcher{
            displayNameFailure(null)
        })
        register_adm_edit_cpf.addTextChangedListener(TxtWatcher{
            displayCpfFailure(null)
        })
        register_adm_edit_phone_number.addTextChangedListener(TxtWatcher{
            displayPhoneNumber(null)
        })
        register_adm_edit_email.addTextChangedListener(TxtWatcher{
            displayEmailFailure(null)
        })
        register_adm_edit_cnpj.addTextChangedListener(TxtWatcher{
            displayCnpjFailure(null)
        })
        register_adm_edit_business_name.addTextChangedListener(TxtWatcher{
            displaybusinessNameFailure(null)
        })
        register_adm_edit_fantasy_name.addTextChangedListener(TxtWatcher{
            displayFantasyNameFailure(null)
        })
        register_adm_edit_cep.addTextChangedListener(TxtWatcher{
            displayCepFailure(null)
        })
        register_adm_edit_address.addTextChangedListener(TxtWatcher{
            displayAddressFailure(null)
        })
        register_adm_edit_state.addTextChangedListener(TxtWatcher{
            displayStateFailure(null)
        })
        register_adm_edit_city.addTextChangedListener(TxtWatcher{
            displayCityFailure(null)
        })
        register_adm_edit_password.addTextChangedListener(TxtWatcher{
            displayPasswordFailure(null)
        })
        register_adm_edit_repassword.addTextChangedListener(TxtWatcher{
            displayRePasswordFailure(null)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun showProgress(enabled: Boolean) {
        register_adm_btn_next.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        register_adm_edit_input_name.error = nameError?.let { getString(it) }
    }

    override fun displayCpfFailure(cpfError: Int?) {
        register_adm_edit_input_cpf.error = cpfError?.let { getString(it) }
    }

    override fun displayPhoneNumber(phoneNumberError: Int?) {
        register_adm_edit_input_phone_number.error = phoneNumberError?.let { getString(it) }
    }

    override fun displayEmailFailure(emailError: Int?) {
        register_adm_edit_input_email.error = emailError?.let { getString(it) }
    }

    override fun displayCnpjFailure(cnpjError: Int?) {
        register_adm_edit_input_cnpj.error = cnpjError?.let { getString(it) }
    }

    override fun displaybusinessNameFailure(businessNameError: Int?) {
        register_adm_edit_input_business_name.error = businessNameError?.let { getString(it) }
    }

    override fun displayFantasyNameFailure(fantasyNameError: Int?) {
        register_adm_edit_input_fantasy_name.error = fantasyNameError?.let { getString(it) }
    }

    override fun displayCepFailure(cepError: Int?) {
        register_adm_edit_input_cep.error = cepError?.let { getString(it) }
    }

    override fun displayAddressFailure(addressError: Int?) {
        register_adm_edit_input_address.error = addressError?.let { getString(it) }
    }

    override fun displayStateFailure(stateError: Int?) {
        register_adm_edit_input_state.error = stateError?.let { getString(it) }
    }

    override fun displayCityFailure(cityError: Int?) {
        register_adm_edit_input_city.error = cityError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        register_adm_edit_input_password.error = passwordError?.let { getString(it) }
    }

    override fun displayRePasswordFailure(rePasswordError: Int?) {
        register_adm_edit_input_repassword.error = rePasswordError?.let { getString(it) }
    }

    override fun onCreateSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private val watcher = TxtWatcher{
        register_adm_btn_next.isEnabled = register_adm_edit_name.text.toString().isNotEmpty()
            && register_adm_edit_cpf.text.toString().isNotEmpty()
            && register_adm_edit_phone_number.text.toString().isNotEmpty()
            && register_adm_edit_email.text.toString().isNotEmpty()
            &&register_adm_edit_cnpj.text.toString().isNotEmpty()
            &&register_adm_edit_business_name.text.toString().isNotEmpty()
            &&register_adm_edit_fantasy_name.text.toString().isNotEmpty()
            &&register_adm_edit_cep.text.toString().isNotEmpty()
            &&register_adm_edit_address.text.toString().isNotEmpty()
            &&register_adm_edit_state.text.toString().isNotEmpty()
            &&register_adm_edit_city.text.toString().isNotEmpty()
            &&register_adm_edit_password.text.toString().isNotEmpty()
            &&register_adm_edit_repassword.text.toString().isNotEmpty()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}