package cn.yiyizuche.util;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.util
 * @FileName : DoublePropertyEditorSupport.java
 * @Description : Double在BaseController的数据转换器
 * @author : lipeng
 * @CreateDate : 2017/3/22 20:53
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class DoublePropertyEditorSupport extends PropertyEditorSupport {

	/**
	 *
	 * (非 Javadoc)
	 *
	 * @param
	 * @see DoublePropertyEditorSupport#getAsText()
	 */
	public String getAsText() {
		return ((Double) getValue()).toString();
	}

	/**
	 *
	 * (非 Javadoc)
	 *
	 * @param text
	 * @see DoublePropertyEditorSupport#setAsText(String)
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(Double.valueOf(0));
		} else {
			try {
				setValue(new Double(text));
			} catch (Exception e) {
				setValue(Double.valueOf(0));
			}
		}
	}
}
