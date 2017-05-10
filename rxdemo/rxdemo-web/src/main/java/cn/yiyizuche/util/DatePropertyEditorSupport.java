package cn.yiyizuche.util;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.util
 * @FileName : DatePropertyEditorSupport.java
 * @Description : Date在BaseController的数据转换器
 * @author : lipeng
 * @CreateDate : 2017/3/22 20:51
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class DatePropertyEditorSupport extends PropertyEditorSupport {
	
	private static final String[] DATEFORMATSTR = {"yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss","yyyy-MM-dd","yyyy/MM/dd"};
	
	private int dateformatstr_index = 0;

	/**
	 *
	 * (非 Javadoc)
	 *
	 * @param
	 * @see DatePropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		return new SimpleDateFormat(DATEFORMATSTR[dateformatstr_index]).format((Date) getValue());
	}

	/**
	 *
	 * (非 Javadoc)
	 *
	 * @param text
	 * @see DatePropertyEditorSupport#setAsText(String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(null);
		} else {
			for (int i = 0; i < DATEFORMATSTR.length; i++) {
				Object data = null;
				try {
					data = new SimpleDateFormat(DATEFORMATSTR[i]).parse(text);
				} catch (ParseException e) {
					continue;
				}
				setValue(data);
				dateformatstr_index = i;
				return;
			}
			throw new IllegalArgumentException("时间格式解析异常!");
		}
	}
}
