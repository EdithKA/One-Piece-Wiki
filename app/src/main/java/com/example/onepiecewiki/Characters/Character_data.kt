package com.example.onepiecewiki.Characters

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class Character_data(
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "french_name") var frenchName: String,
    var job: String,
    var size: String,
    var birthday: String,
    var age: String,
    var bounty: String,
    var status: String,
    @ColumnInfo(name = "crew_id") val crewId: Int?,
    @ColumnInfo(name = "fruit_id") val fruitId: Int?,
    @ColumnInfo(name = "2nd_fruit_id") val secondFruitId: Int?,
    val character_favorite : String  = "No"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!, // Leer como cadena y luego verificar si es nulo
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,

        //No tendremos en cuenta estas tres últimas características ya que para muchos personajes
        //son nulas y no las considero útiles para nuestra app
        parcel.readIntOrNull(),
        parcel.readIntOrNull(),
        parcel.readIntOrNull(),

        parcel.readString()!!,
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(frenchName)
        parcel.writeString(job)
        parcel.writeString(size)
        parcel.writeString(birthday ?: "") // Si el campo es nulo, escribir una cadena vacía en su lugar
        parcel.writeString(age)
        parcel.writeString(bounty)
        parcel.writeString(status)
        parcel.writeIntOrNull(crewId)
        parcel.writeIntOrNull(fruitId)
        parcel.writeIntOrNull(secondFruitId)
        parcel.writeString(character_favorite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character_data> {
        override fun createFromParcel(parcel: Parcel): Character_data {
            return Character_data(parcel)
        }

        override fun newArray(size: Int): Array<Character_data?> {
            return arrayOfNulls(size)
        }

        fun Parcel.writeIntOrNull(value: Int?) { //Permite que guardemos algunos de los datos que deberian ser int,
                                                // como nulos en la base de
            if (value != null) {
                writeInt(value)
            } else {
                writeInt(-1)
            }
        }

        fun Parcel.readIntOrNull(): Int? {
            val value = readInt()
            return if (value != -1) value else null
        }
    }
}