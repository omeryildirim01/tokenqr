package com.yildirimomer.tokenqr.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@Parcelize
data class GetQrResponse(
    val QRdata: String?,
    val returnCode: Int?,
    val returnDesc: String?,
    var amount: Int?
) : Parcelable