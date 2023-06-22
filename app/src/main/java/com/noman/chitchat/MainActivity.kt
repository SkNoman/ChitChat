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

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        drawerLayout = binding.drawerLayout
        window.statusBarColor = getColor(R.color.white)

        binding.ivBack.setOnClickListener{
            navController.popBackStack()
        }
        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
            when(destination.id){
                R.id.splash, R.id.login, R.id.conversation ->{
                    binding.ivBack.visibility = View.GONE
                }
                else->{
                    binding.ivBack.visibility = View.VISIBLE
                }
            }
        }

    }

   /* @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.login, R.id.home2 -> {
                finish()
            }
            R.id.conversation -> {
                navController.navigate(R.id.home2)
            }
            R.id.signUp -> {
                navController.navigate(R.id.login)
            }
        }
        super.onBackPressed()
    }*/
}