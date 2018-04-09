package functions.rfc.sap.document.sap_com.test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import functions.rfc.sap.document.sap_com.advance.ZSTR_OA_CONF_YF;
import functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_INTERFACE_02;
import functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_INTERFACE_02Locator;
import functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_INTERFACE_02Stub;

public class AdvanceTest {
	
	public static void main(String[] args) throws ServiceException, RemoteException ,InstantiationException, IllegalAccessException{
		ZWS_FICO_OA_INTERFACE_02 ser=new ZWS_FICO_OA_INTERFACE_02Locator();
		ZWS_FICO_OA_INTERFACE_02Stub stub=(ZWS_FICO_OA_INTERFACE_02Stub)ser.getZWS_FICO_OA_INTERFACE_02();
		
		ZSTR_OA_CONF_YF[] cost=new ZSTR_OA_CONF_YF[1];
		ZSTR_OA_CONF_YF ct=new ZSTR_OA_CONF_YF();
		ct.setBELNR("");
		ct.setBELNR_NEW("");//      凭证号
		ct.setBKTXT("11");//		凭证抬头文本
		ct.setBLART("Z3");//        凭证类型
		ct.setBLDAT("20170713");//		凭证日期
		ct.setBUDAT("20170713");//		过账日期
		ct.setBUKRS("1");//		公司代码
		ct.setDMBTR("11");//	          金额
		ct.setHKONT("11");//		总分类帐帐目 科目代码
		ct.setKOSTL("11");//        成本中心
		ct.setLIFNR("11");//		            供应商或债权人的帐号
		ct.setMESSAGE("");
		ct.setNUMPG("11");//		页数
		ct.setPPNAM("11");//		预制此凭证的用户名
		ct.setPRCTR("11");//		利润中心
		ct.setRSTGR("11");//       付款原因代码  
		ct.setSGTXT("11");//		项目文本
		ct.setUSNAM("11");//		用户名
		ct.setWAERS("CNY");//       货币码
		ct.setXBLNR("11");//		参照
		ct.setXREF1("11");
		ct.setXREF2("11");
		ct.setXREF3("11");
		ct.setYLZD1("11");
		ct.setYLZD2("11");
		ct.setYLZD3("11");
		ct.setYLZD4("11");
		ct.setYLZD5("11");
		ct.setZFI_CONFIRM("");
		ct.setZUONR("");//分配号
		cost[0]=ct;
		ZSTR_OA_CONF_YF[] c=stub.ZWS_FICO_OA_002(cost);
		System.out.println(c);
	}
}
