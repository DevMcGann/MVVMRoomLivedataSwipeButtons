package com.gsoft.argentina.probandolivedataroommvvm.ui.adaptadores


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.gsoft.argentina.probandolivedataroommvvm.R

import com.gsoft.argentina.probandolivedataroommvvm.data.model.Entidad


class Adaptador(listaDeElementos: MutableList<Entidad>) : RecyclerView.Adapter<Adaptador.EjemploViewHolder>() {


    private var lista : MutableList<Entidad> = listaDeElementos



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjemploViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent , false)
        return EjemploViewHolder(vista)
    }

    override fun onBindViewHolder(holder: EjemploViewHolder, position: Int) {
        holder.texto.text = lista[position].nombre
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    inner class EjemploViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val texto : TextView

        init {
            texto = itemView.findViewById(R.id.t_item)

            itemView.setOnClickListener(){
                val position : Int = adapterPosition
                Toast.makeText(itemView.context, "Hiciste click en  ${lista[position]}", Toast.LENGTH_LONG).show()
            }
        }
    }

}
