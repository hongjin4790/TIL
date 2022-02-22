package com.example.firebasestudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasestudy.databinding.ActivityMainBinding
import com.example.firebasestudy.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.signBtn.setOnClickListener {
            createAccount(binding.editId.text.toString(),binding.editPw.text.toString())
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser !=null)
        {
            reload()
        }
    }

    private fun createAccount(email: String, password: String){
       if(email.isNotEmpty()&& password.isNotEmpty()){
           auth?.createUserWithEmailAndPassword(email, password)
               ?.addOnCompleteListener(this){task ->
                   if(task.isSuccessful){
                       Log.d("createAc", "createUserWithEmail:success")
                       val user = auth.currentUser
                       updateUI(user)
                       finish()
                   }else{
                       Log.w("createAc", "createUserWithEmail:failure", task.exception)
                       Toast.makeText(baseContext, "Authentication failed.",
                           Toast.LENGTH_SHORT).show()
                       updateUI(null)
                   }
               }
       }
        else{
           Toast.makeText(baseContext, "입력해주세요",
               Toast.LENGTH_SHORT).show()
       }
    }

    private fun updateUI(user: FirebaseUser?){

    }

    private fun reload() {

    }

}