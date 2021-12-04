package com.yildirimomer.tokenqr.model

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
data class PaymentInfo(
    val paymentActionList: List<PaymentAction>?,
    val paymentProcessorID: Int?
)