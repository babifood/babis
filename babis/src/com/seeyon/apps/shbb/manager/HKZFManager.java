package com.seeyon.apps.shbb.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.seeyon.shbb.utils.GetHkzfDataToBing;

import functions.rfc.sap.document.sap_com.payment.ZSTR_OA_CONF_YF;
import functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03;
import functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Locator;
import functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Stub;

public class HKZFManager extends BaseManager{
	private static final Log log = LogFactory.getLog(HKZFManager.class);
	// 货款支付单据拋送
	@SuppressWarnings({ "unchecked" })
	public void sapHKZFData(String id) {
		 String message = "";
		 String belnr = "";
		 String state = "";
		 Map<String, Object> filters=null;
		 List<ZSTR_OA_CONF_YF> arylist =null;
		 ZSTR_OA_CONF_YF[] cost =null;
		 ZWS_FICO_OA_INTERFACE_03 ser=null;
		 ZWS_FICO_OA_INTERFACE_03Stub stub =null;
		try {
			List<Map<String, Object>> list = ibatisQueryDao.getSqlMapClientTemplate().queryForList("getSapData.select", id);
			arylist = GetHkzfDataToBing.setHkzfToBing(list);
			cost = arylist.toArray(new ZSTR_OA_CONF_YF[arylist.size()]);
			if (cost.length > 0) {
				// 调用SAP接口获取返回值
				ser = new ZWS_FICO_OA_INTERFACE_03Locator();
				stub = (ZWS_FICO_OA_INTERFACE_03Stub) ser.getZWS_FICO_OA_INTERFACE_03();
				ZSTR_OA_CONF_YF[] c = stub.ZWS_FICO_OA_003(cost);
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
			log.info("货款支付抛送SAP异常:"+e.getMessage());
		}
		try {
			// 更新档案底表数据
			filters = new HashMap<String, Object>();
			filters.put("id", id);
			filters.put("MESSAGE", message);
			filters.put("BELNR", belnr);
			filters.put("state", state);
			ibatisQueryDao.getSqlMapClient().update("sapdate_update", filters);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("货款支付更新档案异常:"+e.getMessage());
		}
		
	}
}
