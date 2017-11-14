// JavaScript Document
function search() {
	var keywords = document.getElementById("keywords");
	if (keywords.value != "" && keywords.value != null) {
		document.getElementById("op").value = "query";
		return true;
	} else {
		document.getElementById("op").value = "manage";
		return true;
	}
}
function delclick() {
	var checkAll = document.getElementById("checkAll");
	if (checkAll.checked == false) {
		document.getElementById("msg").value = "请选择需要删除的数据!";
		return;
	}
	var checks = document.getElementsByName("checks");
	var dels = new Array();
	for (var i = 0; i < checks.length; i++) {
		if (checks[i].checked == true) {
			dels.push(checks[i].value);
		}
	}
	
	if (confirm("确认删除选择的信息?")) {
		document.getElementById("op").value = "delcheck";
		document.getElementById("cleckid").value = dels.toString();
		document.myForm.submit();
	}

}

//选中一个
function clickid(str) {
	var ckall = document.getElementById("checkAll");
	var checks = document.getElementsByName("checks");
	if (str.checked == true) {
		if (ckall.checked == false) {
			ckall.checked = true;
		}
	} else {
		for (var i = 0; i < checks.length; i++) {
			//判断是否还有选中的选项
			if (checks[i].checked == true) {
				return;
			}
		}
		//都没有选中的，取消全选的选中。
		ckall.checked = false;
	}
}

//全选/全不选
function all_check() {
	var checkAll = document.getElementById("checkAll");
	var checks = document.getElementsByName("checks");
	for (var i = 0; i < checks.length; i++) {
		if(checks[i].disabled==true){continue}
		checks[i].checked = checkAll.checked;
	}
}