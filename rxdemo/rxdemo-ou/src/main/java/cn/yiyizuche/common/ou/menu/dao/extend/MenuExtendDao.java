package cn.yiyizuche.common.ou.menu.dao.extend;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.ou.menu.dao.MenuDao;
import cn.yiyizuche.common.ou.menu.entity.Menu;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.menu.dao.extend 
 * @Class : MenuExtendDao.java 
 * @Description : 菜单数据层扩展接口
 * @author : jiwenfang 
 * @CreateDate : 2017年3月7日 下午7:25:19 
 * @version : V1.0.0 
 * @Copyright: 2017 htht Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository
public interface MenuExtendDao extends MenuDao{
	/**
	 * 
	 * @Method: deleteByIds  
	 * @Description: 根据菜单id集合删除菜单
	 * @param ids
	 * @return int (返回类型描述) 
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午7:22:01
	 */
	public int deleteByIds(List<Integer> ids);
	
	/**
	 * 
	 * @Method: updateMenuList  
	 * @Description: 查询所有启用菜单
	 * @param pid 菜单根节点id
	 * @return List<Menu> 菜单集合
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午7:23:31
	 */
	public List<Menu> updateMenuList(int pid);

	/**
	 * 
	 * @Method: findMenuChildListByParam  
	 * @Description: 根据查询条件查询子菜单列表
	 * @param paramsMap 查询条件
	 * @return List<Menu> (返回类型描述) 
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午7:24:04
	 */
	public List<Menu> findMenuChildListByParam(Map<String, Object> paramsMap);

	/**
	 * 
	 * @Method: countMenuByMenuName  
	 * @Description: 根据菜单名称查询菜单数量
	 * @param name 菜单名称
	 * @return long 同名的菜单数量
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月7日 下午8:28:21
	 */
	public List<Menu> countMenuByMenuName(String name);
	
	/** 
	 * @Method: countMenuByMenuCode  
	 * @Description: 根据菜单code查询菜单数量
	 * @param code
	 * @return List<Menu> 同code的菜单
	 * @throws  
	 * @author : wangjianwei 
	 * @CreateDate : 2017年3月28日 下午6:48:13 
	 */
	public List<Menu> countMenuByMenuCode(String code);
	
	/**
	 * 
	 * @Method: selectMenuByPage  
	 * @Description: 根据查询条件分页查询菜单数据
	 * @param page 分页对象
	 * @param params 查询条件
	 * @return Page<Menu> 菜单分页查询数据 
	 * @throws  
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午2:23:05
	 */
	public Page<Menu> selectMenuByPage(Page<Menu> page, Map<String, Object> params);

	/**
	 * 
	 * @Method: updateMenuStatus  
	 * @Description: 修改菜单状态（启用、禁用）
	 * @param menuList 菜单列表
	 * @author : jiwenfang 
	 * @CreateDate : 2017年3月9日 下午4:29:37
	 */
	public void updateMenuStatus(List<Menu> menuList);
	
	/**
	 * 
	 * @Method: selectMenuListByPid  
	 * @Description: 根据父id查询菜单子菜单列表
	 * @param pid 父id
	 * @return List<Menu> 菜单列表 
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月10日 下午6:15:04
	 */
	public List<Menu> selectMenuListByPid(int pid);
	
	/**
	 * 
	 * @Method: selectMenuListByMenuIds  
	 * @Description: 根据菜单id数组查询菜单列表
	 * @param menuIds 菜单Id数组
	 * @return List<Menu> 菜单集合
	 * @author :jiwenfang
	 * @CreateDate : 2017年3月17日 上午10:53:17
	 */
	public List<Menu> selectMenuListByMenuIds(int[] menuIds);

	/**
	 *
	 * @Method: findMenuPageByPid
	 * @Description: (根据PID)
	 * @param parames 参数
	 * @param page 分页对象
	 * @return Page<Menu> (菜单分页对象)
	 * @throws
	 * @author : wangjing
	 * @CreateDate : 2017年3月22日 下午9:33:45
	 */
	Page<Menu> findMenuPageByPid(Map<String, Object> parames, Page<Menu> page);
}
