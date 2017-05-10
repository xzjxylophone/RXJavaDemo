package cn.yiyizuche.common.ou.user.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.yiyizuche.common.base.BaseController;
import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.user.entity.UserInfoVo;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.ou.user.service.UserService;
import cn.yiyizuche.common.sys.util.DateUtil;
import cn.yiyizuche.common.sys.util.MD5Util;
import cn.yiyizuche.common.sys.util.RequestUtil;
import cn.yiyizuche.common.sys.util.SysConstants;


/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.user.controller 
 * @Class : UserController.java 
 * @Description : 用户控制层 
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年3月7日 下午8:24:28 
 * @version : V1.0.0 
 * @Copyright: 2017 htht Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Controller
@RequestMapping("/ou/user")
public class UserController extends BaseController {

	@Autowired
    private UserService userService;

	/**
	 * 
	 * @Method: test  
	 * @Description:Test
	 * @param request void (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月22日 下午5:37:34
	 */
	@ResponseBody
	@RequestMapping(value = "/testUser", method = RequestMethod.GET)
	public void test(HttpServletRequest request) {
		Map<String, String[]> map = (Map<String, String[]>)request.getParameterMap();
		String paramJson = JSON.toJSONString(map);
//		System.out.println(">>>>>>>>>>>>>>>>>>参数:"+paramJson);
		_log.debug("_____________________参数:{}"+paramJson);
	}

	/**
	 * 
	 * @Method: addUser  
	 * @Description: 添加用户
	 * @param user
	 * @return User 用户
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月7日 下午8:22:40
	 */
    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResultMsg addUser(User user, String roleIds, HttpServletRequest req) {
	    	_log.debug("roleIds:" + roleIds);
	    	String pwd = user.getPwd();
	    	String md5Pwd = MD5Util.getMD5Digest(pwd);
	    	user.setPwd(md5Pwd);
	    	HttpSession session = req.getSession();
		UserVo user2 = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		if(user2 != null){
			user.setCreateUser(user2.getUserId());
			user.setCreateTime(new Date());
		}
		return this.userService.addUser(user, roleIds);
	}
    
    /**
     * 
     * @Method: updateUserStatusByUserId  
     * @Description:
     * @param id
     * @param status
     * @return ResultMsg (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月23日 上午10:45:10
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserStatusByUserId", method = RequestMethod.POST)
	public ResultMsg updateUserStatusByUserId(int id, int status) {
		return this.userService.updateUserStatusByUserId(id, status);
	}


    /**
     * 
     * @Method: deleteByUserId  
     * @Description: 删除用户
     * @param userId
     * @return int 当是0删除失败，否则返回Id
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月7日 下午8:22:43
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUserByUserId", method = RequestMethod.POST)
    public ResultMsg deleteByUserId(@RequestParam(value = "userId", defaultValue = "0") Integer userId, HttpServletRequest req) {
		return this.userService.deleteByUserId(userId);
	}

    

    /**
     * 
     * @Method: updateUser  
     * @Description: 更新用户
     * @return User 用户 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月7日 下午8:22:47
     */
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResultMsg updateUser(User user, HttpServletRequest req) {
    	_log.debug("updateUser id:" + user.getId() + ",birthday:" + user.getBirthdate());
		UserVo userVo = (UserVo)RequestUtil.getSessionAttribute(req, SysConstants.USER_INFO);
		user.setUpdateUser(userVo.getUserId());
		user.setUpdateTime(new Date());
		return this.userService.updateUser(user);
	}
    
    /**
     * 
     * @Method: updateUserAndRoles  
     * @Description:更新用户和其相关的角色
     * @param user
     * @param roleIds
     * @param req
     * @return ResultMsg (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月28日 下午5:52:11
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserAndRoleIds", method = RequestMethod.POST)
    public ResultMsg updateUserAndRoles(User user, String roleIds, HttpServletRequest req) {
    		_log.debug("updateUser id:" + user.getId() + ",birthday:" + user.getBirthdate());
		UserVo userVo = (UserVo)RequestUtil.getSessionAttribute(req, SysConstants.USER_INFO);
		user.setUpdateUser(userVo.getUserId());
		user.setUpdateTime(new Date());
		return this.userService.updateUser(user, roleIds);
	}
    
    
    

    /**
     * 
     * @Method: updateResetPwdByUserId  
     * @Description:重置密码
     * @param id
     * @param pwd
     * @return ResultMsg (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月23日 下午12:20:03
     */
    @ResponseBody
    @RequestMapping(value = "/updateResetPwdByUserId", method = RequestMethod.POST)
    public ResultMsg updateResetPwdByUserId(int id, String pwd) {
		if(StringUtils.isNotBlank(pwd)){
			pwd = MD5Util.getMD5Digest(pwd);
		}
		return this.userService.updateResetPwdByUserId(id, pwd);
    }
	

