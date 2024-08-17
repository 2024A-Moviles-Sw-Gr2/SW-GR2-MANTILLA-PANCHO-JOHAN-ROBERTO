package com.example.proyecto2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PrestamoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prestamo, container, false)
        initRecyclerView(view)
        return view
    }

    fun initRecyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_prestamo)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ReciclerViewAdaptadorPrestamo(PrestamoProvider.arregloPrestamos)
    }
}
