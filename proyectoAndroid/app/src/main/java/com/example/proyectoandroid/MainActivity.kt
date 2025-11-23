    package com.example.proyectoandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoandroid.Activities.loginActivity

    class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_inicio)
        button.setOnClickListener {
            // Acción a realizar al hacer clic en el botón
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }

}
