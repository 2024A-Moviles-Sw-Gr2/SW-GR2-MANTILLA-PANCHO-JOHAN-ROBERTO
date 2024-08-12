package com.example.ferreteria

import android.os.Parcel
import android.os.Parcelable

class BFerreteria(
    val idFerreteria: Int,
    val nombre: String,
    val abierta: String?,
    val numeroTelefono: Int,
    val area: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readFloat()
    ) {

    }

    override fun toString(): String {
        return "$idFerreteria, '$nombre', '$abierta', $numeroTelefono, $area)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idFerreteria)
        parcel.writeString(nombre)
        parcel.writeString(abierta)
        parcel.writeInt(numeroTelefono)
        parcel.writeFloat(area)
    }


    override fun describeContents(): Int {
        return 0;
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