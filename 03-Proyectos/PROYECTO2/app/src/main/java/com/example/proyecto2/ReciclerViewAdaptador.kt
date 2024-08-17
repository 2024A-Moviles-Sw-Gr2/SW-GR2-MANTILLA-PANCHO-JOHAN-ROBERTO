package com.example.proyecto2

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ReciclerViewAdaptador(
    private val lista: List<EquipoModel>,
    private val onItemClick: (EquipoModel) -> Unit
) : RecyclerView.Adapter<
        ReciclerViewAdaptador.MyViewHolder
        >() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView
        val imagenImageView: ImageView
        val tarjetaCardView: CardView

        init {
            tarjetaCardView = view.findViewById(R.id.cv_tarjeta)
            nombreTextView = view.findViewById(R.id.tv_nombre_equipo)
            imagenImageView = view.findViewById(R.id.iv_imagen)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recicler_view_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    //Setear los datos para a iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val elementoActual = this.lista[position]
        holder.nombreTextView.text = elementoActual.nombreEquipo
        holder.imagenImageView.setBackgroundResource(elementoActual.imagenId)

        holder.tarjetaCardView.setOnClickListener {
            onItemClick(elementoActual)
        }
    }
}