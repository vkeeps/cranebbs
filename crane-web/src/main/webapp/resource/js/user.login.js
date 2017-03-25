$(function(){
	var url = crane.curUrl;
	if (url.indexOf("redirect") != -1) {
		crane.redirect = url.substring(url.indexOf("redirect") + 9, url.length);
	}
	//crane.initForm($("#loginForm").eq(0));
	crane.url = {
		login : "login.do"
	};
	var $formObj = $("#loginForm").eq(0);
	$("#loginModal").on("show.bs.modal",function(){
		$("#loginPassword").val('');
		$("#inputCheckCode").val('');
		$("#refreshCheckCode").find("img").attr("src", "checkCode?" + new Date());
	});
	$("#loginModal").on("shown.bs.modal",function(){
		$formObj.find("#loginUserName").focus();
	});
	$("#loginBtn").click(function(){
		//$(this).button('loading').delay(500).queue(function(){
			login($formObj);
		//});
		//$(this).button('reset');
	});
	$(document).on("click", "#refreshCheckCode", function () {
        $(this).find("img").attr("src", "checkCode?" + new Date());
    });
});
function login(obj){
	crane.ajaxRequest({
		async : true,url :crane.url.login,data:obj.serialize(),showLoad:true,fun:function(res){
			if(res.responseCode.code==crane.resultCode.MOREMAXLOGINCOUNT){
				layer.msg(res.errorMsg, {
  					  icon: 2,
  					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
				});
				if ($("#checkCode-area").children().length == 0) {
					$("<label for='loginCheckCode' class='col-sm-3 control-label fa-label'><i class='fa fa-lock fa-medium'></i></label>").appendTo($("#checkCode-area"));
					$("<div class='col-sm-3'><input type='text' class='form-control' id='loginCheckCode' name='checkCode' placeholder='请输入验证码' checkData='{rq:true}'></div>").appendTo($("#checkCode-area"));
					$("<div class='col-sm-4 checkCode'><a href='javascript:void(0)' id='refreshCheckCode'><img src='checkCode'></a></div>").appendTo($("#checkCode-area"));
				}
			}else if(res.responseCode.code == crane.resultCode.SUCCESS){
				layer.msg("登录成功",{
					icon:1,
					time:1000
				},function(){
					document.location.href = crane.absolutePath + "/" ;
				});
			}
		}
	});
}