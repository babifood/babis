package com.seeyon.apps.shbb.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.seeyon.shbb.utils.GetFyDataToBing;

import functions.rfc.sap.document.sap_com.expense.ZSTR_OA_COST;
import functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACE;
import functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACELocator;
import functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACEStub;

public class FYManager extends BaseManager{
	private static final Log log = LogFactory.getLog(FYManager.class);
	// 费用类单据拋送
	@SuppressWarnings({ "unchecked" })
	public void sapData(String id) throws Exception {
		 String message = "";
		 String belnr = "";
		 String state = "";
		 Map<String, Object> filters =null;
		 List<ZSTR_OA_COST> arylist =null;
		 ZSTR_OA_COST[] cost = null;
		 ZWS_FICO_OA_INTERFACE ser = null;
		 ZWS_FICO_OA_INTERFACEStub stub =null;
		try {
			List<Map<String, Object>> list = ibatisQueryDao.getSqlMapClientTemplate().queryForList("getSapData.select", id);
			List<Map<String, Object>> list2 = ibatisQueryDao.getSqlMapClientTemplate().queryForList("sapdate_select", id);
			arylist = GetFyDataToBing.GetFyToBing(list,list2);
			cost = arylist.toArray(new ZSTR_OA_COST[arylist.size()]);
			if (cost.length > 0) {
				// 调用SAP接口获取返回值
				ser = new ZWS_FICO_OA_INTERFACELocator();
				stub = (ZWS_FICO_OA_INTERFACEStub) ser.getZWS_FICO_OA_INTERFACE();
				ZSTR_OA_COST[] c = stub.ZWS_FICO_OA_001(cost);
				message = c[0].getMESSAGE();
				belnr = c[0].getBELNR();
				state = message.substring(0, 1);
			} else {
				message = "没有需要拋送的数据";
			}
		} catch (Exception e) {
			message = "底表数据异常，请仔细核对信息";
			state = "0";
			log.info("费用类单据拋送SAP失败：" + e.getMessage());
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
			log.info("费用类单据更新底表异常：" + e.getMessage());
		}
	}
}
