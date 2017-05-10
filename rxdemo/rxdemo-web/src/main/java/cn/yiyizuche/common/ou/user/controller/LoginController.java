package cn.yiyizuche.common.ou.user.controller;

import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.employee.entity.EmployeeVo;
import cn.yiyizuche.common.ou.employee.service.EmployeeService;
import cn.yiyizuche.common.ou.menu.entity.Menu;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.ou.user.service.LoginService;
import cn.yiyizuche.common.ou.user.service.UserService;
import cn.yiyizuche.common.ou.util.OuConstants;
import cn.yiyizuche.common.sys.util.MD5Util;
import cn.yiyizuche.common.sys.util.SysConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class LoginController {
	

	@Autowired
    private LoginService loginService;
	@Autowired
	private UserService userService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * @Method: mainPage
	 * @Description: 进入main页面
	 * @param
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017/3/21 12:25
	 */
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public ModelAndView mainPage() {
		return new ModelAndView("main");
	}

	/**
	 * @Method: welcomePage
	 * @Description: 进入welcome页面
	 * @param
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017/3/24 9:45
	 */
	@RequestMapping(value = "/welcomePage", method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		return new ModelAndView("welcome");
	}

    /**
     * @Method: homePage
     * @Description: 跳转到home页
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/21 12:18
     */
	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}
	

	@RequestMapping(value = "/changePwdPage", method = RequestMethod.GET)
	public ModelAndView changePwdPage() {
		return new ModelAndView("changePwd");
	}


	/**
	 * @Method: loginPage
	 * @Description: 跳转到登录页
	 * @param
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017/3/21 12:19
	 */
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMsg loginAction(@RequestParam(value = "userName", defaultValue = "0") String userName, @RequestParam(value = "pwd", defaultValue = "0") String pwd, HttpServletRequest request) {
		
		_log.debug("login url");
		//MD5加密
		String password = (StringUtils.isNotBlank(pwd)) ? MD5Util.getMD5Digest(pwd) : pwd;
		Map<String, Object> result = this.loginService.selectByUserNameAndPwd(userName, password);
		//获取userVo实体
		UserVo userVo = (UserVo) result.get("userVo");
		ResultMsg resultMsg = (ResultMsg) result.get("msg");
		//判断用户是否具有员工信息
		if(userVo!=null){
			resultMsg = checkEmployeeInfo(userVo.getUserId(), resultMsg);
		}
		//判断userVo对象是否为空,不为空,则将实体存于session中
		if(userVo != null && resultMsg.getState()){
			HttpSession session = request.getSession(); 
			session.setAttribute(SysConstants.USER_INFO, userVo);
		}
		_log.debug("user:{} login {}", userName, resultMsg.getState());
		//跳转到登录首页
		return resultMsg;
	}

	/**
	 * @Method: checkEmployeeInfo
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param userId 用户id
	 * @param resultMsg 返回信息结果集
	 * @return cn.yiyizuche.common.base.ResultMsg (返回类型描述)
	 * @throws
	 * @author : lipeng
	 * @CreateDate : 2017/3/27 14:16
	 */
	private ResultMsg checkEmployeeInfo(int userId, ResultMsg resultMsg){
		if(userId != SysConstants.ADMINID){
			EmployeeVo employeeVo = employeeService.selectEmployeeVoByUserId(userId);
			if(employeeVo==null){//用户不存在员工信息
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.USER_EMPLOYEE_ERROR);
			}
		}
		return resultMsg;
	}

	/**
	 * 
	 * @Method: loadMenu  
	 * @Description: 根据用户id查询菜单列表
	 * @return List<Menu> 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月20日 下午9:09:33
	 */
	@ResponseBody
    @RequestMapping(value = "/loadMenu", method = RequestMethod.POST)
	public List<Menu> loadMenu(int userId){
		return loginService.updateMenuList(userId);
	}
	
	@ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResultMsg logoutAction(HttpServletRequest request) {
		_log.debug("logout url");
		HttpSession session = request.getSession(); 
		session.removeAttribute(SysConstants.USER_INFO);
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setState(SysConstants.SUCCESS);
		resultMsg.setMessage("退出登录成功");
//		mView = new ModelAndView(new RedirectView("login.do"));
		_log.debug("success logout");
		return resultMsg;
		
	}

	/**
	 *
	 * @Method: changePwdAction
	 * @Description:修改密码
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return ResultMsg 返回信息
	 * @throws
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月25日 下午12:03:59
	 */
	@ResponseBody
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public ResultMsg changePwdAction(@RequestParam(value = "oldPwd", defaultValue = "0") String oldPwd, @RequestParam(value = "newPwd", defaultValue = "0") String newPwd, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo)session.getAttribute(SysConstants.USER_INFO);
		int userId = userVo.getUserId();
		ResultMsg resultMsg = this.userService.updateChangePwdByUserId(userId, oldPwd, newPwd);
		_log.debug("userId：{} change pwd result msg:{}", userVo.getUserId(), resultMsg.toString());
		//判断userVo对象是否为空,不为空,则将实体存于session中
		return resultMsg;
	}
	
	private static final Logger _log = LoggerFactory.getLogger(LoginController.class);


}
