package com.noman.chitchat.ui.login_sign_up

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.noman.chitchat.R
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentSignUpBinding
import com.noman.chitchat.model.User

class SignUp : BaseFragmentWithBinding<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
)
{
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference
        binding.btnSignUp.setOnClickListener{
            if (validateSignUp() == "OK"){
                signUp(binding.etFullName.text.toString(),
                binding.etPhone.text.toString() ,
                binding.etEmailSignUp.text.toString(),
                binding.etPasswordSignUp.text.toString()
                )
            }else{
                Toast.makeText(requireContext(),validateSignUp(), Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLoginBack.setOnClickListener{
            findNavController().navigate(R.id.login)
        }
    }

    private fun signUp(name:String,phone:String,email:String,password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,phone,email,auth.currentUser?.uid!!)
                    Toast.makeText(requireContext(),"Sign up Successful",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.home2)
                } else {
                    Toast.makeText(requireContext(),"Sign up Failed: ${task.exception}",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, phone: String, email: String, uid: String) {

        dbRef.child("users").child(uid).setValue(User.Users(name,phone,email,uid,
            ""))
    }

    private fun validateSignUp():String {
        if (binding.etEmailSignUp.text.isNullOrEmpty()){
            return "Please enter email"
        }else if(binding.etEmailSignUp.text!!.length < 15){
            return "Please enter valid email"
        }else if(binding.etPasswordSignUp.text.isNullOrEmpty()){
            return "Please enter password"
        }else if (binding.etPasswordSignUp.text!!.length < 6){
            return "Password minimum length is 6"
        }
        return "OK"
    }
}