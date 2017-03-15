$(function(){
	crane.url={regist:"regist.do"};
	$(".banner-menu ul li").removeClass("active");
	//crang.initForm(); bootstrap好像已经处理过了
	
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
			regist();
		}
	});
	$("#inputUserName").focusout(function(){
		crane.ajaxRequest({
			
		});
	});
});
function regist(){
	var formObj = $("form#registForm").eq(0);
	var result = ulewo.checkForm(formObj);
}
