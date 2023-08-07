package com.example.onepiecewiki.Crews

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class Crew_data(
    @ColumnInfo(name = "id") var id: Int,
    var french_name: String,
    var roman_name: String,
    var description: String,
    var total_prime: String,
    var number: String,
    var status: String,
    var affiliation: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(french_name)
        parcel.writeString(roman_name)
        parcel.writeString(description)
        parcel.writeString(total_prime)
        parcel.writeString(number)
        parcel.writeString(status)
        parcel.writeString(affiliation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Crew_data> {
        override fun createFromParcel(parcel: Parcel): Crew_data {
            return Crew_data(parcel)
        }

        override fun newArray(size: Int): Array<Crew_data?> {
            return arrayOfNulls(size)
        }
    }
}
