package cn.yiyizuche.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yiyizuche.common.ou.menu.entity.Menu;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.sys.util.SysConstants;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.filter 
 * @Class : AuthFilter.java 
 * @Description : 权限过滤器
 * @author :jiwenfang
 * @CreateDate : 2017年3月17日 下午3:35:17 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Component
public class AuthFilter implements Filter {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * (非 Javadoc) 
	 *   
	 *   对接收到的请求进行权限过滤
	 * @param req 请求对象
	 * @param resp 响应对象
	 * @param filterChain 过滤器
	 * @throws IOException
	 * @throws ServletException  
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 请求URI
		String requestURI = request.getServletPath();
		// 请求类型
		String requestType = request.getParameter("requestType");
		// 请求结果
		int result = SysConstants.FLAG_YES;
		// 获取Session中loginUser的用户id
		UserVo loginUser = (UserVo) getSession(request).getAttribute(SysConstants.USER_INFO);
		// 如果是菜单类型
		if(requestType.equals(SysConstants.REQUESTTYPE)){
			int userId = loginUser.getUserId();
			// 如果不是管理员，则进行权限过滤
			if (SysConstants.ADMINID != userId) {
				// 从缓存session中获取当前登录用户的菜单List
				List<Menu> menuList = (List<Menu>) getSession(request).getAttribute(SysConstants.MENU_LIST + userId);
				// 通过request获取的path和从session中获取的菜单List中的Url进行对比
				List<String> urlList = new ArrayList<String>();
				for (int i = 0; i < menuList.size(); i++) {
					Menu menu = menuList.get(i);
					String menuUrl = menu.getUrl();
					urlList.add(menuUrl);
				}
				// 去掉第一个“/”
				String path = requestURI.substring(1);
				// 如果不匹配，设置result = Constants.FLAG_NO;
				if (!urlList.contains(path)) {
					result = SysConstants.FLAG_NO;
				}
			}
		}
			
		if (result == SysConstants.FLAG_YES) {
			filterChain.doFilter(request, response);
		} else {
			// 给予相应提示
			String msg = String.valueOf(SysConstants.FLAG_NO);
			response.getWriter().write(msg);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * @Method: getSession  
	 * @Description: 获取Session
	 * @param request
	 * @return HttpSession (返回类型描述) 
	 * @throws  
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 下午3:36:06
	 */
	private HttpSession getSession(HttpServletRequest request) {
		return request.getSession(true);
	}
}
