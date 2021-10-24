package com.mehyo.darbuka.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.mehyo.darbuka.R
import com.mehyo.darbuka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController=findNavController(R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        listener=NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.listFragment ->{ binding.bottomNavigationView.visibility= View.VISIBLE }
                R.id.detailsFragment ->{ binding.bottomNavigationView.visibility= View.GONE }
                R.id.bookmarksFragment ->{ binding.bottomNavigationView.visibility= View.VISIBLE }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }
    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

}