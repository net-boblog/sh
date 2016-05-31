package com.qccr.sh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chenguowan
 * @date 2016-05-09
 */
public class IOUtils {

    private static final Logger log = LoggerFactory.getLogger(IOUtils.class);

    /**
     * response输出
     *
     * @param response
     * @param data
     */
    public static void responsePrint(HttpServletResponse response, String data) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(data);
        } catch (IOException e) {
            log.error("error when call response.getWriter()", e);
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
