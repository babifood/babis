<%@tag import="com.seeyon.shbb.utils.CoreUtils"%>
<%@tag import="com.seeyon.shbb.utils.PropertySearchFilter"%>
<%@tag import="java.util.Map"%>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shbb" tagdir="/WEB-INF/tags" %>
<%
	Map<String, PropertySearchFilter> filterParams=CoreUtils.getParametersStartingWith(request);
	if(!filterParams.isEmpty()){
		for (Map.Entry<String, PropertySearchFilter> entry : filterParams.entrySet()) {
			PropertySearchFilter value=entry.getValue();
			String pram="gridPramsObj."+value.fieldName+"={\"fieldName\":\""+value.fieldName+"\",\"fieldValue\":\""+value.value+"\",\"operation\":\""+value.getOperatorExp()+"\"}"; 
			out.println(pram);
		}
	}	 
%>

