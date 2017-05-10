package cn.yiyizuche.common.ou.menu.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.base.TreeNode;
import cn.yiyizuche.common.ou.menu.entity.Menu;
import cn.yiyizuche.common.ou.menu.entity.MenuVo;
import cn.yiyizuche.common.ou.menu.service.MenuService;
import cn.yiyizuche.common.ou.role.service.RoleService;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.controller 
 * @Class : MenuController.java 
 * @Description : 菜单控制层
 * @author : jiwenfang 
 * @CreateDate : 2017年3月7日 下午8:15:12 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@RequestMapping("/ou/menu")
@Controller
public class MenuController {
	
	@Autowired
	MenuService menuService;
	@Autowired
	RoleService roleService;
	
	/**
	 * 
	 * @Method: addMenu  
	 * @Description: 添加菜单
	 * @param menu 菜单实体
	 * @return ResultMsg 提示信息
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午9:54:46
	 */
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg addMenu(Menu menu){
		return menuService.addMenu(menu);
	}
	
	/**
	 * 
	 * @Method: showMenuListPage  
	 * @Description: 进入菜单页面
	 * @return ModelAndView  
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月22日 下午12:49:42
	 */
	@RequestMapping(value = "/showMenuListPage", method = RequestMethod.GET)
	public ModelAndView showMenuListPage(){
		return new ModelAndView("/page/ou/menu/menu_list");
	}
	
