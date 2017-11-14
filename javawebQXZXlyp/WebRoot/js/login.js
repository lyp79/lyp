// JavaScript Document
function checkLogin() {
	var userName = document.getElementById("inputU").value;
	var userPass = document.getElementById("inputP").value;
	if(userName!=null&&userPass!=null&&userName!=""&&userPass!=""){
		return true;
	}else if(userName!=null||userName!=""){
		document.getElementById("namemsg").value="用户名不能为空!";
		return false;
	}else if(userPass!=null||userPass!=""){
		document.getElementById("passmsg").value="密码不能为空!";
		return false;
	}
	
}
if(top!=self){
	top.location.href="../login.jsp";
}