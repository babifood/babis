package functions.rfc.sap.document.sap_com.payment;

public class ZWS_FICO_OA_03Proxy implements functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_03 {
  private String _endpoint = null;
  private functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_03 zWS_FICO_OA_03 = null;
  
  public ZWS_FICO_OA_03Proxy() {
    _initZWS_FICO_OA_03Proxy();
  }
  
  public ZWS_FICO_OA_03Proxy(String endpoint) {
    _endpoint = endpoint;
    _initZWS_FICO_OA_03Proxy();
  }
  
  private void _initZWS_FICO_OA_03Proxy() {
    try {
      zWS_FICO_OA_03 = (new functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Locator()).getZWS_FICO_OA_INTERFACE_03();
      if (zWS_FICO_OA_03 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zWS_FICO_OA_03)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zWS_FICO_OA_03)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zWS_FICO_OA_03 != null)
      ((javax.xml.rpc.Stub)zWS_FICO_OA_03)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_03 getZWS_FICO_OA_03() {
    if (zWS_FICO_OA_03 == null)
      _initZWS_FICO_OA_03Proxy();
    return zWS_FICO_OA_03;
  }
  
  public functions.rfc.sap.document.sap_com.payment.ZSTR_OA_CONF_YF[] ZWS_FICO_OA_003(functions.rfc.sap.document.sap_com.payment.ZSTR_OA_CONF_YF[] IT_CONF) throws java.rmi.RemoteException{
    if (zWS_FICO_OA_03 == null)
      _initZWS_FICO_OA_03Proxy();
    return zWS_FICO_OA_03.ZWS_FICO_OA_003(IT_CONF);
  }
  
  
}