package com.example.signupupsigninprojects

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.signupupsigninprojects.R.id.txtWelcome
import com.example.signupupsigninprojects.R.id.txtemail
import com.example.signupupsigninprojects.R.id.txtpassword

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_activety)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val email = intent.getStringExtra(SignInActivity.KEY1)
        val password = intent.getStringExtra(SignInActivity.KEY3)

        val welcomeText = findViewById<TextView>(txtWelcome)
        val emailText = findViewById<TextView>(txtemail)
        val passwordText = findViewById<TextView>(txtpassword)


        welcomeText.text = "Welcome$name"
        emailText.text = "Mail :$email"
        passwordText.text = "Password :$password"
    }
}