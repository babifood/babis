 (function($) {
})(jQuery);

/**
 * _formCustomControls为自定义控件说明数组
 * field为当前表单字段名称
 * ctlTypeFun控件包装是调用的扩展方法
 * params是控件自定义参数，ctlTypeFun中的自定义参数
 * var formCustomControls=[{"field":"field0002","ctlTypeFun":jQuery.fn.ct_defaultValue,"params":{}}];
 */
function initCustomControls(_formCustomControls,_box){
	var $p = $(_box || document);	
	for(var ctl in _formCustomControls){	   
       if (_formCustomControls[ctl].ctlTypeFun)
       _formCustomControls[ctl].ctlTypeFun.call($("span#"+_formCustomControls[ctl].field+"_span",$p),_formCustomControls[ctl].params);   
	
    }
}

