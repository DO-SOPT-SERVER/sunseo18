package org.sopt.www.FirstSeminar.dto;

import lombok.Getter;

@Getter
public class HealthCheckResponse {

    private static final String STATUS_OK = "OK";
    private String status;

    private HealthCheckResponse(String status) {
        this.status = status;
    }

    public static HealthCheckResponse ok() {
        return new HealthCheckResponse(STATUS_OK);
    }
}

