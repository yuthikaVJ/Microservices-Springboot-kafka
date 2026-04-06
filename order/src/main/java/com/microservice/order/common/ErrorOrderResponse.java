package com.microservice.order.common;

import lombok.Getter;

@Getter
public class ErrorOrderResponse implements OrderResponse {

    private final String errorMassege;

    public ErrorOrderResponse(String errorMassege){
        this.errorMassege = errorMassege;
    }
}
