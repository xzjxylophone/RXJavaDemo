package cn.yiyizuche.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yiyizuche.common.ou.user.entity.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.sys.util.DateUtil;
import cn.yiyizuche.common.sys.util.RequestUtil;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.filter
 * @FileName : LoginFilter.java
 * @Description : 登录验证过滤器
 * @author : lipeng
 * @CreateDate : 2017/3/16 11:52
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class LoginFilter implements Filter {

	/**
	 * 要排除的请求路径(/开头)列表
	 */
	private Set<String> exRoutes = new HashSet<String>();

	/**
	 * Filter接口方法
	 */
	@Override
	public void destroy() {
	}

	/**
	 * 进行url过滤，判断用户是否登录等操作
	 * (非 Javadoc)
	 *
	 * @param req
	 * @param res
	 * @param chain
	 * @see LoginFilter#doFilter(ServletRequest,ServletResponse,FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);
		UserVo user = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		String servletPath = request.getServletPath();
		if (!checkExRoute(servletPath)) {
			if (user == null) {// session过期处理
				String clientIp = RequestUtil.getIpAddr(request);
				String userClient = request.getHeader("user-agent");
				_log.warn(DateUtil.thisDateTime() + "----用户未登录或会话已经过期，请重新登录！");
				_log.warn("客户端ip:{}", clientIp);
				_log.warn("客户端浏览器类型:{}" , userClient);
				toLoginPage(request, response);
				return;
			} else {// 如果当前SESSION有登录用户信息
				_log.info("当前登录的用户:{}" , user.getUserName());
				// 如果当前页面用户和SESSION用户不一致
				String _userId = req.getParameter(SysConstants.USER_ID_INPAGE);
				if (_userId != null && !_userId.equals(user.getUserId())) {// 如果当前页面用户和SESSION用户不一致
					toLoginPage(request, response);
					return;
				}
//				ThreadParameter.set(Constants.THREAD_PARAMETER_USERVO, userVo);
			}
		}
		// 登录有效放行
		chain.doFilter(request, response);
	}

	/**
	 * 
	 * @Method: toLoginPage  
	 * @Description: 跳转到登录页
	 * @param request  请求对象
	 * @param response  响应对象
	 * @throws IOException
	 * @author : jiwenfang
	 * @CreateDate : 2016年1月26日 下午5:31:02
	 */
	private void toLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String contextPath = request.getContextPath();
//		response.sendRedirect(contextPath + "/beforeLogin.do");
		String contextPath = request.getContextPath();
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest")) {
			//异步请求时session过期,通过common.js中的处理跳转到登录页
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.print("{sessionState:0}");
			printWriter.flush();
			printWriter.close();
		} else {//同步请求时session过期,通过refreshSession.jsp跳转到登录页
//			response.sendRedirect(contextPath + "/loginPage.do");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script>");
			out.println("window.open ('"+request.getContextPath()+"/loginPage.do','_top')");
			out.println("</script>");
			out.println("</html>");
		}
	}


	/**
	 * Filter接口方法
	 * (非 Javadoc)
	 *
	 * @param conf
	 * @see LoginFilter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig conf) throws ServletException {
		// 要排除的请求路径配置在web.xml的filter初始化参数的名称集合
		Enumeration<String> exNames;
		exNames = conf.getInitParameterNames();
		if (null != exNames) {
			// 要排除的请求路径在配置文件中的名称
			String exName;
			// 要排除的请求路径
			String exRoute;
			while (exNames.hasMoreElements()) {
				exName = (String) exNames.nextElement();
				exRoute = conf.getInitParameter(exName);
				exRoutes.add(exRoute);
			}
		}
	}
	/**
	 * 
	 * @Method: checkExRoute  
	 * @Description: 验证当前请求路径是否在要排除的范围内
	 * @param reqPath 请求路径
	 * @return boolean  当前请求路径状态
	 * @author : jiwenfang
	 * @CreateDate : 2016年1月25日 下午2:07:21
	 */
	private boolean checkExRoute(String reqPath) {
		boolean flag = false;
		for (String exPath : exRoutes) {
			if (reqPath.contains(exPath)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

    private static final Logger _log = LoggerFactory.getLogger(LoginFilter.class);
}
