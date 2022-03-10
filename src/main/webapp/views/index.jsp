<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WELCOME</title>
</head>
<body>
<h3>
    welcome to my test project, please go to the first page:
</h3>
<form method="get" action="${pageContext.request.contextPath}/getListOfDepartments">
    <input type="submit" value="go to main page">
</form>
</body>
</html>
