package com.example.examen2bm

import android.os.Parcel
import android.os.Parcelable

class BHerramienta(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val ferreteriaId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun toString(): String {
        return "$nombre , $${String.format("%.2f", precio)}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeDouble(precio)
        parcel.writeInt(ferreteriaId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BHerramienta> {
        override fun createFromParcel(parcel: Parcel): BHerramienta {
            return BHerramienta(parcel)
        }

        override fun newArray(size: Int): Array<BHerramienta?> {
            return arrayOfNulls(size)
        }
    }
}