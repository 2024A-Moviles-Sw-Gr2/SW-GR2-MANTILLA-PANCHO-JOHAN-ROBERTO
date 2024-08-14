package com.example.examen2bm

import android.os.Parcel
import android.os.Parcelable

class BFerreteria(
    val idFerreteria: Int,
    val nombre: String,
    val abierta: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "$nombre"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idFerreteria)
        parcel.writeString(nombre)
        parcel.writeString(abierta)
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BFerreteria> {
        override fun createFromParcel(parcel: Parcel): BFerreteria {
            return BFerreteria(parcel)
        }

        override fun newArray(size: Int): Array<BFerreteria?> {
            return arrayOfNulls(size)
        }
    }


}