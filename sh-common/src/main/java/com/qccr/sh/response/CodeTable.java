package com.qccr.sh.response;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by xianchao.yan on 2015/11/4.
 */
public enum CodeTable {

    SUCCESS(0, "成功!"),
    EXCEPTION(1, "处理发生异常"),
    ERROR(2, "服务暂不可用"),
    INVALID_ARGS(200, "请求参数无效!");

    private static final ConcurrentMap<Integer, CodeTable> CODE_MAP;
    private final int code;
    private final String message;

    private CodeTable(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeTable fromCode(Integer code) {
        return (CodeTable) CODE_MAP.get(code);
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    static {
        CODE_MAP = new ConcurrentHashMap<Integer, CodeTable>(
                values().length);

        for (CodeTable codeTable : values())
            CODE_MAP.put(Integer.valueOf(codeTable.code), codeTable);
    }
}
