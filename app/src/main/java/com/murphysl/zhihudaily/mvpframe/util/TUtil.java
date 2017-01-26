package com.murphysl.zhihudaily.mvpframe.util;

import java.lang.reflect.ParameterizedType;

/**
 * TUtil
 *
 * @author: MurphySL
 * @time: 2017/1/19 17:55
 */

public class TUtil {
    public static <T> T getT(Object o , int i){
        try{
            return ((Class<T>) (((ParameterizedType)(o.getClass().getGenericSuperclass()))
                    .getActualTypeArguments()[i]))
                    .newInstance();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (ClassCastException e){
            e.printStackTrace();
        }

        return null;
    }

    // 获得类名className对应的Class对象
    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
