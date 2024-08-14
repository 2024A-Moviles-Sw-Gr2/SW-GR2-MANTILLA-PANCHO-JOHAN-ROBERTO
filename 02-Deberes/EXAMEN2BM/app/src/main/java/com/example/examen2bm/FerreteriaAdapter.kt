package com.example.examen2bm

import android.view.View

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class FerreteriaAdapter(context: Context, ferreterias: List<BFerreteria>) :
    ArrayAdapter<BFerreteria>(context, 0, ferreterias) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_ferreteria, parent, false)
        }

        val ferreteria = getItem(position)
        val nombreTextView: TextView = itemView!!.findViewById(R.id.tv_nombre_ferreteria)
        val btnVerMapa: Button = itemView.findViewById(R.id.btn_ver_mapa)

        nombreTextView.text = ferreteria?.nombre

        if (position == 0) {
            btnVerMapa.visibility = View.VISIBLE
            btnVerMapa.setOnClickListener {
                ferreteria?.let { (context as MainActivity).verMapa(it) }
            }
        } else {
            btnVerMapa.visibility = View.GONE
        }

        return itemView
    }
}