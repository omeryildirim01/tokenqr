package com.yildirimomer.tokenqr.data

import com.yildirimomer.tokenqr.core.BaseApiResponse
import com.yildirimomer.tokenqr.core.NetworkResult
import com.yildirimomer.tokenqr.data.remote.RemoteDataSource
import com.yildirimomer.tokenqr.model.GetQrRequest
import com.yildirimomer.tokenqr.model.GetQrResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getQrApi(totalReceiptAmount: GetQrRequest): Flow<NetworkResult<GetQrResponse>> {
        return flow<NetworkResult<GetQrResponse>> {
            emit(safeApiCall { remoteDataSource.getQrApi(totalReceiptAmount) })
        }.flowOn(Dispatchers.IO)
    }
}