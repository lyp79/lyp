//改变指定元素的背景颜色和边框
function changebg_hover(obj){
	obj.style.backgroundColor="#FFF3D9";
	obj.style.border="1px solid #E37A08";
}
//改变指定元素的背景颜色和边框
function changebg_out(obj){
	obj.style.backgroundColor="#E5EAF0";
	obj.style.border="1px solid #98BFF0";
}
//改变指定元素的背景颜色和边框
function changebg(obj,bgColor,border){
	obj.style.backgroundColor=bgColor;
	obj.style.border=border;
}

//表单验证
function submit_form(){
	var num = document.goodsForm.num.value;
	if(num==""){
		alert("商品数量不能为空！");
		return false;
	}
	
	var pattern = /^[1-9]\d*$/;
	if(!pattern.test(num)){
		alert("商品数量应该是大于等于0的整数！");
		return false;
	}
	return true;
}

//让商品图片居中显示
window.onload=function(){
	var goodsPhoto = document.getElementById("goodsPhoto");
	goodsPhoto.style.position="relative";
	goodsPhoto.style.top=(155-parseInt(goodsPhoto.height)/2)+"px";
	goodsPhoto.style.left=(155-parseInt(goodsPhoto.width)/2)+"px";
}