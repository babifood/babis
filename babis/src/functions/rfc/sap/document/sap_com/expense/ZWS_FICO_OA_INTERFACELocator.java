/**
 * ZWS_FICO_OA_INTERFACELocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package functions.rfc.sap.document.sap_com.expense;

public class ZWS_FICO_OA_INTERFACELocator extends org.apache.axis.client.Service implements functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACE {

    public ZWS_FICO_OA_INTERFACELocator() {
    }


    public ZWS_FICO_OA_INTERFACELocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZWS_FICO_OA_INTERFACELocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZWS_FICO_OA_INTERFACE
    private java.lang.String ZWS_FICO_OA_INTERFACE_address = "http://ERPQAS01.babifood.com:8000/sap/bc/srt/rfc/sap/zws_fico_oa_01/600/zws_fico_oa_interface/zws_fico_oa_interface";

    public java.lang.String getZWS_FICO_OA_INTERFACEAddress() {
        return ZWS_FICO_OA_INTERFACE_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ZWS_FICO_OA_INTERFACEWSDDServiceName = "ZWS_FICO_OA_INTERFACE";

    public java.lang.String getZWS_FICO_OA_INTERFACEWSDDServiceName() {
        return ZWS_FICO_OA_INTERFACEWSDDServiceName;
    }

    public void setZWS_FICO_OA_INTERFACEWSDDServiceName(java.lang.String name) {
        ZWS_FICO_OA_INTERFACEWSDDServiceName = name;
    }

    public functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_01 getZWS_FICO_OA_INTERFACE() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZWS_FICO_OA_INTERFACE_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZWS_FICO_OA_INTERFACE(endpoint);
    }

    public functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_01 getZWS_FICO_OA_INTERFACE(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACEStub _stub = new functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACEStub(portAddress, this);
            _stub.setPortName(getZWS_FICO_OA_INTERFACEWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZWS_FICO_OA_INTERFACEEndpointAddress(java.lang.String address) {
        ZWS_FICO_OA_INTERFACE_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_01.class.isAssignableFrom(serviceEndpointInterface)) {
                functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACEStub _stub = new functions.rfc.sap.document.sap_com.expense.ZWS_FICO_OA_INTERFACEStub(new java.net.URL(ZWS_FICO_OA_INTERFACE_address), this);
                _stub.setPortName(getZWS_FICO_OA_INTERFACEWSDDServiceName());
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
        if ("ZWS_FICO_OA_INTERFACE".equals(inputPortName)) {
            return getZWS_FICO_OA_INTERFACE();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZWS_FICO_OA_INTERFACE");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZWS_FICO_OA_INTERFACE"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ZWS_FICO_OA_INTERFACE".equals(portName)) {
            setZWS_FICO_OA_INTERFACEEndpointAddress(address);
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
