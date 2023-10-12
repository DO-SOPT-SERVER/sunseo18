package org.sopt.www.FirstSeminarKotlin.controller

import org.sopt.www.FirstSeminarKotlin.dto.HealthCheckResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthCheckController {

    @GetMapping("/v1")
    fun healthCheck(): Map<String, String> {
        val response: MutableMap<String, String> = HashMap()
        response.put("status", "ok")
        return response
    }

    @GetMapping("/v2")
    fun healthCheckV2(): ResponseEntity<String> {
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/v3")
    fun healthCheckv3(): String {
        return "ok"
    }

    @GetMapping("/v4")
    fun healthCheckV4(): ResponseEntity<Map<String, String>> {
        val response: MutableMap<String, String> = HashMap()
        response.put("status", "ok")
        return ResponseEntity.ok(response)
    }

    @GetMapping("/v5")
    fun healthCheckV5(): ResponseEntity<HealthCheckResponse> {
        return ResponseEntity.ok(HealthCheckResponse.ok())
    }
}
