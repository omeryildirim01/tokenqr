package com.yildirimomer.tokenqr.data.remote

import com.yildirimomer.tokenqr.model.GetQrRequest
import javax.inject.Inject

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getQrApi(totalReceiptAmount: GetQrRequest) = apiService.getQrApi(totalReceiptAmount)
}