package com.example.wattravl

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val verificationCodeEditText: EditText = findViewById(R.id.verificationCodeEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val verificationCode = verificationCodeEditText.text.toString()

            if (true || email == "user@example.com" && verificationCode == "123456") { // Replace with actual authentication logic
                // Pass the login state to MainActivity
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("isloggedin", true)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid email or verification code", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
