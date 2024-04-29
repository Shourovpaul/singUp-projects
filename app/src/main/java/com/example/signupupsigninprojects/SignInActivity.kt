package com.example.signupupsigninprojects

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.signupupsigninprojects.R.id.signInPage
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@Suppress("NAME_SHADOWING")
class SignInActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    companion object{
        const val KEY1 = "com.example.signupupsigninprojects.mail"
        const val KEY2 = "com.example.signupupsigninprojects.name"
        const val KEY3 = "com.example.signupupsigninprojects.pass"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in_activety)


        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.edtSignUserName)


       signInButton.setOnClickListener {

           val name = userName.text.toString()
           if(name.isNotEmpty()){
               readData(name)
           }else{
               Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show()

           }
       }
    }// onCreate Method Over.


    private fun readData(name: String) {

       database = FirebaseDatabase.getInstance().getReference("Users")

        database.child(name).get().addOnSuccessListener {

            // if User Exist Or  not.
            if(it.exists()){
                //Welcome user in your app.in intent amd also pass.
               val email = it.child("email").value
                val name = it.child("name").value
                val password = it.child("password").value

                val intentWelcome = Intent(this,HomeActivity::class.java)
                intentWelcome.putExtra(KEY1, email.toString())
                intentWelcome.putExtra(KEY2, name.toString())
                intentWelcome.putExtra(KEY3, password.toString())
                startActivity(intentWelcome)

            }else{
                Toast.makeText(this, "User does not exist." , Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed, Error in DB,", Toast.LENGTH_SHORT).show()
        }
    }
}