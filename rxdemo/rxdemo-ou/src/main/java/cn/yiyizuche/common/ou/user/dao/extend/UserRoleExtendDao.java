package cn.yiyizuche.common.ou.user.dao.extend;

import java.util.List;

import cn.yiyizuche.common.ou.user.dao.UserRoleDao;
import cn.yiyizuche.common.ou.user.entity.UserRoleKey;
import cn.yiyizuche.common.ou.user.entity.UserRoleVo;


public interface UserRoleExtendDao extends UserRoleDao {
	
	/**
	 * 
	 * @Method: selectUserRole  
	 * @Description:通过userRole搜索UserRole
	 * @param userRole
	 * @return UserRoleKey (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月10日 下午3:49:26
	 */
	UserRoleKey selectUserRole(UserRoleKey userRole);
	
	/**
	 * 
	 * @Method: selectAll  
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @return List<UserRole> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:49
	 */
	List<UserRoleKey> selectAll();
	
	/**
	 * 
	 * @Method: selectUserRoleListByUserId  
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param userId
	 * @return List<UserRole> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:46
	 */
	List<UserRoleKey> selectUserRoleListByUserId(int userId);
	
	/**
	 * 
	 * @Method: selectUserRoleListByRoleId  
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param roleId
	 * @return List<UserRole> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:43
	 */
	List<UserRoleKey> selectUserRoleListByRoleId(int roleId);
	
	/**
	 * 
	 * @Method: deleteByUserRoleId  
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param id
	 * @return int (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月8日 下午2:53:39
	 */
	int deleteByUserRole(UserRoleKey userRole);

	/**
	 * 
	 * @Method: selectUserRoleVoListByRoleId  
	 * @Description:通过RoleId搜索UserRoleVo
	 * @param roleId
	 * @return List<UserRoleVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:46:13
	 */
	List<UserRoleVo> selectUserRoleVoListByRoleId(int roleId);
	
	/**
	 * 
	 * @Method: selectUserRoleVoListByUserId  
	 * @Description:通过UserId搜索UserRoleVo
	 * @param userId
	 * @return List<UserRoleVo> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月28日 下午5:46:20
	 */
	List<UserRoleVo> selectUserRoleVoListByUserId(int userId);
}