	/**
	 * 
	 * @Method: updateMenu  
	 * @Description: 修改菜单
	 * @param menu 菜单实体
	 * @return ResultMsg 提示信息 
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午9:55:25
	 */
	@RequestMapping(value = "/updateMenu",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg updateMenu(Menu menu, HttpServletRequest req){
		HttpSession session = req.getSession();
		UserVo user = (UserVo) session.getAttribute(SysConstants.USER_INFO);
		if(user != null){
			menu.setUpdateUser(user.getUserId());
			menu.setUpdateTime(new Date());
		}
		return menuService.updateMenu(menu);
	}
	/**
	 * 
	 * @Method: deleteMenuById  
	 * @Description: 根据菜单id删除菜单
	 * @param id 菜单id
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午9:57:14
	 */
	@RequestMapping(value = "/deleteMenu",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteMenuById(int id){
		return menuService.deleteMenubyId(id);
	}
	
	/**
	 * 
	 * @Method: selectMenuById  
	 * @Description: 根据菜单id查询菜单实体
	 * @param id 菜单ID
	 * @return Menu 返回菜单实体
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月8日 上午10:03:59
	 */
	@RequestMapping(value = "/selectMenuById",method = RequestMethod.POST)
	@ResponseBody
	public MenuVo selectMenuById(int id) throws IllegalAccessException, InvocationTargetException{
		Menu menu = menuService.selectMenuById(id);
		MenuVo menuVo = new MenuVo(); 
		BeanUtils.copyProperties(menuVo, menu);
		if(menu.getpId() == 0){
			menuVo.setParentName("菜单");
		}else{
			Menu parentMenu = menuService.selectMenuById(menu.getpId());
			menuVo.setParentName(parentMenu.getName());
		}
		
		return menuVo;
	}
	
	/**
	 * 
	 * @Method: updateMenuStatus  
	 * @Description: 更新菜单状态（启用、禁用）
	 * @param menuId 菜单id
	 * @param status 菜单状态
	 * @return ResultMsg 提示信息
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午7:59:55
	 */
	@RequestMapping(value = "/updateMenuStatus",method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg updateMenuStatus(int menuId, int status){
		return menuService.updateMenuStatus(menuId, status);
	}
	
	/**
	 * 
	 * @Method: selectMenuByPage  
	 * @Description: 分页查询菜单
	 * @param pId 父菜单id
	 * @param status 菜单状态
	 * @param name 菜单名称
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午8:17:04
	 */
	@RequestMapping(value = "/selectMenuByPage",method = RequestMethod.POST)
	@ResponseBody
	public void selectMenuByPage(Page<Menu> page,Map<String, Object> params){
		menuService.selectMenuByPage(page, params);
	}
	
	/**
	 * 
	 * @Method: findMenuTreeList  
	 * @Description: 菜单管理页面根据父id查询菜单树
	 * @param pid 父id
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午8:35:32
	 */
	@RequestMapping(value = "/findMenuTreeList",method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> findMenuTreeList(int pid){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pId", pid);
		return menuService.findMenuTreeList(params);
	}
	
	@RequestMapping(value = "/findMenuTreeList2",method = RequestMethod.POST)
	@ResponseBody
	public void findMenuTreeList2(int pid, HttpServletResponse response) throws IOException{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pId", pid);
		List<TreeNode> treeNodes = menuService.findMenuTreeList(params);
		
		JSONArray json = new JSONArray();
        for(TreeNode a : treeNodes){
            JSONObject jo = new JSONObject();
            jo.put("id", a.getId());
            jo.put("parent", a.getpId());
            jo.put("text", a.getName());
            json.add(jo);
        }
        String jsonString = json.toJSONString();
		
		response.setCharacterEncoding("utf-8");  
        PrintWriter pw = response.getWriter();  
      
        //需要返回的数据有总记录数和行数据  
        String jsonResultString = "[" + jsonString + "]";  
        pw.print(jsonResultString);
		
	}
	

	@RequestMapping(value = "/findMenuTreeList3",method = RequestMethod.POST)
	@ResponseBody
	public List<JSONObject> findMenuTreeList3(int pid, HttpServletResponse response) throws IOException{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pId", pid);
		List<TreeNode> treeNodes = menuService.findMenuTreeList(params);
		
		List<JSONObject> jsonObjests = new ArrayList<JSONObject>();
        for(TreeNode a : treeNodes){
            JSONObject jo = new JSONObject();
            jo.put("id", a.getId());
            jo.put("parent", a.getpId());
            jo.put("text", a.getName());
            jsonObjests.add(jo);
        }  
        return jsonObjests;
	}
	
	
	

	/**
	 * 
	 * @Method: findMenuTreeList  
	 * @Description:查询角色的树形菜单
	 * @param pid
	 * @param roleId
	 * @return List<TreeNode> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月27日 下午4:32:10
	 */
	@ResponseBody
	@RequestMapping(value = "/findCheckMenuTreeList",method = RequestMethod.POST)
	public List<TreeNode> findMenuTreeList(int pid, int roleId){
		return menuService.findMenuTreeList(pid, roleId);
	}
	
	
	
	/**
	 * 
	 * @Method: findMenuListByPid  
	 * @Description: (根据菜单父ID查询下级的所有菜单列表，适用于菜单页面列表展示)
	 * @param pid 菜单父ID
	 * @return Map<String, Object> (菜单的分页集合) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月22日 下午5:40:38
	 */
	@ResponseBody
	@RequestMapping(value = "/findMenuListByPid", method = RequestMethod.POST)
	public Map<String, Object> findMenuPageByPid(int pid, int current, int rowCount){
		Page<Menu> page = new Page<>(current, rowCount);
		Page<Menu> pageResult = menuService.findMenuPageByPid(pid, page);
		Map<String, Object> gridMap = pageResult.getGridMap();
		return gridMap;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/setRoleMenu", method = RequestMethod.POST)
	public ResultMsg setRoleMenu(int roleId, String menuIds){
		
		String[] menuArrayStr = {};
		if (StringUtils.isNotEmpty(menuIds)) {
			menuArrayStr = menuIds.split(",");
		}
		int[] menuIdsArray = new int[menuArrayStr.length];
		
		for(int i=0;i<menuArrayStr.length;i++){
			menuIdsArray[i] = Integer.parseInt(menuArrayStr[i]);
		}
		return roleService.saveAuthority(roleId, menuIdsArray);
		
	}
	
}
