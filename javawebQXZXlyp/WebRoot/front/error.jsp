<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>访问的页面不存在!</title>
    <!-- <link rel="stylesheet" type="text/css" href="styles.css"> -->
    <style type="text/css">
    body{
    width:100%;
    }
    div{
    margin:120px auto;
    }
    input{
    font-size: 28px;
    background-color: rgb(159, 192, 236);
    }
    </style>
  </head>
  <body>
    ${pageContext.exception.message }sdsds
    <div><input type="button" value="你访问的页面不存,点我返回" onclick="history.go(-1)" /></div>
  </body>
</html>
