<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit department</title>
</head>
<body>

<c:if test="${originalDepartment != null}">
    <h3>Edit ${originalDepartment.departmentName}</h3>
</c:if>
<c:if test="${originalDepartment == null}">
    <h3>Edit ${department.departmentName}</h3>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/editDepartment">
    enter new department name: <input type="text" name="departmentNewName" value="${department.departmentName}"
                                      placeholder="enter new department name here" size="40">
    <c:forEach var="constraintViolation" items="${constraintViolationList}">
        <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'departmentName'}"><font
                color="#dc143c">error</font></c:if>
    </c:forEach>
    <input type="submit" value="save">
    <input hidden="true" value="${department.departmentId}" name="departmentId">
</form>
<form method="get" action="${pageContext.request.contextPath}/getListOfDepartments">
    <input type="submit" value="back to list of departments">
</form>
</body>
</html>