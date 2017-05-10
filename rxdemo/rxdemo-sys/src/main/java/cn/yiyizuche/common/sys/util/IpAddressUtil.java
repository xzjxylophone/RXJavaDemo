package cn.yiyizuche.common.sys.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.sys.util
 * @FileName : IpAddressUtil.java
 * @Description : 获取登录IP地址
 * @author : lipeng
 * @CreateDate : 2017/3/16 16:28
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class IpAddressUtil {

	/**
	 * @Method: getIpAddress
	 * @Description: 获取登录IP地址
	 * @param request
	 * @return java.lang.String
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017/3/16 16:28
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;
                try {  
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();  
                }  
                ip= inet.getHostAddress();  
            }  

		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15  
            if(ip.indexOf(",")>0){  
            	ip = ip.substring(0,ip.indexOf(","));  
            }  
        }  

		return ip;
	}

}
