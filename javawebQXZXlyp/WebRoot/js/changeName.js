// JavaScript Document
function checkName() {
	
	var olduserName = document.getElementById("inputU").value;
	var newUserName = document.getElementById("newInputU").value;
	var reNewUserName = document.getElementById("reNewInputU").value;
	if((newUserName!=""||newUserName != null)&&newUserName===reNewUserName){
		return true;
	}else{
		document.getElementById("msg").innerHTML = "*输入有误,请确认后提交!";
		return false;
	}
}
