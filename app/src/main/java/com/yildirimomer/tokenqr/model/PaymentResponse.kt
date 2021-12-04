package com.yildirimomer.tokenqr.model

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
data class PaymentResponse(
    val applicationID: String?,
    val posID: String?,
    val returnCode: Int?,
    val returnDesc: String?,
    val sessionID: String?
)