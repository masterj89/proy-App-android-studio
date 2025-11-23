package com.example.appcomprayventa.Opciones_login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appcomprayventa.MainActivity
import com.example.appcomprayventa.R
import com.example.appcomprayventa.Registro_email
import com.example.appcomprayventa.databinding.ActivityLoginEmailBinding
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: ActivityLoginEmailBinding
private lateinit var firebaseAuth: FirebaseAuth
private lateinit var progressDialog: ProgressDialog

class Login_email : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Por favor espere")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnIngresar.setOnClickListener {
            validarInfo()
        }

        binding.TxtRegistrarme.setOnClickListener {
            startActivity(Intent(this@Login_email, Registro_email::class.java))
        }
    }
    private var email=""
    private var password=""
    private fun validarInfo() {

        email = binding.EtEmail.text.toString().trim()
        password = binding.EtPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.EtEmail.error = "Formato de email invalido"
            binding.EtEmail.requestFocus()
        }
        else if (email.isEmpty()){
            binding.EtEmail.error = "Ingrese su email"
            binding.EtEmail.requestFocus()
        }
        else if (password.isEmpty()){
            binding.EtPassword.error = "Ingrese su password"
            binding.EtPassword.requestFocus()
        }
        else{
            loginUsuario()
        }
    }

    private fun loginUsuario() {
        progressDialog.setMessage("ingresando")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()

        }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(
                    this, "no se pudo iniciar sesión debido a ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }
}