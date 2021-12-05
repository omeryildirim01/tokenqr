package com.yildirimomer.tokenqr.data.remote

import com.yildirimomer.tokenqr.core.Constants
import com.yildirimomer.tokenqr.model.dto.GetQrRequest
import com.yildirimomer.tokenqr.model.dto.GetQrResponse
import com.yildirimomer.tokenqr.model.dto.PaymentRequest
import com.yildirimomer.tokenqr.model.dto.PaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
interface ApiService {

    @Headers(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.HEADER_1, Constants.HEADER_2)
    @POST(Constants.GET_QR_API)
    suspend fun getQrApi(@Body totalReceiptAmount: GetQrRequest): Response<GetQrResponse>

    @Headers(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.HEADER_1, Constants.HEADER_2)
    @POST(Constants.GET_PAYMENT_API)
    suspend fun getPaymentApi(@Body paymentRequest: PaymentRequest): Response<PaymentResponse>
}