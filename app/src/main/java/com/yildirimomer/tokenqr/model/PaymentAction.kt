package com.yildirimomer.tokenqr.model

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
data class PaymentAction(
    val amount: Int?,
    val currencyID: Int?,
    val paymentType: Int?,
    val vatRate: Int?
)