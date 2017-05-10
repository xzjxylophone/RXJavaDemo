package cn.yiyizuche.common.ou.menu.dao.impl.extend;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.ou.menu.dao.extend.MenuExtendDao;
import cn.yiyizuche.common.ou.menu.dao.impl.MenuDaoImpl;
import cn.yiyizuche.common.ou.menu.entity.Menu;
/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.menu.dao.impl.extend 
 * @Class : MenuExtendDaoImpl.java 
 * @Description : 菜单数据层扩展实现类
 * @author : jiwenfang 
 * @CreateDate : 2017年3月7日 下午7:26:44 
 * @version : V1.0.0 
 * @Copyright: 2017 zizukeji Inc. All rights reserved. 
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Repository(value="menuExtendDao")
public class MenuExtendDaoImpl extends MenuDaoImpl implements MenuExtendDao{
	/**根据菜单id集合删除菜单
	 * (非 Javadoc) 
	 *   
	 * @param ids 菜单id集合
	 * @return  
	 * @see cn.yiyizuche.common.sys.menu.dao.extend.MenuExtendDao#deleteByIds(java.util.List)
	 */
	public int deleteByIds(List<Integer> ids){
		return this.getSqlSession().delete(NAMESPACE+".deleteByIds", ids);
	}
	
	/**
	 * 查询所有菜单
	 * (非 Javadoc) 
	 *   
	 * @return  
	 * @see cn.yiyizuche.common.sys.menu.dao.extend.MenuExtendDao#selectAllMenu()
	 */
	public List<Menu> updateMenuList(int pid) {
		return this.getSqlSession().selectList(NAMESPACE+".updateMenuList", pid);
	}

	/**
	 * 根据条件查询子菜单列表
	 * (非 Javadoc) 
	 *   
	 * @param paramsMap 菜单父id
	 * @return  
	 * @see cn.yiyizuche.common.sys.menu.dao.extend.MenuExtendDao#findMenuTreeListByPid(int)
	 */
	public List<Menu> findMenuChildListByParam(Map<String, Object> paramsMap) {
		return this.getSqlSession().selectList(NAMESPACE+".findMenuChildListByParam",paramsMap);
	}

	/**
	 * 根据菜单名称查询菜单数量
	 * (非 Javadoc) 
	 *   
	 * @param name 菜单名称
	 * @return  
	 * @see cn.yiyizuche.common.sys.menu.dao.extend.MenuExtendDao#countMenuByMenuName(java.lang.String)
	 */
	@Override
	public List<Menu> countMenuByMenuName(String name) {
		return this.getSqlSession().selectList(NAMESPACE+".countMenuByMenuName",name);
	}

	
	/** (非 Javadoc) 
	 *   
	 * @param code 菜单code
	 * @return  
	 * @see cn.yiyizuche.common.ou.menu.dao.extend.MenuExtendDao#countMenuByMenuCode(java.lang.String)  
	 */
	@Override
	public List<Menu> countMenuByMenuCode(String code) {
		return this.getSqlSession().selectList(NAMESPACE+".countMenuByMenuCode",code);
	}
	
	/**
	 * 根据条件分页查询菜单数据
	 * (非 Javadoc) 
	 *   
	 * @param page 菜单分页对象
	 * @param params 查询条件
	 * @return  
	 * @see cn.yiyizuche.common.sys.menu.dao.extend.MenuExtendDao#selectMenuByPage(cn.yiyizuche.common.base.Page, java.util.Map)
	 */
	@Override
	public Page<Menu> selectMenuByPage(Page<Menu> page, Map<String, Object> paramsMap) {
		return this.selectPage(page, NAMESPACE + ".selectByPage", NAMESPACE + ".selectByPageCount", paramsMap);
	}

	/**
	 * 修改菜单状态（启用、禁用）
	 * (非 Javadoc) 
	 *   
	 * @param menuList 菜单集合
	 * @see cn.yiyizuche.common.sys.menu.dao.extend.MenuExtendDao#updateMenuStatus(int, int)
	 */
	@Override
	public void updateMenuStatus(List<Menu> menuList) {
		 this.getSqlSession().update(NAMESPACE+".updateMenuStatus",menuList);
	}

	/**
	 * 根据父id查询菜单子菜单列表
	 * (非 Javadoc) 
	 *   
	 * @param pid 菜单父id
	 * @return  
	 * @see cn.yiyizuche.common.ou.menu.dao.extend.MenuExtendDao#selectMenuListByPid(int)
	 */
	@Override
	public List<Menu> selectMenuListByPid(int pid) {
		return this.getSqlSession().selectList(NAMESPACE+".selectMenuListByPid", pid);
	}

	/**
	 * 根据菜单id数组查询菜单列表
	 * (非 Javadoc) 
	 *   
	 * @param menuIds 菜单Id数组
	 * @return  
	 * @see cn.yiyizuche.common.ou.menu.dao.extend.MenuExtendDao#selectMenuListByMenuIds(int[])
	 */
	@Override
	public List<Menu> selectMenuListByMenuIds(int[] menuIds) {
		return this.getSqlSession().selectList(NAMESPACE+".selectMenuListByMenuIds", menuIds);
	}

	/**
	 * (非 Javadoc)
	 *
	 * @param parames 参数
	 * @param page 分页对象
	 * @return Page<Menu> (菜单分页对象)
	 * @see cn.yiyizuche.common.ou.menu.dao.extend.MenuExtendDao#findMenuPageByPid(java.util.Map, cn.yiyizuche.common.base.Page)
	 */
	@Override
	public Page<Menu> findMenuPageByPid(Map<String, Object> parames, Page<Menu> page) {
		return this.selectPage(page, NAMESPACE+".findMenuPageByPid", NAMESPACE+".findMenuPageCountByPid", parames);
	}
}
