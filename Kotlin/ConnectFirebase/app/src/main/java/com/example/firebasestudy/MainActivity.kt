package com.example.firebasestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebasestudy.databinding.ActivityMainBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference

        writeNewUser("qwe9","hong","94@")
        writeNewPost("asd","d","a","353")


        database.child("users").child("qwe9").get().addOnSuccessListener {
            Log.i("firebase11", "Got value ${it.value}")
        }.addOnFailureListener{
            Log.e("firebase11", "Error getting data", it)
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