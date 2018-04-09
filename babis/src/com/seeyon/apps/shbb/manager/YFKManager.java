package com.seeyon.apps.shbb.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.seeyon.shbb.utils.GetYfkDataToBing;

import functions.rfc.sap.document.sap_com.advance.ZSTR_OA_CONF_YF;
import functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_INTERFACE_02;
import functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_INTERFACE_02Locator;
import functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_INTERFACE_02Stub;

public class YFKManager extends BaseManager {
	//2017年12月5號对供应商移交的代码进行优化--李志强
	private static final Log log = LogFactory.getLog(YFKManager.class);
	// 预付款单据拋送
	@SuppressWarnings({ "unchecked" })
	public void sapYFKData(String id) throws Exception {
		//定义变量
		 String message = "";//消息
		 String belnr = "";//凭证编号
		 String state = "";//状态
		 Map<String, Object> filters=null;
		 ZSTR_OA_CONF_YF[] cost=null;//对象数组
		 List<ZSTR_OA_CONF_YF> arylist =null;//存放赋值后的集合
		 ZWS_FICO_OA_INTERFACE_02 ser = null;//接口对象
		 ZWS_FICO_OA_INTERFACE_02Stub stub = null;//接口对象
		try {
			List<Map<String, Object>> list = ibatisQueryDao.getSqlMapClientTemplate().queryForList("getSapData.select", id);
			arylist = GetYfkDataToBing.setYFKToBing(list);
			//集合转数组
			cost =  arylist.toArray(new  ZSTR_OA_CONF_YF[arylist.size()]);
			if (cost.length > 0) {
				//创建接口实例
				ser = new ZWS_FICO_OA_INTERFACE_02Locator();
				stub = (ZWS_FICO_OA_INTERFACE_02Stub) ser.getZWS_FICO_OA_INTERFACE_02();
				// 调用SAP接口获取返回值
				ZSTR_OA_CONF_YF[] c = stub.ZWS_FICO_OA_002(cost);
				message = c[0].getMESSAGE();
				belnr = c[0].getBELNR_NEW();
				state = message.substring(0, 1);
			} else {
				message = "没有需要拋送的数据";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "底表数据异常，请仔细核对信息";
			state = "0";
			log.info("预付款单据抛送SAP异常"+e.getMessage());
		}
		// 更新档案底表数据
		try {
			filters = new HashMap<String, Object>();
			filters.put("id", id);
			filters.put("MESSAGE", message);
			filters.put("BELNR", belnr);
			filters.put("state", state);
			ibatisQueryDao.getSqlMapClient().update("sapdate_update", filters);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("预付款单据更新档案异常"+e.getMessage());
		}
		
	}
}
