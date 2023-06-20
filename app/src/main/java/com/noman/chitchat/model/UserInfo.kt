package com.noman.chitchat.model

import android.os.Parcel
import android.os.Parcelable

data class UserInfo(val userInfoList: List<UserInfoList>) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(UserInfoList)!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(userInfoList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserInfo> {
        override fun createFromParcel(parcel: Parcel): UserInfo {
            return UserInfo(parcel)
        }

        override fun newArray(size: Int): Array<UserInfo?> {
            return arrayOfNulls(size)
        }
    }

    data class UserInfoList(
        var email: String? = null,
        var image: String? = null,
        var name: String? = null,
        var phone: String? = null,
        var uid: String? = null
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(email)
            parcel.writeString(image)
            parcel.writeString(name)
            parcel.writeString(phone)
            parcel.writeString(uid)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<UserInfoList> {
            override fun createFromParcel(parcel: Parcel): UserInfoList {
                return UserInfoList(parcel)
            }

            override fun newArray(size: Int): Array<UserInfoList?> {
                return arrayOfNulls(size)
            }
        }
    }
}

