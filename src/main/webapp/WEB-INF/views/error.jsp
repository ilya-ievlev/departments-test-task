<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<h3>
    some error was generated, please, go to the main page
</h3>
<form method="get" action="${pageContext.request.contextPath}/getListOfDepartments">
    <input type="submit" value="go to main page">
</form>
</body>
</html>
