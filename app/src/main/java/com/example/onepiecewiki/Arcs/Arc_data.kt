package com.example.onepiecewiki.Arcs

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class Arc_data(@ColumnInfo(name = "id") val id: Int,
                     val arc_number: String,
                    @ColumnInfo(name = "arc_title") val arc_title: String,
                     val arc_chapters: String,
                     val arc_volume: String,
                     val arc_episode: String,
                     val arc_favorite : String  = "No"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(arc_number)
        parcel.writeString(arc_title)
        parcel.writeString(arc_chapters)
        parcel.writeString(arc_volume)
        parcel.writeString(arc_episode)
        parcel.writeString(arc_favorite) // Agregar arc_favorite al parcel

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Arc_data> {
        override fun createFromParcel(parcel: Parcel): Arc_data {
            return Arc_data(parcel)
        }

        override fun newArray(size: Int): Array<Arc_data?> {
            return arrayOfNulls(size)
        }
    }
}
