package com.seeyon.shbb.utils;
/**
 * 获取客户代码或用户编号
 * @param clientCode 客户代码
 * @param userNumber 用户编号
 * @return 返回客户代码或用户编号
 */
public class GetClientOrUser {
	public static String GetClientCodeOrUserNumber(Object clientCode,Object userNumber){
		String clientcode =clientCode==null||"".equals(clientCode)?"":clientCode.toString().replaceAll(" +", "");
		String usernumber=userNumber==null||"".equals(userNumber)?"":userNumber.toString().replaceAll(" +", "");
		String KUNNR = "";
		if ("".equals(clientcode) && "".equals(usernumber)) {
			return KUNNR;
		} else if ("".equals(clientcode)  && !"".equals(usernumber)) {
			 KUNNR = usernumber;
		} else if (!"".equals(clientcode) ) {
			 KUNNR = clientcode;
		}
		return KUNNR;
	}
	/**
	 * 获取团餐用户编号
	 * @param FormName表单名称
	 * @param companyCode公司代码
	 * @param Cost成本中心
	 * @param userNumber用户编号
	 * @return
	 */
	public static String GetTcUserNumber(String FormName,String companyCode,String Cost,String  userNumber){
		String SEGMENT = "";
		if("".equals(Cost)){
			return SEGMENT;
		}else if ("1000".equals(companyCode) && "100003".equals(Cost.substring(0, 6)) || "1300".equals(companyCode) && "130003".equals(Cost.substring(0, 6))) {
			SEGMENT =  userNumber;
		}
		return SEGMENT;
	}
}
