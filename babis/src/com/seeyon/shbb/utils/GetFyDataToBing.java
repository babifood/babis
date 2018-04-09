package com.seeyon.shbb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import functions.rfc.sap.document.sap_com.expense.ZSTR_OA_COST;

public class GetFyDataToBing{
	/**
	 * 获取费用的赋值后的集合
	 * @param id选中后的费用项ID
	 * @return 返回赋值后的集合
	 */
	public static List<ZSTR_OA_COST> GetFyToBing(List<Map<String, Object>> list,List<Map<String, Object>> list2){
		List<ZSTR_OA_COST> arylist = new ArrayList<ZSTR_OA_COST>();
		// 获取所选行的数据
		String KUNNR = GetClientOrUser.GetClientCodeOrUserNumber(list.get(0).get("khdaima"), list.get(0).get("ygbianhao"));
		String XBLNR =  list.get(0).get("lcbh") != null ? list.get(0).get("lcbh").toString().replaceAll(" +", "") : "";
		String BLART =  list.get(0).get("pzleixing") != null ? list.get(0).get("pzleixing").toString().replaceAll(" +", "") : "";
		String BUKRS =  list.get(0).get("gsdaima") != null ? list.get(0).get("gsdaima").toString().replaceAll(" +", "") : "";
		String BUDAT =  list.get(0).get("gzdate") != null ? list.get(0).get("gzdate").toString().replaceAll(" +", "") : "";
		String BLDAT =  list.get(0).get("pzdate") != null ? list.get(0).get("pzdate").toString().replaceAll(" +", "") : "";
		String USNAM =  list.get(0).get("gzyonghu") != null ? list.get(0).get("gzyonghu").toString().replaceAll(" +", "") : "";
		String PPNAM = list.get(0).get("yonghuming") != null ? list.get(0).get("yonghuming").toString().replaceAll(" +", "") : "";
		String NUMPG = list.get(0).get("fujian") != null&& !list.get(0).get("fujian").equals("") ? list.get(0).get("fujian").toString().replaceAll(" +", "") : "";
		String WAERS =  list.get(0).get("huobima") != null ? list.get(0).get("huobima").toString().replaceAll(" +", "") : "";
		String PRCTR = list.get(0).get("lrzhongxin") != null ? list.get(0).get("lrzhongxin").toString().replaceAll(" +", "") : "";
		String KOSTL =  list.get(0).get("cbzhongxin") != null ? list.get(0).get("cbzhongxin").toString().replaceAll(" +", "") : "";
		String ANLN1 = list.get(0).get("zcbainhao") != null ? list.get(0).get("zcbainhao").toString().replaceAll(" +", "") : "";
		// 凭证抬头文本
		String BKTXT =  list.get(0).get("taitoutext") != null ? SubStringLength.SubStringMaxStrLength(list.get(0).get("taitoutext"), 25) : "";
		// 行项目文本
		String SGTXT =  list.get(0).get("hangtext") != null ? SubStringLength.SubStringMaxStrLength(list.get(0).get("hangtext"), 50) : "";
		//获取用户编号
		String userNumber = list.get(0).get("yuangongno") != null ? list.get(0).get("yuangongno").toString().replaceAll(" +", ""): "";
		//获取表单名称
		String FormName =  list.get(0).get("bdlaiyuan") != null ? list.get(0).get("bdlaiyuan").toString().replaceAll(" +", "") : "";
		// 团餐用户需要传报销人的员工编号，对应SAP中的“段”
		String SEGMENT = GetClientOrUser.GetTcUserNumber(FormName, BUKRS, KOSTL, userNumber);
		// 查询重复表
		for (int i = 0; i < list2.size(); i++) {
			//行号
			String FIELD0069 = list2.get(i).get("xuhao") != null ? list2.get(i).get("xuhao").toString().replaceAll(" +", "") : "";
			//记账码
			String FIELD0070 = (String) list2.get(i).get("jizhang") != null ? list2.get(i).get("jizhang").toString().replaceAll(" +", ""): "";
			//科目代码
			String FIELD0071 = (String) list2.get(i).get("kemu") != null ? list2.get(i).get("kemu").toString().replaceAll(" +", ""): "";
			//金额
			String FIELD0072 = list2.get(i).get("jinge") != null ? list2.get(i).get("jinge").toString().replaceAll(" +", "") : "0";
			// 项目文本(内容摘要)
			String FIELD0083 = (String) list2.get(i).get("wenben") != null ? SubStringLength.SubStringMaxStrLength(list2.get(i).get("wenben"), 50) : "";
			//待摊费用
			String FIELD0084 = (String) list2.get(i).get("daitan") != null ? list2.get(i).get("daitan").toString().replaceAll(" +", ""): "";
			// 行号不为空、金额不为零 、金额不为空
			if (FIELD0069 != null && !FIELD0072.equals("0")) {
				ZSTR_OA_COST costs = new ZSTR_OA_COST();
				costs.setANBWA("");// 资产交易类型
				costs.setBLART(BLART);// 凭证类型
				costs.setBUKRS(BUKRS);// 公司代码
				costs.setBUDAT(BUDAT);// 过账日期
				costs.setBLDAT(BLDAT);// 凭证日期
				costs.setXBLNR(XBLNR);// 参照
				costs.setBKTXT(BKTXT);// 凭证抬头文本
				costs.setUSNAM(USNAM);// 用户名（过账人）
				costs.setPPNAM(PPNAM);// 预制此凭证的用户名（预制人）
				costs.setNUMPG(NUMPG);// 页数
				costs.setXBLNR_ALT("");// 备选参考编号
				costs.setXREF1_HD("");// 凭证标题的内部参考码 1
				costs.setXREF2_HD("");// 凭证抬头的内部参考码 2
				costs.setYLZD1H("");// 预留1（需求更改--用户id）
				costs.setYLZD2H("");// 预留2（需求更改--预制此凭证的id）
				costs.setITEMNO(FIELD0069);// 会计凭证行项目编号 序号
				costs.setBSCHL(FIELD0070);// 记帐代码
				costs.setUMSKZ("");// 特别总账标识 会计科目代码
				costs.setHKONT(FIELD0071);// 总分类帐帐目
				costs.setSGTXT(FIELD0083);// 项目文本
				costs.setKUNNR(KUNNR);// 客户编号
				costs.setLIFNR(KUNNR);// 供应商或债权人的帐号
				costs.setZUONR("");// 分配号
				costs.setANLN1(ANLN1);// 主资产号
				costs.setANLN2("");// 资产次级编号
				costs.setEBELN("");// 采购凭证编号
				costs.setEBELP("");// 采购凭证的项目编号
				costs.setKOSTL(KOSTL);// 成本中心
				costs.setPRCTR(PRCTR);// 利润中心
				costs.setAUFNR("");// 订单号
				costs.setSEGMENT(SEGMENT);// 段
				costs.setGSBER("");// 业务范围
				costs.setFKBER_SHORT("");// 功能范围
				costs.setMATNR("");// 物料编号
				costs.setWERKS("");// 工厂
				costs.setMEINS("");// 数量
				costs.setMENGE("");// 单位
				costs.setDMBTR(FIELD0072);// 金额
				costs.setWRBTR("");// 金额
				costs.setWAERS(WAERS);// 货币码
				costs.setRSTGR("");// 付款原因代码
				costs.setXREF1("");
				costs.setXREF2("");
				costs.setXREF2("");
				costs.setXREF3("");
				costs.setYLZD1("");
				costs.setYLZD2("");
				costs.setYLZD3(FIELD0084);
				costs.setYLZD4("");
				costs.setYLZD5("");
				costs.setBELNR("");
				costs.setGJAHR("");
				costs.setMESSAGE("");
				arylist.add(costs);
			}
		}
		for (int i = 0; i < 8; i++) {
			String ITEMNO =  list.get(0).get("hanghao" + String.valueOf(i + 1)) != null ? list.get(0).get("hanghao" + String.valueOf(i + 1)).toString().replaceAll(" +", "") : "";
			String BSCHL =  list.get(0).get("jzdaima" + String.valueOf(i + 1)) != null ? list.get(0).get("jzdaima" + String.valueOf(i + 1)).toString().replaceAll(" +", "") : "";
			String UMSKZ =  list.get(0).get("zzbiaoshi" + String.valueOf(i + 1)) != null ? list.get(0).get("zzbiaoshi" + String.valueOf(i + 1)).toString().replaceAll(" +", "") : "";
			String causeCode =  list.get(0).get("yuanyinma" + String.valueOf(i + 1)) != null ? list.get(0).get("yuanyinma" + String.valueOf(i + 1)).toString().replaceAll(" +", "") : "";
			String RSTGR = causeCode != null && !causeCode.equals("") ? causeCode.substring(0,causeCode.indexOf("_")) : "";
			String HKONT = list.get(0).get("kjkmdaima" + String.valueOf(i + 1)) != null ? (String) list.get(0).get("kjkmdaima" + String.valueOf(i + 1)).toString().replaceAll(" +", ""): "";
			String DMBTR = list.get(0).get("bwbmoney" + String.valueOf(i + 1)) != null&& !list.get(0).get("bwbmoney" + String.valueOf(i + 1)).equals("") ? list.get(0).get("bwbmoney" + String.valueOf(i + 1)).toString().replaceAll(" +", "") : "0";
			// 行号不为空、金额不为零 、金额不为空
			if (ITEMNO != null && !DMBTR.equals("0")) {
				ZSTR_OA_COST costs = new ZSTR_OA_COST();
				costs.setANBWA("");// 资产交易类型
				costs.setBLART(BLART);// 凭证类型
				costs.setBUKRS(BUKRS);// 公司代码
				costs.setBUDAT(BUDAT);// 过账日期
				costs.setBLDAT(BLDAT);// 凭证日期
				costs.setXBLNR(XBLNR);// 参照
				costs.setBKTXT(BKTXT);// 凭证抬头文本
				costs.setUSNAM(USNAM);// 用户名
				costs.setPPNAM(PPNAM);// 预制此凭证的用户名
				costs.setNUMPG(NUMPG);// 页数
				costs.setXBLNR_ALT("");// 备选参考编号
				costs.setXREF1_HD("");// 凭证标题的内部参考码 1
				costs.setXREF2_HD("");// 凭证抬头的内部参考码 2
				costs.setYLZD1H("");// 预留1（需求更改--用户id）
				costs.setYLZD2H("");// 预留2（需求更改--预制此凭证的id）
				costs.setITEMNO(ITEMNO);// 会计凭证行项目编号
				costs.setBSCHL(BSCHL);// 记帐代码
				costs.setUMSKZ(UMSKZ);// 特别总账标识
				costs.setHKONT(HKONT);// 总分类帐帐目
				costs.setSGTXT(SGTXT);// 项目文本
				costs.setKUNNR(KUNNR);// 客户编号
				costs.setLIFNR(KUNNR);// 供应商或债权人的帐号
				costs.setZUONR("");// 分配号
				costs.setANLN1(ANLN1);// 主资产号
				costs.setANLN2("");// 资产次级编号
				costs.setEBELN("");// 采购凭证编号
				costs.setEBELP("");// 采购凭证的项目编号
				costs.setKOSTL(KOSTL);// 成本中心
				costs.setPRCTR(PRCTR);// 利润中心
				costs.setAUFNR("");// 订单号
				costs.setSEGMENT(SEGMENT);// 段
				costs.setGSBER("");// 业务范围
				costs.setFKBER_SHORT("");// 功能范围
				costs.setMATNR("");// 物料编号
				costs.setWERKS("");// 工厂
				costs.setMEINS("");// 数量
				costs.setMENGE("");// 单位
				costs.setDMBTR(DMBTR);// 金额
				costs.setWRBTR("");// 金额
				costs.setWAERS(WAERS);// 货币码
				costs.setRSTGR(RSTGR);// 付款原因代码
				costs.setXREF1("");
				costs.setXREF2("");
				costs.setXREF2("");
				costs.setXREF3("");
				costs.setYLZD1("");
				costs.setYLZD2("");
				costs.setYLZD3("");
				costs.setYLZD4("");
				costs.setYLZD5("");
				costs.setBELNR("");
				costs.setGJAHR("");
				costs.setMESSAGE("");
				arylist.add(costs);
			}
		}
		return arylist;
	}
}
