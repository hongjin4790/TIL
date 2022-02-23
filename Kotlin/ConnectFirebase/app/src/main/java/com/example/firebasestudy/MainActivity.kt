package com.example.firebasestudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebasestudy.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "KotlinActivity"
    }
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.apply {
            loginBtn.setOnClickListener {
                if(loginId.text.toString().isNotEmpty() && loginPw.text.toString().isNotEmpty()){
                    signIn(loginId.text.toString(),loginPw.text.toString())
                }
            }
            signUpBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun signIn(email: String, password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Log.d("SignIn", "signInWithEmail:success")
                    val intent = Intent(this@MainActivity, NewsActivity::class.java)
                    startActivity(intent)
                }else{
                    Log.w("SignIn", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun writeNewUser(userId: String, name: String, email:String){
        val user = User(name,email)

        database.child("users").child(userId).setValue(user) //하위 노드를 포함하여 모든 데이터를 덮어씁니다.
        database.child("user").child(userId).child("username").setValue(name) //전체 객체를 다시 쓰지 않고도 하위 항목을 업데이트하는 방법
    }

    private fun writeNewPost(userId: String, username:String, title:String, body: String){
        val key = database.child("posts").push().key
        if(key ==null){
            Log.w(TAG, "Couldn't get push key for posts")
            return
        }
        val post = Post(userId,username,title,body)
        val postValues = post.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/posts/$key" to postValues,
            "/user-posts/$userId/$key" to postValues
        )
        database.updateChildren(childUpdates)
    }
}