    /**
     * 
     * @Method: selectUser  
     * @Description:通过userId得到用户
     * @param userId
     * @return User 用户
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月7日 下午8:22:49
     */
    @ResponseBody
    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    public UserInfoVo selectUser(@RequestParam(value = "userId", defaultValue = "0") Integer userId, HttpServletRequest req) throws Exception {
		User user = this.userService.selectByUserId(userId);
		UserInfoVo userInfoVo = new UserInfoVo(user);
		if (userInfoVo.getBirthdate() != null) {
			String birthdateString = DateUtil.date2Str(userInfoVo.getBirthdate(), DateUtil.DEFAULT_DATE_FORMAT);
			userInfoVo.setBirthdateString(birthdateString);
			_log.debug("birthdateString:" + birthdateString);
		}
		_log.debug("User birthdate:" + user.getBirthdate());
		_log.debug("UserInfoVo birthdate:" + userInfoVo.getBirthdate());
		return userInfoVo;
	}

    /**
     * 
     * @Method: selectUserList  
     * @Description: 查询所有用户
     * @return List<User> 用户列表 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月7日 下午8:22:53
     */
    @ResponseBody
    @RequestMapping(value = "/selectUserAllList", method = RequestMethod.POST)
	public List<User> selectUserList() {
		return this.userService.selectAll();
	}
    
    /**
     * 
     * @Method: selectUserListNotInEmployee  
     * @Description:查找没有关联员工的用户
     * @param id
     * @return List<User> (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月28日 下午5:53:22
     */
    @ResponseBody
    @RequestMapping(value = "/selectUserListNotInEmployee", method = RequestMethod.POST)
	public List<User> selectUserListNotInEmployee(int id) {
		return this.userService.selectUserListNotInEmployee(id);
	}
    
    /**
     * 
     * @Method: selectUserListByName  
     * @Description:模糊查询用户
     * @param userName
     * @param realName
     * @param page
     * @return Page<User> (返回类型描述) 
     * @throws  
     * @author : Rush.D.Xzj
     * @CreateDate : 2017年3月22日 下午5:37:43
     */
	@ResponseBody
	@RequestMapping(value = "/selectUserListByName", method = RequestMethod.POST)
	public Page<User> selectUserListByName(@RequestParam(value = "userName", defaultValue = "") String userName, String realName, Page<User> page) {
		Page<User> pageResult = this.userService.selectUserListByName(userName, realName, page);
		_log.info("current:" + page.getStartRow() + ",size:" + page.getPageSize());
	    return pageResult;
	}

