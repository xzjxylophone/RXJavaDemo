package cn.yiyizuche.common.ou.menu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.base.TreeNode;
import cn.yiyizuche.common.ou.employee.entity.Employee;
import cn.yiyizuche.common.ou.menu.dao.extend.MenuExtendDao;
import cn.yiyizuche.common.ou.menu.entity.Menu;
import cn.yiyizuche.common.ou.menu.service.MenuService;
import cn.yiyizuche.common.ou.role.dao.extend.RoleMenuExtendDao;
import cn.yiyizuche.common.ou.role.entity.RoleMenuKey;
import cn.yiyizuche.common.ou.util.OuConstants;
import cn.yiyizuche.common.sys.util.SysConstants;
/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.menu.service 
 * @Class : MenuService.java 
 * @Description : 菜单服务层实现类
 * @author : jiwenfang 
 * @CreateDate : 2017年3月7日 下午4:37:40 
 * @version : V1.0.0 
 * @Copyright: 2017 htht Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuExtendDao menuDao;
	@Autowired
	private RoleMenuExtendDao roleMenuExtendDao;
	
	/**
	 * 
	 * @Method: addMenu  
	 * @Description: 添加菜单
	 * @param menu 菜单实体
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午4:33:08
	 */
	public ResultMsg addMenu(Menu menu){
		ResultMsg result = new ResultMsg();
		try{
			//判断是否有同名菜单
			boolean existFlag = checkMenu(menu,OuConstants.OPERATION_ADD);
			if(existFlag){
				result.setState(SysConstants.FAIL);
				result.setMessage(OuConstants.MENU_REPEAT_MSG);
				return result;
			}
			
			//判断是否有同code带单
			List<Menu> list = menuDao.countMenuByMenuCode(menu.getCode());
			if(CollectionUtils.isNotEmpty(list)){
				result.setState(SysConstants.FAIL);
				result.setMessage(OuConstants.MENU_REPEAT_CODE_MSG);
				return result;
			}
			
			//判断是否二级以内菜单
			if(menu.getpId()!=0){
				Menu parentMenu = menuDao.selectByPrimaryKey(menu.getpId());
				if(parentMenu.getpId()!=0){
					result.setState(SysConstants.FAIL);
					result.setMessage(OuConstants.MENU_LEVEL_LIMIT_MSG);
					return result;
				}
			}
			
			//设置菜单相关信息
			menu.setCreateTime(new Date());
//			menu.setCreateUser();
			//保存添加的菜单
			menuDao.insert(menu);
			result.setState(SysConstants.SUCCESS);
			result.setMessage(SysConstants.SAVE_SUCCESS_MSG);
			
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return result;
	}
	
	/**
	 * 
	 * @Method: updateMenu  
	 * @Description: 修改菜单
	 * @param menu 菜单实体
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午4:33:49
	 */
	public ResultMsg updateMenu(Menu menu){
		ResultMsg result = new ResultMsg();
		try{
			
			//判断是否有同名菜单
			boolean existFlag = checkMenu(menu,OuConstants.OPERATION_UPDATE);
			if(existFlag){
				result.setState(SysConstants.FAIL);
				result.setMessage(OuConstants.MENU_REPEAT_MSG);
				return result;
			}
			
			//判断是否有同code带单
			List<Menu> list = menuDao.countMenuByMenuCode(menu.getCode());
			if(CollectionUtils.isNotEmpty(list)){
				if(list.get(0).getId() != menu.getId()){
					result.setState(SysConstants.FAIL);
					result.setMessage(OuConstants.MENU_REPEAT_CODE_MSG);
					return result;
				}
			}
		
			//设置菜单相关信息
			menu.setUpdateTime(new Date());
			//menu.setUpdateUser(updateUser);
			
			

			Menu instance = this.menuDao.selectByPrimaryKey(menu.getId());
			instance.setpId(menu.getpId());
			instance.setName(menu.getName());
			instance.setCode(menu.getCode());
			instance.setSort(menu.getSort());
			instance.setUrl(menu.getUrl());
			instance.setStatus(menu.getStatus());
			instance.setRemark(menu.getRemark());
			instance.setUpdateUser(menu.getUpdateUser());
			instance.setUpdateTime(menu.getUpdateTime());
			
			
			//保存修改后的菜单信息
			menuDao.updateByPrimaryKey(instance);
			//设置保存成功提示信息
			result.setState(SysConstants.SUCCESS);
			result.setMessage(SysConstants.SAVE_SUCCESS_MSG);
			
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置保存失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.SAVE_FAIL_MSG);
		}
		return result;
	}
	
	/**
	 * 
	 * @Method: selectMenuById  
	 * @Description: 根据菜单Id查询菜单实体
	 * @param id 菜单id
	 * @return Menu 菜单实体
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午4:34:26
	 */
	public Menu selectMenuById(int id){
		return menuDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	 * @Method: deleteMenubyId  
	 * @Description: 根据菜单id删除菜单实体
	 * @param id 菜单id
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午4:35:26
	 */
	public ResultMsg deleteMenubyId(int id){
		ResultMsg result = new ResultMsg();
		try{
			//1、根据菜单ID查询角色与菜单的关联关系
			List<RoleMenuKey> rmList = roleMenuExtendDao.selectRelationByMenuId(id);
			//判断删除的菜单是否已被使用
			if(rmList != null && rmList.size()>0){//已被使用，则不允许删除
				//设置不允许删除提示信息
				result.setState(SysConstants.FAIL);
				result.setMessage(OuConstants.MENU_FORBID_DELETE_MSG);
			}else{//未被使用，执行删除操作
				//1、根据菜单父id查询子菜单列表
				List<Menu> list = menuDao.selectMenuListByPid(id);
				//2、获取菜单id数组
				List<Integer> ids = new ArrayList<Integer>();
				for(Menu menu : list){
					ids.add(menu.getId());
				}
				//3、删除菜单及其子菜单
				menuDao.deleteByIds(ids);
				//设置删除成功提示信息
				result.setState(SysConstants.SUCCESS);
				result.setMessage(SysConstants.DELETE_SUCCESS_MSG);
			}
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置删除失败提示信息
			result.setState(SysConstants.FAIL);
			result.setMessage(SysConstants.DELETE_FAIL_MSG);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Method: checkMenu  
	 * @Description: 根据菜单名称查询同名菜单个数
	 * @param menu
	 * @return Boolean (返回类型描述) 
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午8:53:22
	 */
	private boolean checkMenu(Menu menu,int option){
		boolean exist = false;
		// 根据菜单名称查询菜单名称是否重复
		List<Menu> list = menuDao.countMenuByMenuName(menu.getName());
		if(list != null && list.size() > 0){
			//当前操作为添加菜单
			if(option == OuConstants.OPERATION_ADD){
				exist = true;
			}else if(option == OuConstants.OPERATION_UPDATE){//当前操作为修改菜单
				for(Menu m:list){
					if(m.getId() != menu.getId() && m.getName().equals(menu.getName())){
						exist = true;
					}
				}
			}
		}
		
		return exist;
	}

	/**
	 * 根据查询条件查询菜单树形
	 * (非 Javadoc) 
	 *   
	 * @param params 查询条件 （一般包括菜单父id及菜单状态）
	 * @return  
	 * @see cn.yiyizuche.common.ou.menu.service.MenuService#findMenuTreeList(Map<String,Object>)
	 */
	@Override
	public List<TreeNode> findMenuTreeList(Map<String,Object> params) {

		//根据菜单父id查询子菜单列表
		List<Menu> menuList = menuDao.findMenuChildListByParam(params);
		// 存放组装后的树节点
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		//循环菜单列表，组装树节点
		for (Menu menu : menuList) {
			TreeNode node = new TreeNode(menu.getId(), menu.getpId() ,menu.getName());
			node.setOpen(true);
			treeNodeList.add(node);
		}
		//返回菜单树
		return treeNodeList;
	}
	
	/**
	 * 查询角色的树形菜单(非 Javadoc) 
	 *   
	 * @param pid
	 * @param roleId
	 * @return  
	 * @see cn.yiyizuche.common.ou.menu.service.MenuService#findMenuTreeList(int, int)
	 */
	public List<TreeNode> findMenuTreeList(int pid, int roleId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pId", pid);
		params.put("status", SysConstants.ENABLE_FLAG);//设置菜单状态为启用状态
		List<TreeNode> treeNodes = this.findMenuTreeList(params);
		
		List<RoleMenuKey> roleMenuKeys = this.roleMenuExtendDao.selectRelationByRoleId(roleId);
		
		for (RoleMenuKey roleMenuKey : roleMenuKeys) {
			int menuId = roleMenuKey.getMenuId();
			for (TreeNode treeNode : treeNodes) {
				int tnMenuId = treeNode.getId();
				if (menuId == tnMenuId) {
					treeNode.setChecked(true);
					break;
				}
			}
		}
		
		return treeNodes;
	}
	
	/**
	 * 根据条件分页查询菜单数据
	 * (非 Javadoc) 
	 *   
	 * @param page 分页对象
	 * @param params
	 * @return  
	 * @see cn.yiyizuche.common.ou.menu.service.MenuService#selectMenuByPage(cn.yiyizuche.common.base.Page, java.util.Map)
	 */
	@Override
	public Page<Menu> selectMenuByPage(Page<Menu> page, Map<String, Object> params) {
		return menuDao.selectMenuByPage(page, params);
	}
	
	/**
	 * 修改菜单状态（启用、禁用）
	 * (非 Javadoc) 
	 *  
	 * @param menuId 菜单id
	 * @param status 菜单状态
	 * @return  
	 * @see cn.yiyizuche.common.ou.menu.service.MenuService#updateMenuStatus(int, int)
	 */
	@Override
	public ResultMsg updateMenuStatus(int menuId, int status) {
		ResultMsg result = new ResultMsg();
		try{
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			//组建参数
			paramsMap.put("pId", menuId);
			//查询菜单下的子菜单列表（如果启用或禁用了父菜单，则同时启用或禁用其下的所有子菜单）
			List<Menu> menuList = menuDao.findMenuChildListByParam(paramsMap);
			Menu curMenu = menuDao.selectByPrimaryKey(menuId);
			menuList.add(curMenu);
			//重新设置各菜单的状态，修改人，修改时间
			for(Menu menu:menuList){
				menu.setStatus(status);//设置菜单状态
				menu.setUpdateTime(new Date());//设置修改时间
				//menu.setUpdateUser(1);//设置修改人
			}
			//调用修改菜单状态的方法
			menuDao.updateMenuStatus(menuList);
			//设置启用、禁用成功提示信息
			setMenuSuccessMsg(status, result);
			
		}catch(Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			//设置启用、禁用失败提示信息
			setMenuFailMsg(status, result);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Method: setMenuSuccessMsg  
	 * @Description: 设置启用、禁用菜单成功提示信息
	 * @param status 菜单状态
	 * @param result
	 * @return ResultMsg 提示信息
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午7:52:23
	 */
	private ResultMsg setMenuSuccessMsg(int status, ResultMsg result){
		result.setState(SysConstants.SUCCESS);
		//根据菜单状态，返回不同的提示信息（启用、禁用）
		if(status == SysConstants.ENABLE_FLAG){//启用状态
			result.setMessage(SysConstants.ENABLE_SUCCESS_MSG);
		}else if(status == SysConstants.UNENABLE_FLAG){//禁用状态
			result.setMessage(SysConstants.UNENABLE_SUCCESS_MSG);
		}
		return result;
	}
	
	/**
	 * 
	 * @Method: setMenuFailMsg  
	 * @Description: 设置菜单启用、禁用失败提示信息
	 * @param status 菜单状态
	 * @param result
	 * @return ResultMsg 提示信息
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午7:53:07
	 */
	private ResultMsg setMenuFailMsg(int status, ResultMsg result){
		result.setState(SysConstants.FAIL);
		//根据菜单状态，返回不同的提示信息（启用、禁用）
		if(status == SysConstants.ENABLE_FLAG){//启用状态
			result.setMessage(SysConstants.ENABLE_FAIL_MSG);
		}else if(status == SysConstants.UNENABLE_FLAG){//禁用状态
			result.setMessage(SysConstants.UNENABLE_FAIL_MSG);
		}
		return result;
	}

	private static final Logger _log = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	/**
	 * (非 Javadoc) 
	 *   
	 * @param pid 菜单父ID
	 * @param page 分页对象
	 * @return  (菜单集合) 
	 * @see cn.yiyizuche.common.ou.menu.service.MenuService#findMenuPageByPid(int, cn.yiyizuche.common.base.Page)
	 */
	@Override
	public Page<Menu> findMenuPageByPid(int pid, Page<Menu> page) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pId", pid);
		params.put("orderBy", "sort, menu_id");
		//根据菜单父id查询子菜单列表
		Page<Menu> result = menuDao.findMenuPageByPid(params, page);
		return result;
	}

}
