// JavaScript Document
function checkPass() {
	var oldUserPass = document.getElementById("oinputP").value;
	var newUserPass = document.getElementById("ninputP").value;
	var reNewUserPass = document.getElementById("reninputP").value;
	if((oldUserPass!=""||oldUserPass!=null)&&newUserPass===reNewUserPass){
		return true;
	}else{
		document.getElementById("msg").innerHTML = "*输入有误,请确认后提交!";
		return false;
	}
}
