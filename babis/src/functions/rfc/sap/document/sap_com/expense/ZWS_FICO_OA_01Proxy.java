package functions.rfc.sap.document.sap_com.expense;

public class ZWS_FICO_OA_01Proxy implements functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_01 {
  private String _endpoint = null;
  private functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_01 zWS_FICO_OA_01 = null;
  
  public ZWS_FICO_OA_01Proxy() {
    _initZWS_FICO_OA_01Proxy();
  }
  
  public ZWS_FICO_OA_01Proxy(String endpoint) {
    _endpoint = endpoint;
    _initZWS_FICO_OA_01Proxy();
  }
  
  private void _initZWS_FICO_OA_01Proxy() {
    try {
      zWS_FICO_OA_01 = (new functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACELocator()).getZWS_FICO_OA_INTERFACE();
      if (zWS_FICO_OA_01 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zWS_FICO_OA_01)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zWS_FICO_OA_01)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zWS_FICO_OA_01 != null)
      ((javax.xml.rpc.Stub)zWS_FICO_OA_01)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_01 getZWS_FICO_OA_01() {
    if (zWS_FICO_OA_01 == null)
      _initZWS_FICO_OA_01Proxy();
    return zWS_FICO_OA_01;
  }
  
  public functions.rfc.sap.document.sap_com.expense.ZSTR_OA_COST[] ZWS_FICO_OA_001(functions.rfc.sap.document.sap_com.expense.ZSTR_OA_COST[] IT_COST) throws java.rmi.RemoteException{
    if (zWS_FICO_OA_01 == null)
      _initZWS_FICO_OA_01Proxy();
    return zWS_FICO_OA_01.ZWS_FICO_OA_001(IT_COST);
  }
  
  
}