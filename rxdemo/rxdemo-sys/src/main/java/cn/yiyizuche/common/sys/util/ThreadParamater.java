package cn.yiyizuche.common.sys.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.sys.util
 * @FileName : ThreadParamater.java
 * @Description : 线程变量
 * @author : lipeng
 * @CreateDate : 2017/3/16 11:34
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class ThreadParamater extends ThreadLocal{
    /**
     * 初始化线程局部变量，重写initialValue方法
     */
    private static final ThreadLocal threadLocal = new ThreadLocal(){
        /**
         * 保存线程变量的容器
         */
        private Map map;
        protected Object initialValue(){
            map = new HashMap();
            return map;
        }
    };

    /**
     * @Method: set
     * @Description: set  设置变量
     * @param key 要保存的变量的键
     * @param value  要保存的值
     * @return void (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/16 11:35
     */
    public static void set(String key, Object value){
        Map map = (Map)threadLocal.get();
        map.put(key, value);
    }

    /**
     * @Method: get
     * @Description: 返回变量
     * @param key 查询变量的键
     * @return java.lang.Object
     * @author : lipeng
     * @CreateDate : 2017/3/16 11:35
     */
    public static Object get(String key){
        Map map = (Map)threadLocal.get();
        return map.get(key);
    }

    /**
     * @Method: remove
     * @Description: 清除变量
     * @param
     * @return void
     * @author : lipeng
     * @CreateDate : 2017/3/16 11:36
     */
    public void remove(){
        Map map = (Map)threadLocal.get();
        map.clear();
        threadLocal.set(map);
    }
}
