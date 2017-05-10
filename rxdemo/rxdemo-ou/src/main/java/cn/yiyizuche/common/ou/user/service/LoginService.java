package cn.yiyizuche.common.ou.user.service;

import java.util.List;
import java.util.Map;

import cn.yiyizuche.common.ou.menu.entity.Menu;


public interface LoginService {

	/**
	 * 
	 * @Method: selectByUserNameAndPwd  
	 * @Description:
	 * @param userName
	 * @param pwd
	 * @return  Map<String,Object> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年3月20日 下午2:56:22
	 */
	 Map<String,Object> selectByUserNameAndPwd(String userName, String pwd);

	 /**
	  * 
	  * @Method: updateMenuList  
	  * @Description: 根据用户id查询菜单列表
	  * @param userId 用户ID
	  * @return List<Menu> (返回类型描述) 
	  * @author :jiwenfang
	  * @CreateDate : 2017年3月20日 下午9:11:04
	  */
	List<Menu> updateMenuList(int userId);
}
