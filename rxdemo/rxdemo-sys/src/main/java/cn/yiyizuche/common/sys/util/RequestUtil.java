package cn.yiyizuche.common.sys.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.sys.util
 * @FileName : RequestUtil.java
 * @Description : 处理对于Request的一些得到参数和设置数据的方法
 * @author : lipeng
 * @CreateDate : 2017/3/16 16:24
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class RequestUtil {
	
	/**
	 * @Method: getStringParameter
	 * @Description: 取得请求参数
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @return java.lang.String 参数值
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static String getStringParameter (HttpServletRequest req, String name) {
		if (name == null || name.trim().length()==0)
			return null;
		return req.getParameter(name.trim());
	}
	
	/**
	 * 
	 * @Method: getStringParameter  
	 * @Description: 取得请求参数, 如果参数不存在则返回指定的默认值
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @param defValue 默认值
	 * @return String  参数值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static String getStringParameter (HttpServletRequest req, String name, String defValue) {
		String value = getStringParameter(req, name);
		if (value == null)
			return defValue;
		return value;
	}
	
	/**
	 * 
	 * @Method: getIntegerParameter  
	 * @Description: 取得请求参数, 转换为整数类型。 如果参数不存在或不符合整数格式则抛出例外
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @return int 参数值 
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static int getIntegerParameter (HttpServletRequest req, String name) {
		return Integer.parseInt(getStringParameter(req, name));
	}
	
	/**
	 * 
	 * @Method: getIntegerParameter  
	 * @Description: 取得请求参数, 转换为整数类型。 如果参数不存在或不符合整数格式则抛出例外
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @param defValue 默认值
	 * @return int 参数值 
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static int getIntegerParameter (HttpServletRequest req, String name, int defValue) {
		try {
			return getIntegerParameter(req, name);
		} catch (Exception e) {
			return defValue;
		}
	}
	
	/**
	 * 
	 * @Method: getLongParameter  
	 * @Description: 取得请求参数, 转换为长整数类型。 如果参数不存在或不符合整数格式则抛出例外
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @return  long 参数值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static long getLongParameter (HttpServletRequest req, String name) {
		return Long.parseLong(getStringParameter(req, name));
	}
	
	/**
	 * 
	 * @Method: getLongParameter  
	 * @Description: 取得请求参数, 转换为长整数类型。 如果参数不存在或不符合整数格式则抛出例外
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @param defValue 默认值
	 * @return  long 参数值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static long getLongParameter (HttpServletRequest req, String name, long defValue) {
		try {
			return getLongParameter(req, name);
		} catch (Exception e) {
			return defValue;
		}
	}
	
	/**
	 * 
	 * @Method: getFloatParameter  
	 * @Description: 取得请求参数, 转换为浮点数类型。 如果参数不存在或不符合整数格式则抛出例外
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @return float 参数值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static float getFloatParameter (HttpServletRequest req, String name) {
		return Float.parseFloat(getStringParameter(req, name));
	}
	
	/**
	 * 
	 * @Method: getFloatParameter  
	 * @Description: 取得请求参数, 转换为浮点数类型。 如果参数不存在或不符合整数格式则抛出例外
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @param defValue  默认值
	 * @return float 参数值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static float getFloatParameter (HttpServletRequest req, String name, float defValue) {
		try {
			return getFloatParameter(req, name);
		} catch (Exception e) {
			return defValue;
		}
	}
	
	/**
	 * 
	 * @Method: getDoubleParameter  
	 * @Description: 取得请求参数, 转换为浮点数类型。 如果参数不存在或不符合整数格式则抛出例外
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @return double 参数值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static double getDoubleParameter (HttpServletRequest req, String name) {
		return Double.parseDouble(getStringParameter(req, name));
	}
	
	/**
	 * 
	 * @Method: getDoubleParameter  
	 * @Description: 取得请求参数, 转换为浮点数类型。 如果参数不存在或不符合整数格式则抛出例外
	 * @param req HttpServletRequest
	 * @param name 参数名称
	 * @param defValue 默认值
	 * @return double 参数值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static double getDoubleParameter (HttpServletRequest req, String name, double defValue) {
		try {
			return getDoubleParameter(req, name);
		} catch (Exception e) {
			return defValue;
		}
	}
	
	/**
	 * 
	 * @Method: getStringAttribute  
	 * @Description: 得到request中对应Attribute的属性的值，如果没有，返回null
	 * @param req HttpServletRequest
	 * @param name 属性名称
	 * @return  String 属性值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static String getStringAttribute (HttpServletRequest req, String name) {
		if (name == null || name.trim().length()==0)
			return null;
		return (String) req.getAttribute(name.trim());
	}
	
	/**
	 * 
	 * @Method: getStringAttribute  
	 * @Description: 得到request中对应Attribute的属性的值，如果没有，返回默认值
	 * @param req HttpServletRequest
	 * @param name 属性名称
	 * @param defValue 默认值
	 * @return  String 属性值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static String getStringAttribute (HttpServletRequest req, String name, String defValue) {
		String value;
		try {
			value = getStringAttribute(req, name);
		} catch (Exception e) {
			value = null;
		}
		if (value == null)
			return defValue;
		return value;
	}
	
	/**
	 * 
	 * @Method: getAttribute  
	 * @Description: 得到request中对应Attribute的参数的值,如果没有,返回null
	 * @param req HttpServletRequest
	 * @param name 属性名称
	 * @return Object 属性值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static Object getAttribute (HttpServletRequest req, String name) {
		if (name == null || name.trim().length()==0)
			return null;
		return req.getAttribute(name.trim());
	}
	
	/**
	 * 
	 * @Method: getAttribute  
	 * @Description: 得到request中对应Attribute的参数的值,如果没有,返回指定的默认值
	 * @param req HttpServletRequest
	 * @param name 属性名称
	 * @param defValue 默认值
	 * @return Object 默认值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static Object getAttribute (HttpServletRequest req, String name, Object defValue) {
		Object value = getAttribute(req, name);
		if (value == null)
			return defValue;
		return value;
	}
	
	/**
	 * 
	 * @Method: getSessionAttribute  
	 * @Description: 得到Session中的指定对象, 如果对象不存在则返回指定的默认值
	 * @param req HttpServletRequest
	 * @param name 属性名称
	 * @param defValue 默认值
	 * @return Object 属性值 
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static Object getSessionAttribute (HttpServletRequest req, String name, Object defValue) {
		Object value = getSessionAttribute(req, name);
		if (value == null)
			return defValue;
		return value;
	}
	
	/**
	 * 
	 * @Method: getSessionAttribute  
	 * @Description: 得到Session中的指定对象, 如果对象不存在则返回null
	 * @param req HttpServletRequest
	 * @param name 属性名称
	 * @return Object 属性值 
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static Object getSessionAttribute (HttpServletRequest req, String name) {
		HttpSession session = req.getSession(true);
		return session.getAttribute(name);
	}
	
	/**
	 * 
	 * @Method: setSessionAttribute  
	 * @Description: 向Session中放入对象
	 * @param req HttpServletRequest
	 * @param name 属性名称
	 * @param value 属性值
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static void setSessionAttribute (HttpServletRequest req, String name, Object value) {
		HttpSession session = req.getSession(true);
		session.setAttribute(name, value);
	}
	
	/**
	 * 
	 * @Method: removeSessionAttribute  
	 * @Description: 移除Session中的一个对象
	 * @param req HttpServletRequest
	 * @param name 属性名称
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static void removeSessionAttribute (HttpServletRequest req, String name) {
		setSessionAttribute(req, name, null);			
	}
	
	/**
	 * 
	 * @Method: parameterToAttribute  
	 * @Description: 将请求的参数放入Attribute中
	 * @param req HttpServletRequest
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static void parameterToAttribute (HttpServletRequest req) {
		String k, v;
		Enumeration enumer = req.getParameterNames();
		while (enumer.hasMoreElements()) {
			k = (String) enumer.nextElement();
			v = req.getParameter(k);
			req.setAttribute(k, v);
		}
	}
	
	/**
	 * 
	 * @Method: isFileUpload  
	 * @Description: 判断提交的请求是否是以文件上传的方式 如果是返回 true 否则返回 false
	 * @param req HttpServletRequest
	 * @return  boolean 是否是以文件上传的方式提交
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static boolean isFileUpload (HttpServletRequest req) {
		boolean upload = false;
		if (req.getContentType() != null && req.getContentType().indexOf("multipart/form-data;") != -1)
			upload = true;
		return upload;
	}
	
	/**
	 * 
	 * @Method: encode  
	 * @Description: 加密字符串
	 * @param str 需要加密的字符串
	 * @param encoding 编码
	 * @return String 加密后的字符串
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static String encode (String str, String encoding) {
		try {
            return URLEncoder.encode(str, encoding);
        } catch (Exception e) {
            return str;
        }
	}
	
	/**
	 * 
	 * @Method: decode  
	 * @Description: 解密字符串
	 * @param str 需要进行解密的字符串
	 * @param encoding 编码
	 * @return String 解密后的字符串
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static String decode (String str, String encoding) {
		try {
            return URLDecoder.decode(str, encoding);
        } catch (Exception e) {
            return str;
        }
	}
	
	/**
	 * 
	 * @Method: sendDispatcher  
	 * @Description: 以RequestDispatcher的方式跳转到指定的位置
	 * @param req HttpServletRequest
	 * @param rsp HttpServletResponse
	 * @param url 目标url
	 * @throws ServletException
	 * @throws IOException    (参数描述)
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static void sendDispatcher(HttpServletRequest req,
			HttpServletResponse rsp, String url) throws ServletException,
            IOException {
		req.getRequestDispatcher(!url.substring(0, 1).equals("/")?("/"+url):url).forward(req, rsp);
		return;
	}
	
	/**
	 * 
	 * @Method: sendRedirect  
	 * @Description: 以sendRedirect的方式跳转到指定的位置
	 * @param rsp HttpServletResponse
	 * @param url 目标url
	 * @throws IOException
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static void sendRedirect (HttpServletResponse rsp, String url) throws IOException {
		rsp.sendRedirect(url);
	}
	
	/**
	 * 
	 * @Method: getRequestPrefix  
	 * @Description: 取得请求的前缀
	 * @param req HttpServletRequest
	 * @param reqType 请求类型
	 * @return String 请求前缀字符串
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	private static String getRequestPrefix (HttpServletRequest req, String reqType) {
        String host = req.getHeader("Host");
        String rtnURL = reqType + "://" + host + "/";
        return rtnURL;
	}
	
	/**
	 * 
	 * @Method: getHttpPrefix  
	 * @Description: 取得HTTP请求的前缀
	 * @param req HttpServletRequest
	 * @return String 请求前缀字符串
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static String getHttpPrefix (HttpServletRequest req) {
		return getRequestPrefix(req, "http");	
	}
	
	/**
	 * 
	 * @Method: getFromUrl  
	 * @Description: 取得请求来源的URL
	 * @param req HttpServletRequest
	 * @return String 请求来源的URL
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static String getFromUrl (HttpServletRequest req) {
		return req.getHeader("Referer");
	}
	
	/**
	 * 
	 * @Method: getHttpSession  
	 * @Description: 取得HttpSession
	 * @param req HttpServletRequest
	 * @param sessionId session的id
	 * @return  HttpSession HttpSession
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	public static HttpSession getHttpSession (HttpServletRequest req, String sessionId) {
		return req.getSession(false).getSessionContext().getSession(sessionId);
	}
	
	/**
	 * 
	 * @Method: getIpAddr  
	 * @Description: 获取远程请求者的IP地址
	 * @param req HttpServletRequest
	 * @return String IP地址
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	 public static String getIpAddr(HttpServletRequest req) {
         String ip = req.getHeader("x-forwarded-for");
         
         getIpAddrByRequestHeader(ip, req, "X-Forwarded-For");
         getIpAddrByRequestHeader(ip, req, "X-Real-IP");
         getIpAddrByRequestHeader(ip, req, "Proxy-Client-IP");
         getIpAddrByRequestHeader(ip, req, "WL-Proxy-Client-IP");
         getIpAddrByRequestHeader(ip, req, null);
         if(ip != null && ip.indexOf(",")!=-1){
        	 String[] ips = ip.split(",");
        	 if (ips.length > 0 && !ips[0].equalsIgnoreCase("unknown")) {
        		 ip = ips[0];
        	 }            	 
         }
        return ip;
    }
	
	/**
	 * 
	 * @Method: getIpAddrByRequestHeader  
	 * @Description: 根据Request的Header参数获取ip
	 * @param ip ip地址
	 * @param req HttpServletRequest
	 * @param header Header参数
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:25
	 */
	private static void getIpAddrByRequestHeader (String ip, HttpServletRequest req, String header) {
		if(StringUtils.isNotBlank(header)){
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getHeader(header);
	        }
		}else {
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getRemoteAddr();
			}
		}
	}
}
