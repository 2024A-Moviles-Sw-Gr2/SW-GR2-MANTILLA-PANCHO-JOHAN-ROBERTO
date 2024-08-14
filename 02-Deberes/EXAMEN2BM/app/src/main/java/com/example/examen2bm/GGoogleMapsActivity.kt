package com.example.examen2bm

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.snackbar.Snackbar
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.maps.OnMapReadyCallback

class GGoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var ferreteriaNombre: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)

        ferreteriaNombre = intent.getStringExtra("FERRETERIA_NOMBRE") ?: "Ferretería"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Coordenadas predefinidas (ejemplo: centro de Quito)
        val ubicacionPredefinida = LatLng(-0.1807, -78.4678)

        mMap.addMarker(MarkerOptions().position(ubicacionPredefinida).title(ferreteriaNombre))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionPredefinida, 15f))

        // Actualizar el TextView con el nombre de la ferretería
        findViewById<TextView>(R.id.txt_mapa_elemento).text = ferreteriaNombre
    }
}