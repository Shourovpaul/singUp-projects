package com.example.signupupsigninprojects

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.signupupsigninprojects.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val edtName = findViewById<TextInputEditText>(R.id.edtUsersName)
        val edtMail = findViewById<TextInputEditText>(R.id.edtMail)
        val edtPass = findViewById<TextInputEditText>(R.id.edtPass)
        val edtConform = findViewById<TextInputEditText>(R.id.edtConformPass)

        btnSignUp.setOnClickListener {

            val name = edtName.text.toString()
            val mail = edtMail.text.toString()
            val password = edtPass.text.toString()
            val conform= edtConform.text.toString()

            val user = User(name, mail, password, conform)
            database = FirebaseDatabase.getInstance().getReference("Users")
            //Create Child Database
            database.child(name).setValue(user).addOnSuccessListener {
                edtName.text?.clear()
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
            }.addOnCanceledListener {
                Toast.makeText(this, "User registered Failed", Toast.LENGTH_SHORT).show()
            }

        }

        val signIn = findViewById<TextView>(R.id.signInPage)
        signIn.setOnClickListener {
            val openSignInActivity = Intent(this, SignInActivity::class.java)
            startActivity(openSignInActivity)
        }



    }

}