//显示注册高级选项
function showGaojiOption(chk) {
	var gaoji = document.getElementById("gaoji");
	if (chk.checked) {
		gaoji.style.display = "block";
	} else {
		gaoji.style.display = "none";
	}
}

var isSubmitForm = false;
//验证Email
function validateEmail(path) {
	var email_input = document.registerForm.email;
	if (email_input.value == "") {
		document.getElementById("emailMsg").innerHTML="请输入用户账户/邮箱！";
		email_input.focus();
		return;
	}
	var emailok=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var oo=new RegExp(emailok);
	if(!oo.test(email_input.value)){
		document.getElementById("emailMsg").innerHTML="邮箱格式不正确！请重新输入..";
		return false;
	}
	var url =path+"/front/checkEmail";
	var params = "email=" + email_input.value;
	sendRequest("post", url, params, callback);
}

function callback(xmlHttp) {
	var text = xmlHttp.responseText;
	var isValidate = false;
	var emailMsg = document.getElementById("emailMsg");
	if (text == "true") {
		emailMsg.innerHTML = "<span style='color:#f00;'>该邮箱已被其他人注册！</span>";
	} else if (text == "false") {
		emailMsg.innerHTML = "<span style='color:#29D134;'>该邮箱可以使用!</span>";
		isValidate = true;
	} else {
		emailMsg.innerHTML = "<span style='color:#f00;'>请正确填写邮箱地址！</span>";
		document.registerForm.email.value = "";
		document.registerForm.email.focus();
	}

	if (isSubmitForm && isValidate) {
		isSubmitForm = false;
		if (checkForm()) {
			document.registerForm.submit();
		}
	}
}

function submitForm(path) {
	isSubmitForm = true;
	validateEmail(path);
}

function checkForm() {
	var pwd_input = document.registerForm.pwd;
	var repwd_input = document.registerForm.repwd;
	if (pwd_input.value == "") {
		alert("请输入密码！");
		pwd_input.focus();
		return false;
	}
	if (repwd_input.value == "") {
		alert("请输入确认密码！");
		repwd_input.focus();
		return false;
	}
	if (pwd_input.value != repwd_input.value) {
		alert("两次输入的密码不一致，请重新输入！");
		pwd_input.value = "";
		repwd_input.value = "";
		pwd_input.focus();
		return false;
	}
	var chkGaoji = document.registerForm.chkGaoji.checked;
	if (chkGaoji) {
		var name_input = document.registerForm.name;
		if (name_input.value == "") {
			alert("请输入用户名！");
			name_input.focus();
			return false;
		}
		var telphone_input = document.registerForm.telPhone;
		var movePhone_input = document.registerForm.movePhone;
		if (telphone_input.value == "" && movePhone_input.value == "") {
			alert("固定电话与移动电话至少选填一项！");
			telphone_input.focus();
			return false;
		}
		var address_input = document.registerForm.address;
		if (address_input.value == "") {
			alert("请填写收货地址！");
			address_input.focus();
			return false;
		}
	}
	return true;
}
function loginFormCheck() {
	var user = document.loginForm.userName;
	if (user.value == "") {
		document.getElementsByClassName("errorDiv")[0].innerHTML = "用户名不能为空。";
		user.focus();
		return false;
	}
	if (document.loginForm.userPwd.value == "") {
		document.getElementsByClassName("errorDiv")[0].innerHTML = "密码不能为空。";
		return false;
	} else if (document.loginForm.yzm.value == "") {
		document.getElementsByClassName("errorDiv")[0].innerHTML = "验证码不能为空。";
		return false;
	} else {
		return true;
	}

}