package com.yildirimomer.tokenqr.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.room.TypeConverter
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.yildirimomer.tokenqr.core.BaseFragment
import com.yildirimomer.tokenqr.model.dto.*
import com.yildirimomer.tokenqr.model.entity.PaymentRecord
import java.util.*

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */

fun BaseFragment.hasInternetConnection(): Boolean {
    val connectivityManager = this.context?.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

fun String.getQrCodeImage(): Bitmap? {
    try {
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(this, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    } catch (e: Exception) {
        return null
    }
}

fun GetQrResponse.getDummyPaymentRequest() : PaymentRequest {
    return  PaymentRequest(
        QRdata = this.QRdata,
        returnCode = 1000,
        returnDesc = "success",
        receiptMsgCustomer = "beko Campaign/n2018",
        receiptMsgMerchant = "beko Campaign Merchant/n2018",
        paymentInfoList = listOf(
            PaymentInfo(
                paymentProcessorID = 67,
                paymentActionList = listOf(
                    PaymentAction(
                        amount = this.amount,
                        currencyID = 949,
                        paymentType = 3,
                        vatRate = 800
                    )
                )
            )
        )
    )
}

fun PaymentResponse.getPaymentRecord(paymentAmount: Int?) : PaymentRecord {
    return PaymentRecord(
        id = 0,
        applicationID = this.applicationID,
        posID = this.posID,
        returnCode = this.returnCode,
        returnDesc = this.returnDesc,
        sessionID = this.sessionID,
        paymentAmount = paymentAmount,
        createdAt = null
    )
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}