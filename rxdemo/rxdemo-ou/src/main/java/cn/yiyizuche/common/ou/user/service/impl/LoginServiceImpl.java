package cn.yiyizuche.common.ou.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.menu.dao.extend.MenuExtendDao;
import cn.yiyizuche.common.ou.menu.entity.Menu;
import cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao;
import cn.yiyizuche.common.ou.role.entity.RoleMenuKey;
import cn.yiyizuche.common.ou.user.dao.extend.UserExtendDao;
import cn.yiyizuche.common.ou.user.dao.extend.UserRoleExtendDao;
import cn.yiyizuche.common.ou.user.entity.User;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.ou.user.service.LoginService;
import cn.yiyizuche.common.ou.util.OuConstants;
import cn.yiyizuche.common.sys.util.SysConstants;

@Service
public class LoginServiceImpl implements LoginService {
	//dao层注入
	@Autowired
	private UserExtendDao userExtendDao;
	@Autowired
	private UserRoleExtendDao userRoleExtendDao;
	@Autowired
	private RoleMenuExtendDao roleMenuExtendDao;
	@Autowired
	private MenuExtendDao menuExtendDao;

	/**
	 * 根据用户id查询菜单列表
	 * (非 Javadoc)
	 *
	 * @param userName 用户名
	 * @param pwd 密码
	 * @return
	 * @see cn.yiyizuche.common.ou.user.service.LoginService#selectByUserNameAndPwd(String, String)
	 */
	@Override
	public Map<String,Object> selectByUserNameAndPwd(String userName, String pwd) {
		Map<String,Object> result  = new HashedMap<String,Object>();
		ResultMsg resultMsg = new ResultMsg();
		List<User> users = this.userExtendDao.selectByUserNameAndPwd(userName, pwd);
		if (users == null || users.size() != 1) {
			resultMsg.setState(SysConstants.FAIL);
			resultMsg.setMessage(OuConstants.USER_USERNAME_PWD_ERROR);
		} else {
			User user = users.get(0);
			//判断是否启用
			if(user.getStatus()==SysConstants.ENABLE_FLAG){//启用
				//创建用户vo对象
				UserVo userVo = new UserVo();
				//设置用户vo属性
				userVo.setUserId(user.getId());
				userVo.setUserName(user.getUserName());
				userVo.setUserPwd(user.getPwd());
				userVo.setUserRealName(user.getRealName());
				userVo.setBirthdate(user.getBirthdate());
				result.put("userVo", userVo);
				resultMsg.setState(SysConstants.SUCCESS);
			}else{//禁用
				resultMsg.setState(SysConstants.FAIL);
				resultMsg.setMessage(OuConstants.USER_STATUS_DISABLE);
			}
		}
		result.put("msg", resultMsg);
		return result;
	}

	/**
	 * 根据用户id查询菜单列表
	 * (非 Javadoc) 
	 *   
	 * @param userId 用户id
	 * @return  
	 * @see cn.yiyizuche.common.ou.user.service.LoginService#selectMenuList(int)
	 */
	@Override
	public List<Menu> updateMenuList(int userId) {
		List<Menu> menuList = menuExtendDao.updateMenuList(SysConstants.MENU_PID);
		//根据用户Id判断当前用户是否是超级管理员
		if(userId != SysConstants.ADMINID){//不是管理员,则进行权限验证
			List<Menu> menus = getMenuListByUserId(userId);
			menuList = createMenuTree(menuList, menus);
		}
		return menuList;
	}

	/**
	 * 
	 * @Method: getMenuListByUserId  
	 * @Description: 根据用户id查询菜单列表集合
	 * @param userId 用户ID
	 * @return List<Menu> 菜单集合
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月21日 上午10:09:37
	 */
	private List<Menu> getMenuListByUserId(int userId){
		List<Menu> menuList = null;
		//1.根据用户Id查询用户与角色的关联关系
		List<UserRoleKey> userRole = userRoleExtendDao.selectUserRoleListByUserId(userId);
		//2.判断用户与角色否关联关系是否为空,如果不为空,则进一步根据角色id数组查询角色与菜单的关联关系
		if(userRole != null && userRole.size()> 0){
			//创建角色id数组
			int[] roleIds = new int[userRole.size()];
			//向角色id数组中添加数据
			for(int i=0; i<userRole.size(); i++){
				roleIds[i] = userRole.get(i).getRoleId();
			}
			//3.根据角色id数组查询角色与菜单的关联关系
			List<RoleMenuKey>  roleMenu = roleMenuExtendDao.selectRelationByRoleIds(roleIds);	
			//4.判断角色与菜单的关联关系是否为空,如果不为空,则进一步根据菜单id数组查询菜单列表
				if(roleMenu != null && roleMenu.size() >0){
					//创建菜单id数组
					int[] menuIds  = new int[roleMenu.size()];
					//向菜单id数组中添加数据
					for(int i=0; i<menuIds.length; i++){
						menuIds[i] = roleMenu.get(i).getMenuId();
					}
					//5.根据菜单id数组查询菜单列表
					menuList = menuExtendDao.selectMenuListByMenuIds(menuIds);
				}
		}
			return menuList;
	}
	
	/**
	 * 
	 * @Method: createMenuTree  
	 * @Description: 创建菜单树
	 * @param allMenuList
	 * @param menuList
	 * @return List<Menu> (返回类型描述) 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月21日 下午10:01:17
	 */
	private List<Menu> createMenuTree(List<Menu> allMenuList, List<Menu> menuList){
		List<Menu> menuTree = new ArrayList<Menu>();
		if(allMenuList != null && menuList != null){
			for(int i=0; i<allMenuList.size(); i++){
				for(int j=0; j<menuList.size(); j++){
					if(allMenuList.get(i).getId() == menuList.get(j).getId()){
						menuTree.add(allMenuList.get(i));
						break;
					}
				}
			}
		}
		return menuTree;
	}
}
