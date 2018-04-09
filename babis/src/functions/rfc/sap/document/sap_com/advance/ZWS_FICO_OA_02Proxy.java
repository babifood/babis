package functions.rfc.sap.document.sap_com.advance;

public class ZWS_FICO_OA_02Proxy implements functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_02 {
  private String _endpoint = null;
  private functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_02 zWS_FICO_OA_02 = null;
  
  public ZWS_FICO_OA_02Proxy() {
    _initZWS_FICO_OA_02Proxy();
  }
  
  public ZWS_FICO_OA_02Proxy(String endpoint) {
    _endpoint = endpoint;
    _initZWS_FICO_OA_02Proxy();
  }
  
  private void _initZWS_FICO_OA_02Proxy() {
    try {
      zWS_FICO_OA_02 = (new functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_INTERFACE_02Locator()).getZWS_FICO_OA_INTERFACE_02();
      if (zWS_FICO_OA_02 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zWS_FICO_OA_02)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zWS_FICO_OA_02)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zWS_FICO_OA_02 != null)
      ((javax.xml.rpc.Stub)zWS_FICO_OA_02)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public functions.rfc.sap.document.sap_com.advance.ZWS_FICO_OA_02 getZWS_FICO_OA_02() {
    if (zWS_FICO_OA_02 == null)
      _initZWS_FICO_OA_02Proxy();
    return zWS_FICO_OA_02;
  }
  
  public functions.rfc.sap.document.sap_com.advance.ZSTR_OA_CONF_YF[] ZWS_FICO_OA_002(functions.rfc.sap.document.sap_com.advance.ZSTR_OA_CONF_YF[] IT_CONF) throws java.rmi.RemoteException{
    if (zWS_FICO_OA_02 == null)
      _initZWS_FICO_OA_02Proxy();
    return zWS_FICO_OA_02.ZWS_FICO_OA_002(IT_CONF);
  }
  
  
}