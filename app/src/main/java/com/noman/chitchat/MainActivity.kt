package com.noman.chitchat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.noman.chitchat.databinding.ActivityMainBinding

private lateinit var drawerLayout: DrawerLayout
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController: NavController = findNavController(R.id.nav_host_fragment_content_main)
        drawerLayout = binding.drawerLayout
        window.statusBarColor = getColor(R.color.white)

        binding.ivBack.setOnClickListener{
            navController.popBackStack()
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.splash ->{
                    binding.ivBack.visibility = View.GONE
                }
                R.id.login ->{
                    binding.ivBack.visibility = View.GONE
                }
                else->{
                    binding.ivBack.visibility = View.VISIBLE
                }
            }
        }

    }
}