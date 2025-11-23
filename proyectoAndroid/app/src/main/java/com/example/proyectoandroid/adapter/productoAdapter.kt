package com.example.proyectoandroid.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoandroid.R
import com.example.proyectoandroid.modelos.producto

class productoAdapter(val productos:ArrayList<producto>): RecyclerView.Adapter<productoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productoAdapter.ViewHolder {
       var vista= LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: productoAdapter.ViewHolder, position: Int) {
        holder.binditems(productos[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {

    }
        fun binditems(producto: producto) {
            val descripcion = itemView.findViewById<TextView>(R.id.textView5)
            descripcion.text = producto.descripcion
        }
    }
}

