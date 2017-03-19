$(function(){
	crane.url={regist:"regist.do",checkUserName:"checkUserName.do"};
	$(".banner-menu ul li").removeClass("active");
	//crang.initForm(); bootstrap好像已经处理过了
	//获得焦点
	$("input[name=userName]").focus();
	//帐号单独校验处理
	$("input[name=userName]").focusout(function(){
		var accountObj = $("input[name=userName]").eq(0);
		checkUserName(accountObj);
		
	});
	//刷新验证码
	$("#refreshCheckCode").click(function() {
		$(this).find("img").attr("src", "checkCode?" + new Date());
    });
	$(".regist-btn").on("click",function(){
		if($("#agree").is(":checked")){
			regist();
		}else{
			layer.msg("请同意修仙吧协议");
		}
		//regist();
	});
	$(document).keyup(function(event){
		var code = event.keyCode();
		if(code==13){
			//regist();
		}
	});
	function regist(){
		var formObj = $("form#registForm").eq(0);
		//var result = crane.checkForm(formObj);
		//alert(formObj.find("input[name='checkCode']").val());
		
	}
	function checkUserName(userNameObj){
		var inputUserName = $.trim(userNameObj.val());
		//获取栅格元素
		var objParent = userNameObj.parent();
		/*if(objParent.find(".regist-loading").length==0){
			$("<div class='regist-loading'><i class='fa fa-spinner fa-pulse'></i></div>").appendTo(objParent);
		};*/
		crane.setLoading(objParent);
		if(inputUserName==""||inputUserName==null){
			crane.setError(objParent,"帐号不能为空");
			//userNameObj.focus();
		}else if(inputUserName==userNameObj.attr("placeholder")){
			crane.setError(objParent,"请输入符合规则的用户名");
			//userNameObj.focus();
		}else if(!crane.regs().all.reg.test(inputUserName)){
			crane.setError(objParent,"帐号长度1-20位字符，中文、数字、_ 、字母");
		}else{
			crane.ajaxRequest({
				async : true,url :crane.url.checkUserName,data:userNameObj.serialize(),showLoad:false,fun:function(res){
					//userNameObj.parent().parent().find(".regist-loading").remove();
					crane.setCurrent(objParent);
				}
			});
		}
	}
});
