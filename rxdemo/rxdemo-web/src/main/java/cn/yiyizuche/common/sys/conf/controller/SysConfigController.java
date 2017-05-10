package cn.yiyizuche.common.sys.conf.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yiyizuche.common.base.BaseController;
import cn.yiyizuche.common.base.Page;
import cn.yiyizuche.common.base.ResultMsg;
import cn.yiyizuche.common.ou.user.entity.UserVo;
import cn.yiyizuche.common.sys.conf.entity.SysConfig;
import cn.yiyizuche.common.sys.conf.service.SysConfigService;
import cn.yiyizuche.common.sys.util.RequestUtil;
import cn.yiyizuche.common.sys.util.SysConstants;

/**
 * 
 * @Project: 壹壹OA 
 * @Package : cn.yiyizuche.common.sys.conf.controller 
 * @Class : SysConfigController.java 
 * @Description :配置Controller
 * @author : Rush.D.Xzj
 * @CreateDate : 2017年4月12日 下午3:55:16 
 * @version : V1.0.0 
 * @Copyright: 2017 yizukeji Inc. All rights reserved.
 * @Reviewed :  
 * @UpateLog:	Name	Date	Reason/Contents 
 * 			--------------------------------------- 
 * 				***		****	**** 
 *
 */
@Controller
@RequestMapping("/sys/conf")
public class SysConfigController extends BaseController {

	@Autowired
    private SysConfigService sysConfigService;

	/**
	 * 
	 * @Method: selectByConfigId  
	 * @Description:通过Id搜索配置
	 * @param id
	 * @param req
	 * @return
	 * @throws Exception SysConfig (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:55:25
	 */
	@ResponseBody
    @RequestMapping(value = "/selectByConfigId", method = RequestMethod.POST)
    public SysConfig selectByConfigId(@RequestParam(value = "id", defaultValue = "0") Integer id, HttpServletRequest req) throws Exception {
		SysConfig sysConfig = this.sysConfigService.selectByConfigId(id);
		return sysConfig;
	}
	
	/**
	 * 
	 * @Method: addConfig  
	 * @Description:添加配置
	 * @param sysConfig
	 * @param req
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:55:34
	 */
    @ResponseBody
    @RequestMapping(value = "/addConfig", method = RequestMethod.POST)
    public ResultMsg addConfig(SysConfig sysConfig, HttpServletRequest req) {
    		UserVo userVo = (UserVo)RequestUtil.getSessionAttribute(req, SysConstants.USER_INFO);
		sysConfig.setCreateUser(userVo.getUserId());
		sysConfig.setCreateTime(new Date());
		return this.sysConfigService.addConfig(sysConfig);
	}
	
	/**
	 * 
	 * @Method: updateConfig  
	 * @Description:更新配置
	 * @param sysConfig
	 * @param req
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:57:50
	 */
	@ResponseBody
    @RequestMapping(value = "/updateConfig", method = RequestMethod.POST)
    public ResultMsg updateConfig(SysConfig sysConfig, HttpServletRequest req) {
		UserVo userVo = (UserVo)RequestUtil.getSessionAttribute(req, SysConstants.USER_INFO);
		sysConfig.setUpdateUser(userVo.getUserId());
		sysConfig.setUpdateTime(new Date());
		return this.sysConfigService.updateConfig(sysConfig);
	}
	
	/**
	 * 
	 * @Method: deleteByConfigId  
	 * @Description:删除配置
	 * @param id
	 * @param req
	 * @return ResultMsg (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:55:40
	 */
    @ResponseBody
    @RequestMapping(value = "/deleteByConfigId", method = RequestMethod.POST)
    public ResultMsg deleteByConfigId(@RequestParam(value = "id", defaultValue = "0") Integer id, HttpServletRequest req) {
		return this.sysConfigService.deleteByConfigId(id);
	}
	
	/**
	 * 
	 * @Method: searchConfigByPage  
	 * @Description:分页查询配置
	 * @param configName
	 * @param configKey
	 * @param current
	 * @param rowCount
	 * @return Map<String,Object> (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:55:51
	 */
	@ResponseBody
	@RequestMapping(value = "/searchConfigByPage", method = RequestMethod.POST)
	public Map<String, Object> searchConfigByPage(String configName, String configKey, int current, int rowCount) {
		Page<SysConfig> page = new Page<>(current, rowCount);
		Page<SysConfig> pageResult = this.sysConfigService.selectConfigListByName(configName, configKey, page);
		Map<String, Object> gridMap = pageResult.getGridMap();
		return gridMap;
	}
	
	/**
	 * 
	 * @Method: showSysConfigPage  
	 * @Description:显示配置页面
	 * @return ModelAndView (返回类型描述) 
	 * @throws  
	 * @author : Rush.D.Xzj
	 * @CreateDate : 2017年4月12日 下午3:55:59
	 */
	@RequestMapping(value = "/showSysConfigPage", method = RequestMethod.GET)
	public ModelAndView showSysConfigPage(){
		ModelAndView view = new ModelAndView("/page/sys/conf/config_list");
		_log.info("showSysConfigPage");
		return view;
	}
	
	private static final Logger _log = LoggerFactory.getLogger(SysConfigController.class);
	
}
