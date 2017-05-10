package cn.yiyizuche.common.sys.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.sys.util
 * @FileName : PropertiesUtil.java
 * @Description : Properties工具类
 * @author : lipeng
 * @CreateDate : 2017/3/25 11:05
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class PropertiesUtil {

    private static Hashtable<String, Properties> pptContainer = new Hashtable<String, Properties>();

    /**
     * @Method: getValue
     * @Description: 获得属性
     * @param propertyFilePath 属性文件(包括类路径)
     * @param key  属性键
     * @return java.lang.String
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:06
     */
    public final static String getValue(String propertyFilePath, String key) {
        Properties ppts = getProperties(propertyFilePath);
        return ppts == null ? null : ppts.getProperty(key);
    }

    /**
     * @Method: getValue
     * @Description: 获得属性文件中Key所对应的值
     * @param propertyFilePath 属性文件路径(包括类路径或文件系统中文件路径)
     * @param key 属性的键
     * @param isAbsolutePath 是否为绝对路径:true|false〔即是本地文件系统路径，比如：C:/test.propreties〕<br>
     *                         <br>
     *                         <b>注：</b>不能通过类路径来获取到属性文件，而只知道属性文件的文件系统路径，即文件系统地址则用此方法来获取其中的Key所对应的Value
     * @return java.lang.String key的属性值
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:06
     */
    public final static String getValue(String propertyFilePath, String key,
                                        boolean isAbsolutePath) {
        if (isAbsolutePath) {
            Properties ppts = getPropertiesByFs(propertyFilePath);
            return ppts == null ? null : ppts.getProperty(key);
        }
        return getValue(propertyFilePath, key);
    }

    /**
     * @Method: getProperties
     * @Description: 获得属性文件的属性
     * @param propertyFilePath 属性文件(包括类路径)
     * @return java.util.Properties 属性
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:16
     */
    public final static Properties getProperties(String propertyFilePath) {
        if (propertyFilePath == null) {
            _log.error("propertyFilePath is null!");
            return null;
        }
        Properties ppts = pptContainer.get(propertyFilePath);
        if (ppts == null) {
            ppts = loadPropertyFile(propertyFilePath);
            if (ppts != null) {
                pptContainer.put(propertyFilePath, ppts);
            }
        }
        return ppts;
    }

    /**
     * @Method: getPropertiesByFs
     * @Description: 获得属性文件的属性
     * @param propertyFilePath 属性文件路径(包括类路径及文件系统路径)
     * @return java.util.Properties 属性文件对象 Properties
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:16
     */
    public final static Properties getPropertiesByFs(String propertyFilePath) {
        if (propertyFilePath == null) {
            _log.error("propertyFilePath is null!");
            return null;
        }
        Properties ppts = pptContainer.get(propertyFilePath);
        if (ppts == null) {
            ppts = loadPropertyFileByFileSystem(propertyFilePath);
            if (ppts != null) {
                pptContainer.put(propertyFilePath, ppts);
            }
        }
        return ppts;
    }

    /**
     * @Method: loadPropertyFile
     * @Description: 加载属性
     * @param propertyFilePath 属性文件(包括类路径)
     * @return java.util.Properties (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:17
     */
    private static Properties loadPropertyFile(String propertyFilePath) {
        java.io.InputStream is = PropertiesUtil.class
                .getResourceAsStream(propertyFilePath);
        if (is == null) {
            return loadPropertyFileByFileSystem(propertyFilePath);
        }
        Properties ppts = new Properties();
        try {
            ppts.load(is);
            return ppts;
        } catch (Exception e) {
            _log.debug("加载属性文件出错:" + propertyFilePath, e);
            return null;
        }
    }

    /**
     * @Method: loadPropertyFileByFileSystem
     * @Description: 从文件系统加载属性文件
     * @param propertyFilePath 属性文件(文件系统的文件路径)
     * @return java.util.Properties 属性
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:18
     */
    private static Properties loadPropertyFileByFileSystem(
            final String propertyFilePath) {
        try {
            Properties ppts = new Properties();
            ppts.load(new java.io.FileInputStream(propertyFilePath));
            return ppts;
        } catch (java.io.FileNotFoundException e) {
            _log.error("FileInputStream(\"" + propertyFilePath
                    + "\")! FileNotFoundException: " + e);
            return null;
        } catch (java.io.IOException e) {
            _log.error("Properties.load(InputStream)! IOException: " + e);
            return null;
        }
    }

    /**
     * @Method: setValueAndStore
     * @Description: 对存在的属性文件中添加键值对并保存
     * @param propertyFilePath 属性文件的路径(包括类路径及文件系统路径)
     * @param htKeyValue 键值对Hashtable
     * @return boolean (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:18
     */
    public final static boolean setValueAndStore(String propertyFilePath,
                                                 java.util.Hashtable<String, String> htKeyValue) {
        return setValueAndStore(propertyFilePath, htKeyValue, null);
    }

    /**
     * @Method: setValueAndStore
     * @Description: 对存在的属性文件中添加键值对并保存
     * @param propertyFilePath 属性文件的路径(包括类路径及文件系统路径)
     * @param htKeyValue 键值对Hashtable
     * @param storeMsg 保存时添加的附加信息（注释）
     * @return boolean
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:19
     */
    public final static boolean setValueAndStore(String propertyFilePath,
                                                 java.util.Hashtable<String, String> htKeyValue, String storeMsg) {
        Properties ppts = getProperties(propertyFilePath);

        if (ppts == null || htKeyValue == null) {
            return false;
        }
        ppts.putAll(htKeyValue);
        java.io.OutputStream stream = null;
        try {
            stream = new java.io.FileOutputStream(propertyFilePath);
        } catch (FileNotFoundException e) {
            _log.debug("propertyFilePath = " + propertyFilePath);
            String path = PropertiesUtil.class.getResource(propertyFilePath)
                    .getPath();
            try {
                stream = new java.io.FileOutputStream(path);
            } catch (FileNotFoundException e1) {
                _log.error("FileNotFoundException! path=" + propertyFilePath);
                return false;
            }
        }

        if (stream == null) {
            return false;
        }

        try {
            ppts.store(stream, storeMsg != null ? storeMsg
                    : "set value and store.");
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Method: createPropertiesFile
     * @Description: 创建属性文件
     * @param propertyFilePath 要存储属性文件的路径
     * @param htKeyValue 属性文件中的键值对Hashtable
     * @return boolean
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:20
     */
    public final static boolean createPropertiesFile(String propertyFilePath,
                                                     java.util.Hashtable<String, String> htKeyValue) {
        java.io.File file = new java.io.File(propertyFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        return setValueAndStore(propertyFilePath, htKeyValue,
                "create properties file:" + file.getName());
    }

    /**
     * @Method: setValue
     * @Description: 设置属性值
     * @param propertyFilePath 属性文件(包括类路径)
     * @param key 属性键
     * @param value 属性值
     * @return boolean (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:21
     */
    public final static boolean setValue(String propertyFilePath, String key,
                                         String value) {
        Properties ppts = getProperties(propertyFilePath);
        if (ppts == null) {
            return false;
        }
        ppts.put(key, value);
        return true;
    }

    /**
     * @Method: store
     * @Description: 保存属性文件对象
     * @param properties 属性文件对象
     * @param propertyFilePath 要保存的路径
     * @param msg 保存时添加的附加信息（注释）
     * @return void
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:28
     */
    public final static void store(Properties properties,
                                   String propertyFilePath, String msg) {
        try {
            java.io.OutputStream stream = new java.io.FileOutputStream(
                    propertyFilePath);
            properties.store(stream, msg);
        } catch (java.io.FileNotFoundException e) {
            _log.error("FileOutputStream(" + propertyFilePath
                    + ")! FileNotFoundException: " + e);
        } catch (java.io.IOException e) {
            _log.error("store(stream, msg)! IOException: " + e);
            e.printStackTrace();
        }
    }

    /**
     * @Method: removeValue
     * @Description: 删除属性值
     * @param propertyFilePath 属性文件(包括类路径)
     * @param key 属性键
     * @return java.lang.String (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:29
     */
    public final static String removeValue(String propertyFilePath, String key) {

        Properties ppts = getProperties(propertyFilePath);
        if (ppts == null) {
            return null;
        }
        return (String) ppts.remove(key);
    }

    /**
     * @Method: removeValue
     * @Description: 删除属性文件中的Key数组所对应的键值对
     * @param propertyFilePath 属性文件路径(包括类路径及文件系统路径)
     * @param key key数组
     * @return java.util.Properties (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:29
     */
    public final static Properties removeValue(String propertyFilePath,
                                               String[] key) {
        if (key == null) {
            _log.error("key[] is null!");
            return null;
        }
        Properties ppts = getProperties(propertyFilePath);
        if (ppts == null) {
            return null;
        }
        for (String strKey : key) {
            ppts.remove(strKey);
        }
        return ppts;
    }

    /**
     * @Method: removeValueAndStore
     * @Description: 删除属性文件中的Key数组所对应的键值对，并将属性文件对象持久化（即保存）
     * @param propertyFilePath 属性文件路径(包括类路径及文件系统路径)
     * @param key  属性文件中的key数组
     * @return boolean (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:30
     */
    public final static boolean removeValueAndStore(String propertyFilePath,
                                                    String[] key) {
        Properties ppts = removeValue(propertyFilePath, key);
        if (ppts == null) {
            return false;
        }
        store(ppts, propertyFilePath, "batch remove key value!");
        return true;
    }

    /**
     * @Method: updateValue
     * @Description: 更新指定路径的属性文件中的键所对应的值
     * @param propertyFilePath 属性文件路径(包括类路径及文件系统路径)
     * @param key 属性文件中的key
     * @param newValue 要更新的新值
     * @return boolean (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:30
     */
    public final static boolean updateValue(String propertyFilePath,
                                            String key, String newValue) {
        if (key == null || newValue == null) {
            _log.error("key or newValue is null!");
            return false;
        }
        java.util.Hashtable<String, String> ht = new java.util.Hashtable<String, String>();
        ht.put(key, newValue);
        return setValueAndStore(propertyFilePath, ht, "update " + key
                + "'s value!");
    }

    /**
     * @Method: batchUpdateValue
     * @Description: 批量更新指定路径的属性文件中的键所对应的值
     * @param propertyFilePath 属性文件路径(包括类路径及文件系统路径)
     * @param htKeyValue 要更新的键值对Hashtable
     * @return boolean 成功与否（true|false）
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:31
     */
    public final static boolean batchUpdateValue(String propertyFilePath,
                                                 java.util.Hashtable<String, String> htKeyValue) {
        if (propertyFilePath == null || htKeyValue == null) {
            return false;
        }
        return setValueAndStore(propertyFilePath, htKeyValue,
                "batch update key value!");
    }

    /**
     * @Method: removePropertyFile
     * @Description: 移除加载的属性文件
     * @param propertyFilePath 属性文件(包括类路径)
     * @return java.util.Properties
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:31
     */
    public final static Properties removePropertyFile(String propertyFilePath) {

        return pptContainer.remove(propertyFilePath);
    }

    /**
     * @Method: reloadPropertyFile
     * @Description: 重新加载某个Property文件
     * @param propertyFilePath 要重新加载的Property文件，如果当前内存中没有的话则加载，否则替换
     * @return void
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:32
     */
    public final static void reloadPropertyFile(String propertyFilePath) {
        pptContainer.remove(propertyFilePath);
        loadPropertyFile(propertyFilePath);
    }

    /**
     * @Method: getPpropertyFilePath
     * @Description: 获得属性文件的路径
     * @param pkg 包名
     * @param propertyFileName 属性文件名
     * @return java.lang.String (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/25 11:32
     */
    public final static String getPpropertyFilePath(String pkg,
                                                    String propertyFileName) {

        pkg = pkg == null ? "" : pkg.replaceAll("\\.", "/");
        propertyFileName = propertyFileName.endsWith(".properties") ? propertyFileName
                : (propertyFileName + ".properties");
        return "/" + pkg + "/" + propertyFileName;
    }

    private static Logger _log = LoggerFactory.getLogger(PropertiesUtil.class);

}
