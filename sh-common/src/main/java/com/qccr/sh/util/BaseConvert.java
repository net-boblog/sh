/**
 * qccr.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.qccr.sh.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * 对象转换基类
 * @author wqi
 * @version v 0.1 2015年8月24日 上午11:17:24 wqi Exp $
 */
public class BaseConvert {
    private static final Logger log = LoggerFactory.getLogger(BaseConvert.class);

    /**
     * @description:   Do to Ro
     * @author: wqi
     * @param source
     * @return
     * @date: 2015年8月24日 上午11:18:33
     */
    public static <T> T beanConvert(Object source, Class<T> target) {
        T targetObj;
        try {
            if (source == null) {
                return null;
            }
            targetObj = target.newInstance();
            BeanUtils.copyProperties(source, targetObj);
            return targetObj;
        } catch (InstantiationException e) {
            log.error(String.format("对象转换异常[%s] to [%s]", source.getClass(), target), e);
        } catch (IllegalAccessException e) {
            log.error(String.format("对象转换异常[%s] to [%s]", source.getClass(), target), e);
        }
        return null;
    }

    /**
     * @description:   Do to Ro
     * @author: wqi
     * @param source
     * @return
     * @date: 2015年8月24日 上午11:18:33
     */
    public static <T,S> List<T> listBeanConvert(List<S> source, Class<T> target) {
        if (source == null) {
            return null;
        }

        List<T> targetObj = new ArrayList<T>();
        for (S s : source) {
            targetObj.add(beanConvert(s, target));
        }

        return targetObj;
    }

    /**
     * @description:
     * @author: wangqi
     * @param source
     * @param target
     * @param ignoreProperties
     * @return
     * @date: 2015年9月1日 下午7:33:08
     */
    public static <T> T beanConvert(Object source, Class<T> target, String... ignoreProperties) {
        T targetObj;
        try {
            if (source == null) {
                return null;
            }
            targetObj = target.newInstance();
            BeanUtils.copyProperties(source, targetObj, ignoreProperties);
            return targetObj;
        } catch (InstantiationException e) {
            log.error(String.format("对象转换异常[%s] to [%s]", source.getClass(), target), e);
        } catch (IllegalAccessException e) {
            log.error(String.format("对象转换异常[%s] to [%s]", source.getClass(), target), e);
        }
        return null;
    }

}
