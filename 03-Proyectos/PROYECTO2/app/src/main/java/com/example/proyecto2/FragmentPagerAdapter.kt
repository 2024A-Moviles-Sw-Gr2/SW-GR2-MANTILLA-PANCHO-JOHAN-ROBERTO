package com.example.proyecto2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EquipoFragment()
            1 -> PrestamoFragment()
            2 -> PerfilFragment()
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}
