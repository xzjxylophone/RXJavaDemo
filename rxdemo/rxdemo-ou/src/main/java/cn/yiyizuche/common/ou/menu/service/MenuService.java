package cn.yiyizuche.common.ou.menu.service;

import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.base.TreeNode;
import cn.yiyizuche.common.ou.menu.entity.Menu;
/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.menu.service 
 * @Class : MenuService.java 
 * @Description : 菜单服务层接口
 * @author : jiwenfang 
 * @CreateDate : 2017年3月7日 下午4:37:40 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
public interface MenuService {
	/**
	 * 
	 * @Method: addMenu  
	 * @Description: 添加菜单
	 * @param menu 菜单实体
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午4:33:08
	 */
	public ResultMsg addMenu(Menu menu);
	
	/**
	 * 
	 * @Method: updateMenu  
	 * @Description: 修改菜单
	 * @param menu 菜单实体
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午4:33:49
	 */
	public ResultMsg updateMenu(Menu menu);
	
	/**
	 * 
	 * @Method: selectMenuById  
	 * @Description: 根据菜单Id查询菜单实体
	 * @param id 菜单id
	 * @return Menu 菜单实体
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午4:34:26
	 */
	public Menu selectMenuById(int id);
	
	/**
	 * 
	 * @Method: deleteMenubyId  
	 * @Description: 根据菜单id删除菜单实体
	 * @param id 菜单id
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午4:35:26
	 */
	public ResultMsg deleteMenubyId(int id);
	
//	/**
//	 * 
//	 * @Method: deleteMenubyIdsMenubyIds  
//	 * @Description: 根据菜单id集合删除批量删除菜单实体
//	 * @param ids 菜单id集合
//	 * @author : jiwenfang 
//	 * @CreateDate : 2017年3月7日 下午4:36:12
//	 */
//	public ResultMsg deleteMenubyIdsMenubyIds(List<Integer> ids);
	
	
	/**
	 * 
	 * @Method: findMenuTreeList  
	 * @Description: 根据查询条件查询菜单树形列表（不包括父菜单自己本身对应的菜单）
	 * @param params 查询条件（一般包括菜单父id及菜单状态）
	 * @return List<TreeNode> 返回菜单树形
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午6:48:09
	 */
	public List<TreeNode> findMenuTreeList(Map<String,Object> params);
	

	/**
	 * 
	 * @Method: findMenuTreeList  
	 * @Description:查询角色的树形菜单
	 * @param pid
	 * @param roleId
	 * @return List<TreeNode> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月27日 下午4:26:50
	 */
	public List<TreeNode> findMenuTreeList(int pid, int roleId);
	
	/**
	 * 
	 * @Method: selectMenuByPage  
	 * @Description: 分页查询菜单数据
	 * @param Map<String, Object> params
	 * @return Page<Menu> 返回菜单的分页数据 
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午2:19:46
	 */
	public Page<Menu> selectMenuByPage(Page<Menu> page, Map<String, Object> params);
	 
	/**
	 * 
	 * @Method: updateMenuStatus  
	 * @Description: 修改菜单状态（启用/禁用）
	 * @param menuId 菜单id
	 * @param status 菜单状态
	 * @return ResultMsg
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午4:15:24
	 */
	public ResultMsg updateMenuStatus(int menuId, int status);
	
	/**
	 * 
	 * @Method: findMenuPageByPid  
	 * @Description: (根据菜单父ID查询下级的所有菜单列表，适用于菜单页面列表展示)
	 * @param pid 菜单父ID
	 * @param page 分页对象
	 * @return List<Menu> (菜单集合) 
	 * @throws  
	 * @author : wangjing 
	 * @CreateDate : 2017年3月22日 下午5:41:42
	 */
	public Page<Menu> findMenuPageByPid(int pid, Page<Menu> page);
		
}