	/**
	 * 
	 * @Method: searchUserByPage  
	 * @Description:模糊查询用户
	 * @param userName
	 * @param realName
	 * @param current
	 * @param rowCount
	 * @return Map<String,Object> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月22日 下午5:38:02
	 */
	@ResponseBody
	@RequestMapping(value = "/searchUserByPage", method = RequestMethod.POST)
	public Map<String, Object> searchUserByPage(String userName, String realName, int current, int rowCount) {
		Page<User> page = new Page<>(current, rowCount);
		Page<User> pageResult = this.userService.selectUserListByName(userName, realName, page);
		Map<String, Object> gridMap = pageResult.getGridMap();
		
		
		List<User> users = pageResult.getResult();
		
		JSONArray json = new JSONArray();
        for(User a : users){
            JSONObject jo = new JSONObject();
            jo.put("id", a.getId());
            jo.put("realName", a.getRealName());
            jo.put("userName", a.getUserName());
            json.add(jo);
        }
        String jsonString = json.toJSONString();
        _log.info("jsonString:" + jsonString);
		
		return gridMap;
	}
	
	
	/**
	 * 
	 * @Method: searchUserByPage2_UseService  
	 * @Description: 使用服务器模式
	 * 返回的json字符串,必须要有"rows"和"total"
	 * 1. 此处有可能出现参数无法传递问题(6楼答案): http://bbs.csdn.net/topics/391884959
	 * 2. 以前默认的是current(当前页码第几页,从1开始,)和rowCount(一页有多少个), 
	 *    这里的:offset(偏移量)和limit(等价于rowCount)
	 *    所以新旧的转换公式是:  current = offset/limit + 1;
	 * @param map
	 * @return List<User> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年5月2日 上午11:30:41
	 */
	@ResponseBody
	@RequestMapping(value = "/searchUserByPage2_UseServer", method = RequestMethod.POST)
	public void searchUserByPage2_UseServer(@RequestBody Map map, HttpServletResponse response) throws Exception {
		
		String userName = (String)map.get("userName");
		String realName = (String)map.get("realName");
		int limit = (Integer)map.get("limit");
		int offset = (Integer)map.get("offset");
		_log.info("userName:" + userName + 
				",realName:" + realName + 
				",limit:" + limit +
				",offset:" + offset);
		
		int current = offset / limit + 1;
		int rowCount = limit;
		Page<User> page = new Page<>(current,rowCount);
		Page<User> pageResult = this.userService.selectUserListByName(userName, realName, page);
		Map<String, Object> gridMap = pageResult.getGridMap();
		
		
		List<User> users = pageResult.getResult();
		
		JSONArray json = new JSONArray();
        for(User a : users){
            JSONObject jo = new JSONObject();
            jo.put("id", a.getId());
            jo.put("realName", a.getRealName());
            jo.put("userName", a.getUserName());
            jo.put("mobile", a.getMobile());
            jo.put("email", a.getEmail());
            jo.put("status", a.getStatus());
            json.add(jo);
        }
        String jsonString = json.toJSONString();
        _log.info("jsonString:" + jsonString);
		
        response.setCharacterEncoding("utf-8");  
        PrintWriter pw = response.getWriter();  
      //得到总记录数  
        long total = pageResult.getTotalItems();  
          
        //需要返回的数据有总记录数和行数据  
        String jsonResultString = "{\"total\":" + total + ",\"rows\":" + jsonString + "}";  
        pw.print(jsonResultString);  
		
	}
	
	
	/**
	 * 
	 * @Method: searchUserByPage2_UseClient  
	 * @Description:当前只能展示前10条数据,
	 * 返回的json字符串,必须要有"data"
	 * @param map
	 * @param response
	 * @throws Exception void (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年5月2日 下午1:56:00
	 */
	@ResponseBody
	@RequestMapping(value = "/searchUserByPage2_UseClient", method = RequestMethod.POST)
	public void searchUserByPage2_UseClient(@RequestBody Map map, HttpServletResponse response) throws Exception {
		
		String userName = (String)map.get("userName");
		String realName = (String)map.get("realName");
		int limit = (Integer)map.get("limit");
		int offset = (Integer)map.get("offset");
		_log.info("userName:" + userName + 
				",realName:" + realName + 
				",limit:" + limit +
				",offset:" + offset);
		
		int current = offset / limit + 1;
		int rowCount = limit;

		Page<User> page = new Page<>(current,rowCount);
		Page<User> pageResult = this.userService.selectUserListByName(userName, realName, page);
		Map<String, Object> gridMap = pageResult.getGridMap();
		
		
		List<User> users = pageResult.getResult();
		
		JSONArray json = new JSONArray();
        for(User a : users){
            JSONObject jo = new JSONObject();
            jo.put("id", a.getId());
            jo.put("realName", a.getRealName());
            jo.put("userName", a.getUserName());
            jo.put("mobile", a.getMobile());
            jo.put("email", a.getEmail());
            jo.put("status", a.getStatus());
            json.add(jo);
        }
        String jsonString = json.toJSONString();
        _log.info("jsonString:" + jsonString);
		
        response.setCharacterEncoding("utf-8");  
        PrintWriter pw = response.getWriter();  
      //得到总记录数  
        long total = pageResult.getTotalItems();  
          
        //需要返回的数据有总记录数和行数据  
        String jsonResultString = "{\"total\":" + total + ",\"data\":" + jsonString + "}";  
        pw.print(jsonResultString);  
		
	}
    

	/**
	 * 
	 * @Method: showUserListPage  
	 * @Description:返回user_list.jsp
	 * @return ModelAndView (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月22日 下午5:38:20
	 */
	@RequestMapping(value = "/showUserListPage", method = RequestMethod.GET)
	public ModelAndView showUserListPage(){
		ModelAndView view = new ModelAndView("/page/ou/user/user_list");
		return view;
	}
	
	@RequestMapping(value = "/showUserListPage2", method = RequestMethod.GET)
	public ModelAndView showUserListPage2(){
		ModelAndView view = new ModelAndView("/page/ou/user/user_list2");
		return view;
	}
    
	private static final Logger _log = LoggerFactory.getLogger(UserController.class);
}
