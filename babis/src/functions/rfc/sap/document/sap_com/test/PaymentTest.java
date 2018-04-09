package functions.rfc.sap.document.sap_com.test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import functions.rfc.sap.document.sap_com.payment.ZSTR_OA_CONF_YF;
import functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03;
import functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Locator;
import functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Stub;




public class PaymentTest {
	public static void main(String[] args) throws ServiceException, RemoteException {
		
		ZWS_FICO_OA_INTERFACE_03 ser = new ZWS_FICO_OA_INTERFACE_03Locator();
		ZWS_FICO_OA_INTERFACE_03Stub stub = (ZWS_FICO_OA_INTERFACE_03Stub) ser.getZWS_FICO_OA_INTERFACE_03();
		
		ZSTR_OA_CONF_YF[] conf=new ZSTR_OA_CONF_YF[1];
		
		ZSTR_OA_CONF_YF yf=new ZSTR_OA_CONF_YF();
		
		yf.setBUKRS("");//		公司代码
		yf.setBUDAT("");//		过账日期
		yf.setBLDAT("");//		凭证日期
		yf.setBLART("");//        凭证类型
		yf.setXBLNR("");//		参照
		yf.setBKTXT("");//		凭证抬头文本
		yf.setNUMPG("");//		页数
		yf.setUSNAM("");//		用户名
		yf.setPPNAM("");//		预制此凭证的用户名
		yf.setLIFNR("");//		            供应商或债权人的帐号
		yf.setBELNR("");
		yf.setZFI_CONFIRM("");
		yf.setHKONT("");//		总分类帐帐目 科目代码
		yf.setRSTGR("");//       付款原因代码  
		yf.setSGTXT("");//		项目文本
		yf.setZUONR("");
		yf.setKOSTL("");//        成本中心
		yf.setPRCTR("");//		利润中心
		yf.setDMBTR("");//	          金额
		yf.setWAERS("");//       货币码
		yf.setXREF1("");
		yf.setXREF2("");
		yf.setXREF3("");
		yf.setYLZD1("");
		yf.setYLZD2("");
		yf.setYLZD3("");
		yf.setYLZD4("");
		yf.setYLZD5("");
		yf.setBELNR_NEW("");//凭证号
		yf.setMESSAGE("");
		conf[0]=yf;
		ZSTR_OA_CONF_YF[] oa=stub.ZWS_FICO_OA_003(conf);
		System.out.println(oa);
	}
}
