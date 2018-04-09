<%@page import="com.seeyon.ctp.common.AppContext"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ctp" uri="http://www.seeyon.com/ctp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

/*
基础数据抛送
*/
function functionBaseSap(){

	if(confirm('您确定需要物料基础数据发送至SAP?')){
		var list =[];
	    var $allCombox = $('#mytable input:checked');
	    $allCombox.each(function(i){
	    	list[i] = $(this).val();
	    });
	    if(list.length==0){
		   /* if(!confirm("没有勾选数据行将抛送所有状态为'未报送'的基础数据到SAP,确定抛送全部数据吗")){
		    	return ;//不抛送
		    }
		   */
		   alert("请选择一条数据！");
		   return ;
	    }else if(list.length>1){
		  alert("只能选取一条数据！");
		  return ;
		}
		var callback=(function(result){
		  if(result=="no"){
		   alert("该条数据已产生凭证号，请不要重复拋送！");
		   return ;
		  }else{
		    $.alert("发送完毕,请查看结果状态!");
		  }
		});
	    var url='${path}/shbbSapController.do?method=sendBasicData';
	    SEEYON_UTILS.loadUrl(url, {"ids":list}, 'POST', true,callback);	   
	    
	}
}

jQuery(document).ready(function(){
	var buttonStr = '<a id="mb_99_a" href="javascript:{btnAction}">' + 
  				  '<em id="mb_99_em" class="ico16 view_log_16"></em>' + 
  				  '<span id="mb_99_span" class="menu_span" title="{btnName}">{btnName}</span></a>';
	//如果要限制到应用绑定，则新增用param.templateId  保存待发用param.moduleTemplateId 校验
	<%--alert("${param.formId}--${param.moduleTemplateId}--${param.templateId}"); --%>
	<c:if test="${param.formId==5822742137875353443}">
      var buttonStr=buttonStr.replaceAll('{btnName}', '抛SAP凭证').replaceAll('{btnAction}','functionBaseSap()');
      $("#toolbar .common_toolbar_box .toolbar_l div").append(buttonStr);
    </c:if>
});