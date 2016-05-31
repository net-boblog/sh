/*   
  * qccr.com Inc.
  * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.qccr.sh.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
* @Description: bean复制工具类
* @author smatiger 王小虎
* @date 2015年10月26日 下午6:51:39 
*/
public class BeanCopierUtils {
    static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<String, BeanCopier>();

    /**
    * @Description: 实体复制
    * @author smatiger 王小虎
    * @param fromObject 需要复制属性的bean
    * @param toObject 复制之后的bean
    * @return 复制之后的bean
    * @throws
    */
    @SuppressWarnings("unchecked")
    public static <T> T copy(Object fromObject, Object toObject) {
        String key = genKey(fromObject.getClass(), toObject.getClass());
        BeanCopier copier = BEAN_COPIERS.get(key);
        if (copier == null) {
            copier = BeanCopier.create(fromObject.getClass(), toObject.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        }
        copier.copy(fromObject, toObject, null);
        return (T) toObject;
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}