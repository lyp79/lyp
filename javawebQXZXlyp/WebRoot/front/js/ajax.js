/**
 * 创建XMLHttpRequest对象
 */
function createXMLHttpRequest() {
	var xmlHttp = null;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest(); // firefox、chrome、safari等
	} else { // IE浏览器
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttp;
}

/**
 * 发送Ajax异步请求
 * @param method 请求方式（POST或GET）
 * @param url 请求的URL地址
 * @param str_params 参数字符串，比如"name=a&&id=1&&size=2"
 * @param callback 回调函数（可选参数）
 * @param error 服务器返回错误时，调用的函数（可选参数）
 */
function sendRequest(method, url, str_params, callback, error) {
	// 若XMLHttpRequest对象为空，则调用方法创建
	var xmlHttp = createXMLHttpRequest();

	// 若为GET方式
	if (method.toLowerCase() == "get" && str_params != null) {
		url += "?" + str_params; // 将参数追加到URL后面进行传递
	}
	// 创建一个新的HTTP请求，指定请求属性
	xmlHttp.open(method, url, true);
	// 设置回调函数
	xmlHttp.onreadystatechange = function() {
		// 若请求完成
		if (xmlHttp.readyState == 4){
			// 若状态正常
			if (xmlHttp.status == 200) {
				// 如果传入的callback参数是一个函数
				if (typeof(callback) == "function") {
					// 调用callback函数
					callback(xmlHttp);
				}
			} else { // 状态不正常，发生了错误
				// 如果传入的error参数是一个函数
				if (typeof(error) == "function") {
					// 调用error函数
					error(xmlHttp);
				}
			}
		}
	};
	// 若为POST请求方式
	if (method.toLowerCase() == "post") {
		// 设置Content-Type请求头
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		// 发送请求
		xmlHttp.send(str_params);
	} else {
		// 发送请求
		xmlHttp.send(null);
	}
}