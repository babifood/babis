(function($) {
	// 前台新增批量检查
	jQuery.fn.ct_checkAsset = function(options, resp) {
		var op = $.extend({
			"url" : "",
			"assetCtlId" : ""
		}, options);
		var list = [];
		$("input#" + op.assetCtlId).each(function() {
			var $this = $(this);
			var id = $this.val();
			if (id != "") {
				list.push(id);
			}
		});
		if (list.length == 0) {
			return resp;
		}
		var success = function(resp) {
			var json = SEEYON_UTILS.jsonEval(resp);
			if (json.statusCode != SEEYON_UTILS.statusCode.ok) {
				resp.isOk = false;
				resp.message = json.message;
				return resp;
			}
		};
		SEEYON_UTILS.loadUrl(op.url, {
			"ids" : list
		}, 'POST', false, success);
	};
 
	// 单个资产变更检查.
	jQuery.fn.ct_checkAssetChangeSingle = function(options, resp) {
		var op = $.extend({
			"url" : "",
			"assetCtlId" : ""
		}, options);
		var map = {};
		map["moduleId"]=$("#moduleId").val();
		map["field0063"] = SEEYON_UTILS.getTextCtlValue("field0063");// 接收人
		map["field0064"]= SEEYON_UTILS.getTextCtlValue("field0064");//接收人姓名
		map["field0065"]= SEEYON_UTILS.getTextCtlValue("field0065");//接收人工号

		map["field0021"]= SEEYON_UTILS.getTextCtlValue("field0021");// 新使用部门

		map["field0024"] = SEEYON_UTILS.getSelectCtlValue("field0024");//使用状况

		map["field0049"] = SEEYON_UTILS.getSelectCtlValue("field0049");//变更方式

		map["field0027"]= SEEYON_UTILS.getTextCtlValue("field0027");//存放地点
		map["field0026"] = SEEYON_UTILS.getTextCtlValue("field0026");//转移单号
		
		var success = function(resp) {
			alert(resp);
			var json = SEEYON_UTILS.jsonEval(resp);
			$.extend(data, json.data);
		};
		SEEYON_UTILS.loadUrl(_ctxPath + op.url, map, 'POST', false, success);

	};

})(jQuery);

/**
 * __formCustomValidationControls为自定义验证控件说明数组 elementId为当前要验证的表单字段名称
 * isEnableFun为判断当前控件是否验证生效的依据 validationFun控件包装是调用的扩展验证方法
 * params是控件自定义参数，必须包含messageKey用于错误提示,validationFun中的自定义参数 var
 * formCustomValidationControls=[{"elementId":"field0002","isEnableFun":"","validationFun":jQuery.fn.vl_defaultValue,"params":{"messageKey":""}];
 */

function validateCustomerExt(obj, param) {
	if (!param.checkNull) {
		// 该标识可能表示当前的验证为非提交或发送触发
		return true;
	}
	var _formCustomValidationControls = $(document).data(
			"formCustomValidationControls");
	var resp = {
		"isOk" : true,
		"message" : ""
	};
	for ( var ctl in _formCustomValidationControls) {

		var $this = $(_formCustomValidationControls[ctl].elementId);
		if ($this.length == 0) {
			continue;
		}
		if (_formCustomValidationControls[ctl].isEnableFun) {
			if (jQuery
					.isFunction(_formCustomValidationControls[ctl].isEnableFun)) {
				var _isEnable = _formCustomValidationControls[ctl].isEnableFun
						.call($this, _formCustomValidationControls[ctl].params);
				if (!_isEnable) {
					continue;
				}
			} else {
				if ($(_formCustomValidationControls[ctl].isEnableFun).length == 0) {
					continue;
				}
			}
		}

		if (_formCustomValidationControls[ctl].validationFun) {

			_formCustomValidationControls[ctl].validationFun.call($this,
					_formCustomValidationControls[ctl].params, resp);

			if (!resp.isOk) {
				// 任意方法未通过则直接显示未通过
				param['errorMsg'] = resp.message;
				return false;
			}
		}

	}

	return resp.isOk;

}

function additionalValidationAttributes(elementFormId) {
	$("#" + elementFormId)
			.append(
					"<span style=\"display:none\"><input name=\"ctx_validation\" type=\"hidden\" class=\"validate\" validate=\"type:'string',func:validateCustomerExt\"/><span>");
}
