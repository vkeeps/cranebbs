$.extend(crane, {
    regs : function() {//验证信息
    	var regs = {
    	    all : {
    	    	reg : /^[\w\u4e00-\u9fa5]+$/, desc : "中文，字母,数字或下划线！"
    	    }, common : {
    	    	reg : /^\w+$/, desc : "字母,数字或下划线！"
    	    }, fangle : {
    	    	reg : /[\uFF00-\uFFFF]/, desc : "全角字符!"
    	    }, vcode : {
    	    	reg : /^\d{4}$/, desc : "4位数字！"
    	    }, email : {
    	    	reg : /^\w[\w\.-]*@[\w-]+(\.[\w-]+)+$/, desc : "邮箱格式！"
    	    }, idcard : {
    	    	reg : /^(\d{15}|\d{17}[\dx])$/, desc : "15或18位身份证号码！"
    	    }, chinese : {
    	    	reg : /^[\u4E00-\u9FAF]+$/, desc : "中文！"
    	    }, truename : {
    	    	reg : /^[\u4E00-\u9FAF]{2,4}$/, desc : "2-4个中文！"
    	    }, english : {
    	    	reg : /^[A-Za-z]+$/, desc : "英文！"
    	    }, date : {
    	    	reg : /^\d{4}-\d{2}-\d{2}$/i, desc : "公历日期(2013-07-06)！"
    	    }, url : {
    	    	reg : /^http(s)?:\/\//i, desc : "URL！"
    	    }, qq : {
    	    	reg : /^[1-9]\d{4,10}$/, desc : "5-11位QQ号！"
    	    }, phone : {
    	    	reg : /^((((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?)|(\d{11}))$/, desc : "电话或手机号码！"
    	    }, mobile : {
    	    	reg : /^(\d{1,4}\-)?(13|15|18){1}\d{9}$/, desc : "手机号码！"
    	    }, symbol : {
    	    	reg : /[`~!@#$%^&*()+=|{}':;',.<>/ ? ~！@#￥ % …… & *（）—— + |{}【】'；：""'。，、？] /, desc : "特殊字符！"
    	    }, password : {
    	    	reg : /^\w+$/, desc : "字母和数字或下划线"
    	    }, ip : {
    	    	reg : /^((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))$/, desc : "IP地址"
    	    }, mac : {
    	    	reg : /[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}/i, desc : "MAC地址"
    	    }, number : {
    	    	reg : /^\d+$/, desc : "数字"
    	    }, unnumber : {
    	    	reg : /\D/, desc : "不为数字"
    	    }, integer : {
    	    	reg : /^-?(0|[1-9]\d*)$/, desc : "整数！"
    	    }, decimal : {
    	    	reg : /^-?[0-9]+\.[0-9]+$/, desc : "小数"
    	    }
    	}
    		return regs;
        },
        /**
         * 自定义ajax请求  crane 二〇一七年三月三日 21:41:39
         */
        ajaxRequest : function(config) {
    		var async = config.asycn = null ? true : config.asycn;
    		var url = config.url;
    		var data = config.data || "";
    		var fun = config.fun;
    		var showLoad = config.showLoad == null ? true : config.showLoad;
    		if (showLoad) {
    		    /*var d = this.tipMsg({
    			type : "loading", content : "加载中......"
    		    });*/
    			var index = layer.load();
    		}
    		var that = this;
    		$.ajax({
    		    async : async, cache : false, type : 'POST', dataType : "json", data : data, url : url,
    		    // 请求的action路径
    		    success : function(response) {
	    			if (showLoad) {
	    			    layer.close(index);
	    			}
	    			if (response.responseCode.code == crane.resultCode.LOGINTIMEOUT) { // 超时
	    			    var url = crane.curUrl;
	    			    that.goLogin();
	    			} else if (response.responseCode.code == crane.resultCode.SUCCESS) { // 请求成功
	    			    fun(response);
	    			} else if(response.responseCode.code == crane.resultCode.MOREMAXLOGINCOUNT){
	    				$("#refreshCheckCode").find("img").attr("src", "checkCode.do?" + new Date().getTime());
	    				fun(response);
	    			}else if (response.responseCode.code == crane.resultCode.NOPERMISSION) { // 没有权限
	    			    /*that.tipMsg({
	    			    	type : "error", content : response.errorMsg, timeout : 3000
	    			    });*/
	    				layer.msg(response.errorMsg, {
						  icon: 2,
						  time: 2000 //2秒关闭（如果不配置，默认是3秒）
						});
	    			} else if (response.responseCode.code == crane.resultCode.CODEERROR) { // 验证码错误
	    				layer.msg(response.errorMsg, {
	  					  icon: 2,
	  					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
						});
	    			    $("#refreshCheckCode").find("img").attr("src", "checkCode.do?" + new Date().getTime());
	    			} else { // 错误
	    				layer.msg(response.errorMsg, {
	  					  icon: 2,
	  					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
						});
	    			    $("#refreshCheckCode").find("img").attr("src", "checkCode.do?" + new Date().getTime());
	    			}
    		    }
    		});
        },
        /**
         * 初始化表单
         */
        initForm : function(formObj) {
	    	// 判断浏览器是否支持 placeholder
	    	if (!placeholderSupport()) {
	    	    formObj.find('[placeholder]').focus(function() {
	    		var input = $(this);
	    		if (input.val() == input.attr('placeholder')) {
	    		    input.val('');
	    		    input.removeClass('placeholder-style');
	    		}
	    		if (input.attr("realType") == "password") {
	    		    input.prop("type", "password");
	    		}
	    	    }).blur(function() {
	    		var input = $(this);
	    		if (input.val() == '' || input.val() == input.attr('placeholder')) {
	    		    if (input.prop("type") == "password") {
	    			input.prop("type", "text");
	    			input.attr("realType", "password")
	    		    }
	    		    input.addClass('placeholder-style');
	    		    input.val(input.attr('placeholder'));
	    		}
	    	    }).blur();
	    	}
        },
        /**
         * 校验表单
         */
        checkForm : function(formObj) {
        	var inputs = formObj.find("input[checkData]");
        	var resutl = true;
        	return inputs;
        },
        /**
         * 提示框，重写layer
         */
        /*tipMsg:function(){
        	
        },*/
        // 获取浏览器版本
        getBrowserType : function() {
        	var browser = navigator.userAgent.indexOf("MSIE") > 0 ? 'IE' : 'others';
        	return browser;
        }
});
