package com.currency.converter.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INCORRECT_REQUEST_BODY(1000, Constants.INCORRECT_REQUEST_BODY_MSG),
    INTERNAL_ERROR_SERVER_HAS_NO_DATA(1004, Constants.INTERNAL_ERROR_SERVER_HAS_NO_DATA_MSG),
    NO_SUCH_CURRENCY(1005, Constants.NO_SUCH_CURRENCY_MSG);
    private final int code;
    private final String msg;

    public static class Constants {
        public final static String INCORRECT_REQUEST_BODY_MSG = "Please check again your inputs. Base, symbol and amount must have three characters and should not be empty.";
        public final static String INTERNAL_ERROR_SERVER_HAS_NO_DATA_MSG = "Problem with the server. Please contact an administrator";
        public final static String NO_SUCH_CURRENCY_MSG = "Currency code is invalid";
    }
}
