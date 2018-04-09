package com.seeyon.shbb.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import functions.rfc.sap.document.sap_com.advance.ZSTR_OA_CONF_YF;

public class GetYfkDataToBing{
	/**
	 * 获取预付款的赋值后的集合
	 * @param id选中后的费用项ID
	 * @return 返回赋值后的集合
	 */
	public static List<ZSTR_OA_CONF_YF> setYFKToBing(List<Map<String, Object>> list){
		List<ZSTR_OA_CONF_YF> arylist= new ArrayList<ZSTR_OA_CONF_YF>();
		// 获取所选行的数据
		//KUNNR = 供应商或债权人的帐号
		String KUNNR = GetClientOrUser.GetClientCodeOrUserNumber(list.get(0).get("khdaima"), list.get(0).get("ygbianhao"));
		String XBLNR = list.get(0).get("lcbh") != null ? list.get(0).get("lcbh").toString().replaceAll(" +", "") : "";
		String BLART = list.get(0).get("pzleixing") != null ? list.get(0).get("pzleixing").toString().replaceAll(" +", "") : "";
		String BUKRS = list.get(0).get("gsdaima") != null ? list.get(0).get("gsdaima").toString().replaceAll(" +", "") : "";
		String BUDAT = list.get(0).get("gzdate") != null ? list.get(0).get("gzdate").toString().replaceAll(" +", "") : "";
		String BLDAT =  list.get(0).get("pzdate") != null ? list.get(0).get("pzdate").toString().replaceAll(" +", "") : "";
		String USNAM =  list.get(0).get("gzyonghu") != null ? list.get(0).get("gzyonghu").toString().replaceAll(" +", "") : "";
		String PPNAM =  list.get(0).get("yonghuming") != null ? list.get(0).get("yonghuming").toString().replaceAll(" +", "") : "";
		String NUMPG = list.get(0).get("fujian") != null&& !list.get(0).get("fujian").equals("") ? list.get(0).get("fujian").toString().replaceAll(" +", "") : "";
		String WAERS =  list.get(0).get("huobima") != null ? list.get(0).get("huobima").toString().replaceAll(" +", "") : "";
		String PRCTR =  list.get(0).get("lrzhongxin") != null ? list.get(0).get("lrzhongxin").toString().replaceAll(" +", "") : "";
		String KOSTL =  list.get(0).get("cbzhongxin") != null ? list.get(0).get("cbzhongxin").toString().replaceAll(" +", "") : "";
		// 凭证抬头文本
		String BKTXT =  list.get(0).get("taitoutext") != null ? SubStringLength.SubStringMaxStrLength(list.get(0).get("taitoutext"), 25) : "";
		// 行项目文本
		String SGTXT =  list.get(0).get("hangtext") != null ? SubStringLength.SubStringMaxStrLength(list.get(0).get("hangtext"), 50) : "";
		
		for (int i = 0; i < 8; i++) {
			String causeCode = list.get(0).get("yuanyinma" + String.valueOf(i + 1)) != null ? list.get(0).get("yuanyinma" + String.valueOf(i + 1)).toString().replaceAll(" +", "") : "";
			String RSTGR = causeCode != null && !"".equals(causeCode) ? causeCode.substring(0,causeCode.indexOf("_")) : "";
			String HKONT = list.get(0).get("kjkmdaima" + String.valueOf(i + 1)) != null ? list.get(0).get("kjkmdaima" + String.valueOf(i + 1)).toString().replaceAll(" +", ""): "";
			String DMBTR = list.get(0).get("bwbmoney" + String.valueOf(i + 1)) != null&& !list.get(0).get("bwbmoney" + String.valueOf(i + 1)).equals("") ? list.get(0).get("bwbmoney" + String.valueOf(i + 1)).toString().replaceAll(" +", "") : "0";
			// 金额不为零、原因码不为空
			if (!DMBTR.equals("0") && !RSTGR.equals("")) {
				ZSTR_OA_CONF_YF costs = new ZSTR_OA_CONF_YF();
				costs.setBELNR("");//         凭证号
		    	costs.setBELNR_NEW("");//      凭证号
				costs.setBKTXT(BKTXT);// 凭证抬头文本
				costs.setBLART(BLART);// 凭证类型
				costs.setBLDAT(BLDAT);// 凭证日期
				costs.setBUDAT(BUDAT);// 过账日期
				costs.setBUKRS(BUKRS);// 公司代码
				costs.setDMBTR(DMBTR);// 金额
				costs.setHKONT(HKONT);// 总分类帐帐目 科目代码
				costs.setKOSTL(KOSTL);// 成本中心
				costs.setLIFNR(KUNNR);// 供应商或债权人的帐号
				costs.setMESSAGE("");
				costs.setNUMPG(NUMPG);// 页数
				costs.setPPNAM(PPNAM);// 预制此凭证的用户名
				costs.setPRCTR(PRCTR);// 利润中心
				costs.setRSTGR(RSTGR);// 付款原因代码
				costs.setSGTXT(SGTXT);// 项目文本
				costs.setUSNAM(USNAM);// 用户名
				costs.setWAERS(WAERS);// 货币码
				costs.setXBLNR(XBLNR);// 参照
				costs.setXREF1("");
		    	costs.setXREF2("");
		    	costs.setXREF3("");
		    	costs.setYLZD1("");
		    	costs.setYLZD2("");
		    	costs.setYLZD3("");
		    	costs.setYLZD4("");
		    	costs.setYLZD5("");
		    	costs.setZFI_CONFIRM("");
		    	costs.setZUONR("");//		       分配号
				arylist.add(costs);
			}
		}
		return arylist;
	}
}
