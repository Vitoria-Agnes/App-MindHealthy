package com.example.app_mindhealthy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.app_mindhealthy.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()


        bottomNavigationView.setupWithNavController(navController)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.visibility = if (
                destination.id == R.id.cadastroFragment2 ||
                destination.id == R.id.loginFragment2
            ) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> replaceFragment(HomeFragment())
                R.id.postar -> replaceFragment(PostFragment())
                R.id.configurar -> replaceFragment(ConfigFragment())

                else -> {}
            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}