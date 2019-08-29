package com.xander.mdblog.exception;

import com.xander.mdblog.enums.ErrorCodeEnum;
import org.slf4j.helpers.MessageFormatter;

/**
 *
 * @author 叶振东
 * @date 2018-11-05
 */
public class BusinessException extends RuntimeException {
    private int code;
    private String snapshot;

    public BusinessException(ErrorCodeEnum errorCode, String snapshotFormat, Object... argArray) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public BusinessException(int code, String message, String snapshotFormat, Object... argArray) {
        super(message);
        this.code = code;
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public BusinessException(String message) {
        super(message);
        this.code = 1;
    }

    public int getCode() {
        return code;
    }

    public String getSnapshot() {
        return snapshot;
    }
}