package com.qccr.sh.response;

/**
 * Created by xianchao.yan on 2015/11/4.
 */
public class ResponseUtil {
    public static <T> Response<T> success() {
        return new Response<T>(CodeTable.SUCCESS.getCode(), CodeTable.SUCCESS.getMessage());
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(CodeTable.SUCCESS.getCode(), CodeTable.SUCCESS.getMessage(), data);
    }

    public static <T> Response<T> exception() {
        return new Response<T>(CodeTable.EXCEPTION.getCode(), CodeTable.EXCEPTION.getMessage());
    }

    public static <T> Response<T> exception(String message) {
        return new Response<T>(CodeTable.EXCEPTION.getCode(), message);
    }

    public static <T> Response<T> error() {
        return new Response<T>(CodeTable.ERROR.getCode(), CodeTable.ERROR.getMessage());
    }

    public static <T> Response<T> error(String message) {
        return new Response<T>(CodeTable.ERROR.getCode(), message);
    }

    public static <T> Response<T> invalid() {
        return new Response<T>(CodeTable.INVALID_ARGS.getCode(), CodeTable.INVALID_ARGS.getMessage());
    }

    public static <T> Response<T> invalid(String message) {
        return new Response<T>(CodeTable.INVALID_ARGS.getCode(), message);
    }

    public static <T> Response<T> response(CodeTable codeTable) {
        return new Response<T>(codeTable.getCode(), codeTable.getMessage());
    }

    public static <T> Response<T> response(CodeTable codeTable, T data) {
        return new Response<T>(codeTable.getCode(), codeTable.getMessage(), data);
    }
}
