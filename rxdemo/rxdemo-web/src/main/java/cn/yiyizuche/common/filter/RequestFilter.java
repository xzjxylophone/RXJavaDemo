package cn.yiyizuche.common.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.yiyizuche.common.sys.util.IpAddressUtil;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.filter
 * @FileName : RequestFilter.java
 * @Description : Request请求过滤器
 * @author : lipeng
 * @CreateDate : 2017/3/16 14:19
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class RequestFilter implements Filter {
	//默认编码
	String ecoding;
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	ecoding = "UTF-8";
    }
    /**
     * 过滤器处理请求
     * 记录请求参数日志
     * 禁止访问jsp
     * (非 Javadoc)
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @see RequestFilter#doFilter(ServletRequest,ServletResponse,FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	servletRequest.setCharacterEncoding(ecoding);
    	servletResponse.setCharacterEncoding(ecoding);
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        //返回发出请求的IP地址
        //String ip = request.getRemoteAddr();
        String ipStr = IpAddressUtil.getIpAddress(request);
        // 请求URI
        String uri = request.getServletPath();
        if(StringUtils.isBlank(uri)){
            uri = request.getPathInfo();
        }
        // 禁止直接访问jsp
        if(uri !=null && uri.contains(".jsp")){
            _log.info("禁止访问jsp，IP:{},请求url:{}", ipStr, uri);
            return;
        }
        /*
         * 获取所有请求参数，封装到Map中
         */
        Map<String, String[]> map = (Map<String, String[]>)request.getParameterMap();
        String paramJson = JSON.toJSONString(map);
        _log.info("IP:{},请求url:{},参数:{}", ipStr, uri, paramJson);
        //这一句代码是放行的意思，如果没有这一句，就是不放行，就无法进入下一步，通常不通过的时候，return or TODO xxxxx，通过就一定要放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private static final Logger _log = LoggerFactory.getLogger(RequestFilter.class);
}
