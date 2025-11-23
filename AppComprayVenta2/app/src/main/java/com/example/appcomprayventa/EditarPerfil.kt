package com.example.appcomprayventa

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.registerForAllProfilingResults
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.appcomprayventa.databinding.ActivityEditarPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hbb20.CountryPickerView

class EditarPerfil : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPerfilBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    private var imageUri : Uri ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Por favor espere")
        progressDialog.setCanceledOnTouchOutside(false)

       cargarInfo()

        binding.FABCambiarImg.setOnClickListener {
            selec_imagen_de()
        }
    }

    private fun cargarInfo() {
        val ref = FirebaseDatabase.getInstance().getReference("usuarios")
        ref.child("${firebaseAuth.uid}")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val nombres = "${snapshot.child("nombres").value}"
                    val imagen = "${snapshot.child("urlImagenPerfil").value}"
                    val fecha_nacimiento = "${snapshot.child("fecha_nacimiento").value}"
                    val telefono = "${snapshot.child("telefono").value}"
                    val codigoTelefono = "${snapshot.child("codigoTelefono").value}"

                    binding.EtNombres.setText(nombres)
                    binding.EtFechaNacimiento.setText(fecha_nacimiento)
                    binding.EtTelefono.setText(telefono)

                    try {
                        Glide.with(applicationContext)
                            .load(imagen)
                            .placeholder(R.drawable.ic_perfil)
                            .into(binding.ImgPerfil)

                    } catch (e:Exception){
                        Toast.makeText(this@EditarPerfil, "${e.message}",
                        Toast.LENGTH_SHORT).show()
                    }
                    try {
                        val codigo = codigoTelefono.replace("+","").toInt()
                        binding.selectorCod.setCountryForPhoneCode(codigo)
                    }catch (e:Exception){
                        Toast.makeText(this@EditarPerfil, "${e.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}
private fun selec_imagen_de() {
    val popupMenu = PopupMenu(this, binding.FABCambiarImg)

    popupMenu.menu.add(Menu.NONE, 1, 1, "Camara")
    popupMenu.menu.add(Menu.NONE, 2, 2, "Galeria")
    popupMenu.show()

    popupMenu.setOnMenuItemClickListener { item ->
        val itemid = item.itemId
        if (itemid == 1) {
if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU) {
    concederPermisoCamara.launch(arrayOf(android.Manifest.permission.CAMERA))
}else{
    concederPermisoCamara.launch(arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    ))
}
        } else if (itemid == 2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                imagegenGaleria()
            } else {
                concederPermisoAlmacenamiento.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        return@setOnMenuItemClickListener true
    }
}

private val concederPermisoCamara=
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermission()) { resultado ->
        var concedidosTodos = true
        for (seConcede in resultado.values) {
            concedidosTodos = concedidosTodos && seConcede
        }
        if (concedidosTodos) {
            imagenCamara()
        } else {
            Toast.makeText(this, "No se dieron los permisos", Toast.LENGTH_SHORT).show()
        }
    }

 private fun imagenCamara() {
     val contentvalues = ContentValues()
     contentvalues.put(MediaStore.Images.Media.TITLE, "Imagen")
     contentvalues.put(MediaStore.Images.Media.DESCRIPTION, "image/jpeg")
     imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentvalues)

     val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
     intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
     resultadoCamara_ARL.launch(intent)
}
private val resultadoCamara_ARL=
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()){resultado->
        if(resultado.resultCode== Activity.RESULT_OK){
            try {
                Glide.with(this)
                    .load(imageUri)
                    .placeholder(R.drawable.ic_perfil)
                    .into(binding.ic_perfil)
            }catch (e.Exception){

            }
        }else{
            Toast.makeText(this, "Cancelado",
                Toast.LENGTH_SHORT).show()
        }

        }

private val concederPermisoAlmacenamiento =
    registerForAllProfilingResults(ActivityResultContracts.RequestPermission()) { esConcedido ->
        if (esConcedido) {
            imagenGaleria()
        } else {
            Toast.makeText(
                this,
                "No se dieron los permisos", Toast.LENGTH_SHORT
            )
                .show()
        }
    }

fun imagenGaleria() {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
resultadoGaleria_ARL.launch(intent)

}
private val resultadoGaleria_ARL =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
        if (resultado.resultCode == Activity.RESULT_OK) {
            val data = resultado.data
            imageUri = data!!.data
            try {
                Glide.with(this)
                    .load(imageUri)
                    .placeholder(R.drawable.ic_perfil)
                    .into(binding.ic_perfil)
            }catch (e:Exception){
                Toast.makeText(this, "cancelado", Toast.LENGTH_SHORT).show()
            }
        }

            }



}



