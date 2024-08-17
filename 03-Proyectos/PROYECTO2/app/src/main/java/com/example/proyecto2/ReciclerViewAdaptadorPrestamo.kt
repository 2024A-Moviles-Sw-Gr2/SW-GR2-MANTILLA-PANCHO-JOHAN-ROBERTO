package com.example.proyecto2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ReciclerViewAdaptadorPrestamo(
    private val lista:List<PrestamoModel>
): RecyclerView.Adapter<
        ReciclerViewAdaptadorPrestamo.MyViewHolder
        >() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val fechaTextView: TextView
        val horarioTextView: TextView
        val ubicacionTextView: TextView
        val tarjetaCardView: CardView
        init {
            tarjetaCardView = view.findViewById(R.id.cv_prestamo)
            fechaTextView = view.findViewById(R.id.tv_fecha)
            horarioTextView = view.findViewById(R.id.tv_horario)
            ubicacionTextView = view.findViewById(R.id.tv_ubicacion)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recicler_view_prestamo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }
    //Setear los datos para a iteracion
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val elementoActual = this.lista[position]
        holder.fechaTextView.text = elementoActual.fecha
        holder.horarioTextView.text = elementoActual.horario
    }
}