package com.example.olympik.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.olympik.R
import com.example.olympik.common.base.DependencyInjector
import com.example.olympik.common.util.TxtWatcher
import com.example.olympik.register.RegisterStudentFirstMeasurements
import com.example.olympik.register.presentation.RegisterStudentFirstMeasurementsPresenter
import kotlinx.android.synthetic.main.fragment_register_student_first_measurements.*

class RegisterStudentFirstMeasurementsFragment : Fragment(R.layout.fragment_register_student_first_measurements), RegisterStudentFirstMeasurements.View{

    private var fragmentAttachListener: FragmentAttachListener? = null

    override lateinit var presenter: RegisterStudentFirstMeasurements.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = DependencyInjector.registerStudentFirstMeasurementRepository()
        presenter = RegisterStudentFirstMeasurementsPresenter(this, repository)

        register_measurements_btn_insert.setOnClickListener {
            presenter.create(
                register_m_weight.text.toString(),
                register_m_height.text.toString(),
                register_m_chest.text.toString(),
                register_m_shoulder.text.toString(),
                register_m_left_arm.text.toString(),
                register_m_right_arm.text.toString(),
                register_m_abdomen.text.toString(),
                register_m_left_thighs.text.toString(),
                register_m_right_thighs.text.toString(),
                register_m_left_calves.text.toString(),
                register_m_right_calves.text.toString()
            )
        }

        register_m_weight.addTextChangedListener(watcher)
        register_m_height.addTextChangedListener(watcher)
        register_m_chest.addTextChangedListener(watcher)
        register_m_shoulder.addTextChangedListener(watcher)
        register_m_left_arm.addTextChangedListener(watcher)
        register_m_right_arm.addTextChangedListener(watcher)
        register_m_abdomen.addTextChangedListener(watcher)
        register_m_left_thighs.addTextChangedListener(watcher)
        register_m_right_thighs.addTextChangedListener(watcher)
        register_m_left_calves.addTextChangedListener(watcher)
        register_m_right_calves.addTextChangedListener(watcher)

        register_m_weight.addTextChangedListener(TxtWatcher{
            displayWeightFailure(null)
        })
        register_m_height.addTextChangedListener(TxtWatcher{
            displayHeightFailure(null)
        })
        register_m_chest.addTextChangedListener(TxtWatcher{
            displayChestFailure(null)
        })
        register_m_shoulder.addTextChangedListener(TxtWatcher{
            displayShoulderFailure(null)
        })
        register_m_left_arm.addTextChangedListener(TxtWatcher{
            displayLeftArmFailure(null)
        })
        register_m_right_arm.addTextChangedListener(TxtWatcher{
            displayRightArmFailure(null)
        })
        register_m_abdomen.addTextChangedListener(TxtWatcher{
            displayAbdomenFailure(null)
        })
        register_m_left_thighs.addTextChangedListener(TxtWatcher{
            displayLeftThighsFailure(null)
        })
        register_m_right_thighs.addTextChangedListener(TxtWatcher{
            displayRightThighsFailure(null)
        })
        register_m_left_calves.addTextChangedListener(TxtWatcher{
            displayLeftCalvesFailure(null)
        })
        register_m_right_calves.addTextChangedListener(TxtWatcher{
            displayRightCalvesFailure(null)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun showProgress(enabled: Boolean) {
        register_measurements_btn_insert.showProgress(enabled)
    }

    override fun displayWeightFailure(weightError: Int?) {
        register_m_weight.error = weightError?.let { getString(it) }
    }

    override fun displayHeightFailure(heightError: Int?) {
        register_m_height.error = heightError?.let { getString(it) }
    }

    override fun displayChestFailure(chestError: Int?) {
        register_m_chest.error = chestError?.let { getString(it) }
    }

    override fun displayShoulderFailure(shoulderError: Int?) {
        register_m_shoulder.error = shoulderError?.let { getString(it) }
    }

    override fun displayLeftArmFailure(leftArmError: Int?) {
        register_m_left_arm.error = leftArmError?.let { getString(it) }
    }

    override fun displayRightArmFailure(rightArmError: Int?) {
        register_m_right_arm.error = rightArmError?.let { getString(it) }
    }

    override fun displayAbdomenFailure(abdomenError: Int?) {
        register_m_abdomen.error = abdomenError?.let { getString(it) }
    }

    override fun displayLeftThighsFailure(leftThighsError: Int?) {
        register_m_left_thighs.error = leftThighsError?.let { getString(it) }
    }

    override fun displayRightThighsFailure(rightThighsError: Int?) {
        register_m_right_thighs.error = rightThighsError?.let { getString(it) }
    }

    override fun displayLeftCalvesFailure(leftCalvesError: Int?) {
        register_m_left_calves.error = leftCalvesError?.let { getString(it) }
    }

    override fun displayRightCalvesFailure(rightCalvesError: Int?) {
        register_m_right_calves.error = rightCalvesError?.let { getString(it) }
    }

    override fun onCreateSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private val watcher = TxtWatcher{
        register_measurements_btn_insert.isEnabled = register_m_weight.text.toString().isNotEmpty()
            && register_m_height.text.toString().isNotEmpty()
            && register_m_chest.text.toString().isNotEmpty()
            && register_m_shoulder.text.toString().isNotEmpty()
            && register_m_left_arm.text.toString().isNotEmpty()
            && register_m_right_arm.text.toString().isNotEmpty()
            && register_m_abdomen.text.toString().isNotEmpty()
            && register_m_left_thighs.text.toString().isNotEmpty()
            && register_m_right_thighs.text.toString().isNotEmpty()
            && register_m_left_calves.text.toString().isNotEmpty()
            && register_m_right_calves.text.toString().isNotEmpty()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}