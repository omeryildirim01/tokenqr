package com.yildirimomer.tokenqr.model

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
data class PaymentRequest(
    val QRdata: String?,
    val paymentInfoList: List<PaymentInfo>?,
    val receiptMsgCustomer: String?,
    val receiptMsgMerchant: String?,
    val returnCode: Int?,
    val returnDesc: String?
)