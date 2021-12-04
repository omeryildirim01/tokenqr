package com.yildirimomer.tokenqr.model

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
data class GetQrResponse(
    val QRdata: String?,
    val returnCode: Int?,
    val returnDesc: String?
)