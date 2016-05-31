package com.qccr.sh.filter;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by hubihua@qccr.com on 2016/3/18.
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{
        public XssHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getHeader(String name) {
            return StringEscapeUtils.escapeHtml4(super.getHeader(name));
        }

        @Override
        public String getQueryString() {
            return StringEscapeUtils.escapeHtml4(super.getQueryString());
        }

        @Override
        public String getParameter(String name) {
            return StringEscapeUtils.escapeHtml4(super.getParameter(name));
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            if(values != null) {
                int length = values.length;
                String[] escapseValues = new String[length];
                for(int i = 0; i < length; i++){
                    escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
                }
                return escapseValues;
            }
            return super.getParameterValues(name);
        }
}
