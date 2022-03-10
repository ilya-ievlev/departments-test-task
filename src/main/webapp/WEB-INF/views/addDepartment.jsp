<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add department</title>
</head>
<body>
<h3>Add new department</h3>
<form method="post" action="${pageContext.request.contextPath}/addDepartment">
    department name must be not null, name should be 3-120 characters long and unique<br><br>
    department name: <input type="text" name="newDepartmentName" placeholder="enter department name here"
                            value="${department.departmentName}" size="40">

    <c:forEach var="constraintViolation" items="${constraintViolationList}">
        <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'departmentName'}"><font
                color="#dc143c">error</font></c:if>
    </c:forEach>
    <input type="submit" value="save">
</form>
<form method="get" action="${pageContext.request.contextPath}/getListOfDepartments">
    <input type="submit" value="back to list of departments">
</form>
</body>
</html>
