package cn.yiyizuche.common.base;

import cn.yiyizuche.util.BigDecimalPropertyEditorSupport;
import cn.yiyizuche.util.DatePropertyEditorSupport;
import cn.yiyizuche.util.DoublePropertyEditorSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.base
 * @FileName : BaseController.java
 * @Description : 全局Controller 提供了数据转换方式
 * @author : lipeng
 * @CreateDate : 2017/3/22 20:49
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class BaseController {

    /**
     * @Method: initBinder
     * @Description: 类型转换器
     * @param binder
     * @return void
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/22 20:50
     */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditorSupport());
		binder.registerCustomEditor(Double.class, new DoublePropertyEditorSupport());
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditorSupport());
	}

}
