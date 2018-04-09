package functions.rfc.sap.document.sap_com.test;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import functions.rfc.sap.document.sap_com.expense.ZSTR_OA_COST;
import functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACE;
import functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACELocator;
import functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACEStub;



/**
 * 
 * @ClassName：ExpenseTest
 * @Description： 费用报销类测试
 * @Author：AmberK
 * @CreateTime：2017年7月10日 上午11:44:41
 *
 */
public class ExpenseTest {



	public static void main(String[] args) throws RemoteException, ServiceException, InstantiationException, IllegalAccessException {
		
		
		ZWS_FICO_OA_INTERFACE ser = new ZWS_FICO_OA_INTERFACELocator();
		ZWS_FICO_OA_INTERFACEStub stub = (ZWS_FICO_OA_INTERFACEStub) ser.getZWS_FICO_OA_INTERFACE();
		
		ZSTR_OA_COST[] cost = new ZSTR_OA_COST[1];
		
		ZSTR_OA_COST ct = new ZSTR_OA_COST();
		ct.setANBWA("100");
		ct.setBLART("Z3");
		ct.setBUKRS("1");
		ct.setBUDAT("20170713");
		ct.setBLDAT("20170713");
		ct.setXBLNR("11");
		ct.setBKTXT("11");
		ct.setUSNAM("11");
		ct.setPPNAM("11");
		ct.setNUMPG("11");
		ct.setXBLNR_ALT("11");
		ct.setXREF1_HD("11");
		ct.setXREF2_HD("11");
		ct.setYLZD1H("11");
		ct.setYLZD2H("11");
		ct.setITEMNO("11");
		ct.setBSCHL("01");
		ct.setUMSKZ("1");
		ct.setHKONT("11");
		ct.setSGTXT("11");
		ct.setKUNNR("11");
		ct.setLIFNR("11");
		ct.setZUONR("11");
		ct.setANLN1("11");
		ct.setANLN2("11");
		ct.setEBELN("11");
		ct.setEBELP("11");
		ct.setKOSTL("11");
		ct.setPRCTR("11");
		ct.setAUFNR("11");
		ct.setSEGMENT("11");
		ct.setGSBER("11");
		ct.setFKBER_SHORT("11");
		ct.setMATNR("11");
		ct.setWERKS("11");
		ct.setMEINS("11");
		ct.setMENGE("11");
		ct.setDMBTR("11");
		ct.setWRBTR("11");
		ct.setWAERS("CNY");
		ct.setRSTGR("11");
		ct.setXREF1("11");
		ct.setXREF2("11");
		ct.setXREF2("11");
		ct.setXREF3("11");
		ct.setYLZD1("11");
		ct.setYLZD2("11");
		ct.setYLZD3("11");
		ct.setYLZD4("11");
		ct.setYLZD5("11");
		ct.setBELNR("11");
		ct.setGJAHR("11");
		ct.setMESSAGE("11");
		cost[0] = ct;		
		ZSTR_OA_COST[] c = stub.ZWS_FICO_OA_001(cost);
		System.out.println(c);
		
		
		
		
	}
}