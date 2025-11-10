package com.example.firstaplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val iniciosesionbtn = findViewById<Button>(R.id.iniciosesion_btn)
        iniciosesionbtn.setOnClickListener {
            val intent = Intent(this, CarActivity::class.java)
            startActivity(intent)

            val crearusuariobtn = findViewById<Button>(R.id.crearsesion_btn)
            crearusuariobtn.setOnClickListener {
                val intent = Intent(this, RegistrousuarioActivity::class.java)
                startActivity(intent)

                enableEdgeToEdge()
                setContentView(R.layout.activity_loging)
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(
                        systemBars.left,
                        systemBars.top,
                        systemBars.right,
                        systemBars.bottom
                    )
                    insets
                }
            }
        }
    }
}