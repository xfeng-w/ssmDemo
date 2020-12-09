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
    <title>注册</title>
</head>

<body>
<form action="" name="userForm">
    账号：<input type="text" name="account"><br>
    手机：<input type="text" name="phone"><br>
    密码：<input type="password" name="password"><br>
    <input type="button" value="注册" onclick="register()">
</form>
<script type="text/javascript">
    function register() {
        const form = document.forms[0];
        form.action = "<%=basePath %>user/register";
        form.method = "post";
        form.submit();
    }
</script>

</body>


</html>