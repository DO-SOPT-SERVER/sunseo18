package org.sopt.www.FirstSeminarKotlin.dto

class HealthCheckResponse private constructor(val status: String) {

    companion object {
        private const val STATUS_OK = "OK"

        fun ok(): HealthCheckResponse {
            return HealthCheckResponse(STATUS_OK)
        }
    }
}
