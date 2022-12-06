package com.example.olympik.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.olympik.R
import com.example.olympik.common.extension.replaceFragment
import com.example.olympik.discovery.view.DiscoveryFragment
import com.example.olympik.frequency.view.FrequencyFragment
import com.example.olympik.measurement.view.MeasurementFragment
import com.example.olympik.profile.view.ProfileFragment
import com.example.olympik.register.TrainingNewWorkoutFragment
import com.example.olympik.training.view.TrainingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, MainFragmentAttachListener{

    private lateinit var discoveryFragment: Fragment
    private lateinit var measurementsFragment: Fragment
    private lateinit var trainingFragment: Fragment
    private lateinit var frequencyFragment: Fragment
    private lateinit var profileFragment: Fragment
    private var currentFragment: Fragment? = null
    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        discoveryFragment = DiscoveryFragment()
        measurementsFragment = MeasurementFragment()
        trainingFragment = TrainingWorkoutsFragment()
        frequencyFragment = FrequencyFragment()
        profileFragment = ProfileFragment()

        main_bottom_nav.setOnNavigationItemSelectedListener(this)
        main_bottom_nav.selectedItemId = R.id.menu_training
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_discovery ->{
                if(currentFragment == discoveryFragment) return false
                currentFragment = discoveryFragment
            }
            R.id.menu_measurements ->{
                if(currentFragment == measurementsFragment) return false
                currentFragment = measurementsFragment
            }
            R.id.menu_training ->{
                if(currentFragment == trainingFragment) return false
                currentFragment = trainingFragment
            }
            R.id.menu_frequency ->{
                if(currentFragment == frequencyFragment) return false
                currentFragment = frequencyFragment
            }
            R.id.menu_profile ->{
                if(currentFragment == profileFragment) return false
                currentFragment = profileFragment
            }

        }
        currentFragment?.let {
            replaceFragment(R.id.main_fragment, it)
        }
        return true
    }

    override fun goToCreateNewTraining() {
        fragment = TrainingNewWorkoutFragment()
        replaceFragment(fragment)
    }
    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.main_fragment, fragment)
    }

}