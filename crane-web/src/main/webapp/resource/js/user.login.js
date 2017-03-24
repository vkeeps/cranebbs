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
	$("#loginModal").on("shown.bs.modal",function(){
		$formObj.find("#loginUserName").focus();
		$formObj.find("#loginCheckCode").attr("src", "checkCode?" + new Date());
	});
	$("#loginBtn").click(function(){
		login($formObj);
	});
	
});
function login(obj){
	alert(obj);
}