$(function(){
	crane.url={regist:"regist.do",checkUserName:"checkUserName.do",checkEmail:"checkEmail.do"};
	$(".banner-menu ul li").removeClass("active");
	//crang.initForm(); bootstrap好像已经处理过了
	//获得焦点
	$("input[name=userName]").focus();
	//帐号单独校验处理
	$("input[name=userName]").focusout(function(){
		var $accountObj = $("input[name=userName]").eq(0);
		checkUserName($accountObj);
	});
	//邮箱单独校验处理
	$("input[name=email]").focusout(function(){
		var $emailObj = $("input[name=email]").eq(0);
		checkEmail($emailObj);
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
	
});
function regist(){
	var $formObj = $("#registForm").eq(0);
	var $emailObj = $("input[name=email]").eq(0);
	var $accountObj = $("input[name=userName]").eq(0);
	//var result = crane.checkForm(formObj);
	//alert(formObj.find("input[name='checkCode']").val());
	var password = $.trim($formObj.find("input[name='password']").val());
	var rePassword = $.trim($formObj.find("input[name='rePassword']").val());
	if(null==password||""==password){
		crane.setError($formObj.find("input[name='password']").parent(),"密码不能为空");
		return;
	}else if(password.length<6||password.length>=20||!crane.regs().common.reg.test(password)){
		crane.setError($formObj.find("input[name='password']").parent(),"密码由字母+数字组成,6-20位");
		return;
	}else if(password!=rePassword){
		crane.setError($formObj.find("input[name='password']").parent(),"两次输入密码不一致");
		crane.setError($formObj.find("input[name='rePassword']").parent(),"两次输入密码不一致");
		return;
	}else{
		crane.setCurrent($formObj.find("input[name='password']").parent());
		crane.setCurrent($formObj.find("input[name='rePassword']").parent());
	}
	if(checkUserName($accountObj)&&checkEmail($emailObj)){
		crane.ajaxRequest({
			async : true,url :crane.url.regist,data:$formObj.serialize(),showLoad:true,fun:function(res){
				layer.msg("注册成功,正在前往个人主页...",{
					icon:1,
					time:2000
				},function(){
					document.location.href = crane.absolutePath + "/" ;
				});
			}
		});
	}
};
function checkUserName(userNameObj){
	var result = true;
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
		result=false;
	}else if(inputUserName==userNameObj.attr("placeholder")){
		crane.setError(objParent,"请输入符合规则的用户名");
		//userNameObj.focus();
		result=false;
	}else if(!crane.regs().all.reg.test(inputUserName)){
		crane.setError(objParent,"帐号长度1-20位字符，中文、数字、_ 、字母");
		result=false;
	}else{
		crane.ajaxRequest({
			async : true,url :crane.url.checkUserName,data:userNameObj.serialize(),obj:objParent,showLoad:false,fun:function(res){
				//userNameObj.parent().parent().find(".regist-loading").remove();
				crane.setCurrent(objParent);
				result=true;
			}
		});
	}
	return result;
};
function checkEmail(emailObj){
	var result = true;
	var inputEmail = $.trim(emailObj.val());
	//获取栅格元素
	var objParent = emailObj.parent().parent();
	crane.setLoading(objParent);
	if(inputEmail==""||inputEmail==null){
		crane.setError(objParent,"邮箱不能为空");
		result=false;
	}else if(inputEmail==emailObj.attr("placeholder")){
		crane.setError(objParent,"请输入正确的邮箱,例如:xx@xx.com");
		result=false;
	}else if(!crane.regs().email.reg.test(inputEmail)){
		crane.setError(objParent,"请输入正确的邮箱,例如:xx@xx.com");
		result=false;
	}else{
		crane.ajaxRequest({
			async : true,url :crane.url.checkEmail,data:emailObj.serialize(),obj:objParent,showLoad:false,fun:function(res){
				crane.setCurrent(objParent);
				result=true;
			}
		});
	}
	return result;
}
