package com.yildirimomer.tokenqr.core

/**
 * Created by OMER YILDIRIM on 12/4/21.
 * yildirimomer01@gmail.com
 */
class Constants {
    companion object {
        const val BASE_URL = "https://sandbox-api.payosy.com"
        const val GET_QR_API = "/api/get_qr_sale"
        const val GET_PAYMENT_API = "/api/get_qr_sale"
        const val CLIENT_ID = "x-ibm-client-id: d56a0277-2ee3-4ae5-97c8-467abeda984d"
        const val CLIENT_SECRET =
            "x-ibm-client-secret: U1wY2tV5dU2rO7iF7qI7wI4sH8pF0vO8oQ2fE1iS5tU4vW5kO1"

        const val HEADER_1 = "Accept: application/json"
        const val HEADER_2 = "Content-Type: application/json"
    }
}