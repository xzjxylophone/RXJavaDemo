package cn.yiyizuche.common.ou.user.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UserInfoVo extends User {

	
	private String birthdateString;
	
	public void setBirthdateString(String birthdateString) {
        this.birthdateString = birthdateString == null ? null : birthdateString.trim();
    }

    public String getBirthdateString() {
        return birthdateString == null ? "" : birthdateString;
    }
    
    public UserInfoVo(User user) throws Exception {
		Class<?> clz = user.getClass();
		Field[] fields = clz.getDeclaredFields();
		

		Class<?> userInfoVoClz = this.getClass();
		

//		field.setAccessible(true);
//		// // 获取get方法对象
//		Method fieldGetMethod = clz.getMethod(getMethodName);
//		Object invoke = fieldGetMethod.invoke(attendanceExtend);
		
		for (Field field : fields) {
    			String propertyName = field.getName(); // 属性名称
    			String methodPropertyName = propertyName.substring(0, 1).toUpperCase() + 
    	    			propertyName.substring(1, propertyName.length());
    			
    			String getName = "get" + methodPropertyName; // get方法名称
    			Method fieldGetMethod = clz.getMethod(getName);
    			Object invoke = fieldGetMethod.invoke(user);

    			String setName = "set" + methodPropertyName; // set方法名称
			Method fieldSetMet = userInfoVoClz.getMethod(setName, field.getType());
			fieldSetMet.invoke(this, invoke);
			
		}
    	
    }
    
    
}
