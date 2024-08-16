package com.example.proyecto2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class EquipoDeportivo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipo_deportivo)

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)

        // Configura el ViewPager
        val adapter = FragmentPagerAdapter(this)
        viewPager.adapter = adapter

        // Configura el TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Equipo"
                    tab.setIcon(R.mipmap.equipo)
                }
                1 -> {
                    tab.text = "Préstamo"
                    tab.setIcon(R.mipmap.prestamo)
                }
                2 -> {
                    tab.text = "Perfil"
                    tab.setIcon(R.mipmap.perfil)
                }
            }
        }.attach()
    }
}
