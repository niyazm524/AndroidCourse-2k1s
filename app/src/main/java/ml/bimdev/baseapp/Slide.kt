package ml.bimdev.baseapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Slide(val id: Long, val title: String, val content: String, val url: String) : Parcelable
