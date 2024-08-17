package com.example.proyecto2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EquipoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_equipo, container, false)
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.mReciclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ReciclerViewAdaptador(EquipoProvider.arregloEquipos) { equipo ->
            // Aquí manejas el clic del item
            val intent = Intent(requireContext(), EquipoViewActivity::class.java).apply {
                putExtra("EQUIPO_NOMBRE", equipo.nombreEquipo)
                putExtra("EQUIPO_IMAGEN_ID", equipo.imagenId)
            }
            startActivity(intent)
        }
    }
}