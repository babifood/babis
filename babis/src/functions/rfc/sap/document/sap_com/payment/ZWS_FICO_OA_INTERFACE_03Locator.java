/**
 * ZWS_FICO_OA_INTERFACE_03Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package functions.rfc.sap.document.sap_com.payment;

public class ZWS_FICO_OA_INTERFACE_03Locator extends org.apache.axis.client.Service implements functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03 {

    public ZWS_FICO_OA_INTERFACE_03Locator() {
    }


    public ZWS_FICO_OA_INTERFACE_03Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZWS_FICO_OA_INTERFACE_03Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZWS_FICO_OA_INTERFACE_03
    private java.lang.String ZWS_FICO_OA_INTERFACE_03_address = "http://ERPQAS01.babifood.com:8000/sap/bc/srt/rfc/sap/zws_fico_oa_03/600/zws_fico_oa_interface_03/zws_fico_oa_interface_03";

    public java.lang.String getZWS_FICO_OA_INTERFACE_03Address() {
        return ZWS_FICO_OA_INTERFACE_03_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ZWS_FICO_OA_INTERFACE_03WSDDServiceName = "ZWS_FICO_OA_INTERFACE_03";

    public java.lang.String getZWS_FICO_OA_INTERFACE_03WSDDServiceName() {
        return ZWS_FICO_OA_INTERFACE_03WSDDServiceName;
    }

    public void setZWS_FICO_OA_INTERFACE_03WSDDServiceName(java.lang.String name) {
        ZWS_FICO_OA_INTERFACE_03WSDDServiceName = name;
    }

    public functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_03 getZWS_FICO_OA_INTERFACE_03() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZWS_FICO_OA_INTERFACE_03_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZWS_FICO_OA_INTERFACE_03(endpoint);
    }

    public functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_03 getZWS_FICO_OA_INTERFACE_03(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Stub _stub = new functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Stub(portAddress, this);
            _stub.setPortName(getZWS_FICO_OA_INTERFACE_03WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZWS_FICO_OA_INTERFACE_03EndpointAddress(java.lang.String address) {
        ZWS_FICO_OA_INTERFACE_03_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_03.class.isAssignableFrom(serviceEndpointInterface)) {
                functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Stub _stub = new functions.rfc.sap.document.sap_com.payment.ZWS_FICO_OA_INTERFACE_03Stub(new java.net.URL(ZWS_FICO_OA_INTERFACE_03_address), this);
                _stub.setPortName(getZWS_FICO_OA_INTERFACE_03WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ZWS_FICO_OA_INTERFACE_03".equals(inputPortName)) {
            return getZWS_FICO_OA_INTERFACE_03();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZWS_FICO_OA_INTERFACE_03");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZWS_FICO_OA_INTERFACE_03"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ZWS_FICO_OA_INTERFACE_03".equals(portName)) {
            setZWS_FICO_OA_INTERFACE_03EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
