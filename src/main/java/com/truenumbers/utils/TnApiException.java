package com.truenumbers.utils;

import lombok.Getter;

public class TnApiException extends Exception {

    @Getter
    protected String code;

    @Getter
    protected String app;

    public TnApiException(String message, String code, String app) {
        super(message);
        this.code = code;
        this.app = app;
    }

    public TnApiException(String message, String code) {
        super(message);
        this.code = code;
    }
}
