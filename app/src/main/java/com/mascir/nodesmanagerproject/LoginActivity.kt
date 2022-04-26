package com.mascir.nodesmanagerproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity(),   View.OnClickListener{
    private var passwordEditText: TextInputEditText? = null
    private var emailEditText: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signinBtn = findViewById<Button>(R.id.login_btn)
        passwordEditText = findViewById(R.id.passwd_edit_text)
        emailEditText = findViewById(R.id.email_edit_text)

        signinBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
         if (v!!.id == R.id.login_btn) {
            if (emailEditText!!.text.toString().isEmpty() || passwordEditText!!.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this@LoginActivity, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show()
            } else {
                if (emailEditText!!.text.toString().equals(
                        "admin",
                        ignoreCase = true
                    ) && passwordEditText!!.text.toString() == "ad123"
                ) startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                ) else {
                    Toast.makeText(this@LoginActivity, "Nom d'utilisateur ou mot de passe est incorrect", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}