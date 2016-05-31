package com.qccr.sh.response;

/**
 * Created by xianchao.yan on 2015/11/4.
 */
public class Response<T> {

    private int code;
    private String message;
    private T data;

    public Response() {
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return CodeTable.SUCCESS.getCode() == this.code;
    }

    public int getCode() {
        return this.code;
    }

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return "Response{code:" + this.code + ", message:'" + this.message + '\'' + ", data:" + this.data + '}';
    }
}
