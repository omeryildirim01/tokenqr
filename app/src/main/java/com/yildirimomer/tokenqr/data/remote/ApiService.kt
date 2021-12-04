package com.yildirimomer.tokenqr.data.remote

import com.yildirimomer.tokenqr.core.Constants
import com.yildirimomer.tokenqr.model.GetQrRequest
import com.yildirimomer.tokenqr.model.GetQrResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
interface ApiService {

    @Headers(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.HEADER_1, Constants.HEADER_2)
    @POST(Constants.GET_QR_API)
    suspend fun getQrApi(@Body totalReceiptAmount: GetQrRequest): Response<GetQrResponse>
}