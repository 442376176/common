package com.zcc.utils.RPC;

public class BizException extends RuntimeException {
    private final int status;
    private final String msg;
    private final String data;

    public BizException(int status, String msg, String data) {
        super(msg);
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public BizException(String msg) {
        super(msg);
        this.status = 600;
        this.msg = msg;
        this.data = msg;
    }

    public BizException(String msg, String data) {
        super(msg);
        this.status = 600;
        this.msg = msg;
        this.data = data;
    }

    public static BizException buildError(String msg) {
        return new BizException(600, msg, msg);
    }

    public String getMsg() {
        return this.msg;
    }

    public String getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }
}
