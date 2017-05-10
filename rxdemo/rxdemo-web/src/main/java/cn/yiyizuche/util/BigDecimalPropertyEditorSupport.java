package cn.yiyizuche.util;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.util
 * @FileName : BigDecimalPropertyEditorSupport.java
 * @Description : BigDecimal在BaseController的数据转换器
 * @author : lipeng
 * @CreateDate : 2017/3/22 20:52
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class BigDecimalPropertyEditorSupport extends PropertyEditorSupport {

	/**
	 *
	 * (非 Javadoc)
	 *
	 * @param
	 * @see BigDecimalPropertyEditorSupport#getAsText()
	 */
	public String getAsText() {
		return ((BigDecimal) getValue()).toString();
	}

	/**
	 *
	 * (非 Javadoc)
	 *
	 * @param text
	 * @see BigDecimalPropertyEditorSupport#setAsText(String)
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(BigDecimal.valueOf(0));
		} else {
			try {
				setValue(new BigDecimal(text));
			} catch (Exception e) {
				setValue(BigDecimal.valueOf(0));
			}
		}
	}
}
