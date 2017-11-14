// JavaScript Document
function changeMemu(imgBtn){
	var left_div=document.getElementById("cont_left");
	var right_div=document.getElementById("cont_right");
	if(left_div!=null&&right_div!=null){
		if(left_div.style.display=="block"||left_div.style.display==""){
			left_div.style.display="none";
			right_div.style.width="1180px";
			imgBtn.src="../img/open_left.gif";
			imgBtn.title="展开左侧";
		}else{
			left_div.style.display="block";
			right_div.style.width="960px";
			imgBtn.src="../img/close_left.gif";
			imgBtn.title="折叠左侧";
		}
	}
	
}