<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>

<head>
    <meta charset="utf-8">
    <title>登录</title>
</head>

<body>
<form action="" name="userForm">
    账号：<input type="text" name="loginName"><br>
    密码：<input type="password" name="password"><br>
    <input type="button" value="登录" onclick="login()">
    <input type="button" onclick="window.location.href='register.jsp';" value="注册">
</form>
<script type="text/javascript">
    function login() {
        const form = document.forms[0];
        form.action = "<%=basePath %>user/login";
        form.method = "post";
        form.submit();
    }
</script>

</body>


</html>