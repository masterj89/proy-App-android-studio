package com.example.proyectoandroid.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectoandroid.R
import com.example.proyectoandroid.tienda_Activity

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.btn_inicio_sesion)
        button.setOnClickListener {
            // Acción a realizar al hacer clic en el botón
            val intent = Intent(this, tienda_Activity::class.java)
            startActivity(intent)
        }
    }
}