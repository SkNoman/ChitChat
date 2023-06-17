package com.noman.chitchat.ui.login_sign_up

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.noman.chitchat.R
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentLoginBinding


private lateinit var auth: FirebaseAuth
class Login : BaseFragmentWithBinding<FragmentLoginBinding>
    (FragmentLoginBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener{
            if (validateLogin() == "OK"){
                login(binding.etEmailLogin.text.toString(),
                binding.etPassLogin.text.toString())
            }else {
                Toast.makeText(requireContext(),validateLogin(),Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvRegister.setOnClickListener{
            findNavController().navigate(R.id.signUp)
        }
    }

    private fun validateLogin():String {
        if (binding.etEmailLogin.text.isNullOrEmpty()){
            return "Please enter email"
        }else if(binding.etEmailLogin.text!!.length < 15){
            return "Please enter valid email"
        }else if(binding.etPassLogin.text.isNullOrEmpty()){
            return "Please enter password"
        }else if (binding.etPassLogin.text!!.length < 6){
            return "Password minimum length is 6"
        }
        return "OK"
    }

    private fun login(email:String,password:String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(),"Login In Successful",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.home2)
                }else {
                    Toast.makeText(requireContext(),"Login Failed: ${task.exception}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}