package com.seeyon.shbb.utils;

public class SubStringLength {
	/**
	 * 截取字符长度
	 * @param obj 原值
	 * @param Length 截取的长度
	 * @return 返回截取后的值
	 */
	public static String SubStringMaxStrLength(Object obj,int Length){
		if(obj==null||"".equals(obj))
		{
			return "";
		}else{
			String str = obj.toString().replaceAll(" +","");
			if(str.length()<=Length){
				return str;
			}else{
				return str.substring(0, Length);
			}
		}
		
	}
}
