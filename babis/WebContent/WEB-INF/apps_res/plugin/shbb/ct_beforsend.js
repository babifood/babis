var SEEYON_UTILS = {	
	statusCode: {ok:200, error:300, timeout:301},
	jsonEval : function(data) {
		try {
			if ($.type(data) == 'string')
				return eval('(' + data + ')');
			else
				return data;
		} catch (e) {
			return {};
		}
	},
	loadUrl : function(url, data, type, async, callback) {
		$.ajax({
			type : type || 'GET',
			url : url,
			data : data,
			async : async,
			cache : false,
			success : function(response) {
				callback(response);
			}

		});

	},ajaxError:function(xhr, ajaxOptions, thrownError){
	 
		alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:"+thrownError + "\n" +xhr.responseText);
		 
	},
	ajaxDone:function(json){
		if (json.statusCode === undefined && json.message === undefined) { // for iframeCallback
			return alert(json);
		} 
		if(json.statusCode == SEEYON_UTILS.statusCode.error) {
			if(json.message) alert(json.message);
		} else {
			if(json.message) alert(json.message);
		};
	},
	isIntegerVal:function(obj) {
		//判断给定数值是否是一个整数 
        //return typeof obj === 'number' && (obj%1 === 0); //整数除1无余数
        return Math.floor(obj) === obj; //另一种判断方法,整数取整后还是等于自己
	},
	isElementNotEmpty:function(element){
		return $(element).length>0;
	},
	elementValue: function( element ) {
			var type = $(element).attr('type'),
				val = $(element).val();

			if ( type === 'radio' || type === 'checkbox' ) {
				return $('input[name="' + $(element).attr('name') + '"]:checked').val();
			}

			if ( typeof val === 'string' ) {
				return val.replace(/\r/g, "");
			}
			return val;
	},
	getTextCtlValue:function(ctlId,_box){
		var $p = $(_box || document);	
		var $ctl =$("span#"+ctlId+"_span",$p);
		var $input=$ctl.find("input#"+ctlId);
		var _val="";
		if($input.length!==0){
		   _val=$input.val();							   
		}else{
		   var _fieldval=$ctl.attr("fieldval");
		   _val=SEEYON_UTILS.jsonEval(_fieldval).value;
		}
		return _val;
	 },
	 getSelectCtlValue:function(ctlId,_box){
			var $p = $(_box || document);	
			var $ctl =$("span#"+ctlId+"_span",$p);
			var $input=$ctl.find("select#"+ctlId);
			var _val="";
			if($input.length!==0){
			   _val=$input.val();							   
			}else{
			   var _fieldval=$ctl.attr("fieldval");
			   _val=SEEYON_UTILS.jsonEval(_fieldval).value;
			}
			return _val;
	},
	leftPad:function(num, n) {
		 var len = num.toString().length;
		 while(len < n) {
			 num = "0" + num;
			 len++;
		 }
		 return num;
	}
};


$.ctp={
    bind : function(eventName, func) {		
        var listeners = ctpEventIntercept[eventName];
        if (!Boolean(listeners)) {
            listeners = [];
            listeners.push(func);
            ctpEventIntercept[eventName] = listeners;
        } else {
            listeners.push(func);
        }
    },
    trigger : function(eventName) {		 
        var listeners = ctpEventIntercept[eventName];
        if (Boolean(listeners)) {
            for (var i = 0; i < listeners.length; i++) {	
				var returnOk=listeners[i]();
                if (!returnOk) {
                    return false;
                }
            }
        }  
        
		return true;
       
    }
};
