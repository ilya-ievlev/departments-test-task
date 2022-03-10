<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>add employee</title>
</head>
<body>
<h3>add new employee</h3><br>
<form method="post" action="${pageContext.request.contextPath}/addEmployee">

    <input type="text" name="employeeName" value="${employee.employeeName}" placeholder="enter employee name" size="40">
<c:forEach var="constraintViolation" items="${constraintViolationList}">
    <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'employeeName'}"><font
            color="#dc143c">error</font></c:if>
</c:forEach><br><br>

    chose position: <select name="employeePosition">
        <c:forEach var="employeePosition" items="${employeePositions}">
            <option
                    <c:if test="${employee.position eq employeePosition}">selected</c:if>
                    value="${employeePosition}">${employeePosition}</option>
        </c:forEach>
    </select>

    <c:forEach var="constraintViolation" items="${constraintViolationList}">
        <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'position'}"><font
                color="#dc143c">error</font></c:if>
    </c:forEach>
    <br><br>

    <fmt:formatDate value="${employee.birthday}" pattern="yyyy-MM-dd" var="formattedBirthday"/>
    birthday: <input type="date" value="${formattedBirthday}" name="employeeBirthday">
    <c:forEach var="constraintViolation" items="${constraintViolationList}">
        <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'birthday'}"><font
                color="#dc143c">error</font></c:if>
    </c:forEach>
    <br><br>

    <input type="text" name="employeeEmail" value="${employee.email}" placeholder="enter employee email" size="40">
    <c:forEach var="constraintViolation" items="${constraintViolationList}">
        <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'email'}"><font
                color="#dc143c">error</font></c:if>
    </c:forEach>
    <br><br>
    <input type="submit" value="save">
    <input hidden="true" name="departmentId" value="${department.departmentId}">
</form>
<form method="get" action="${pageContext.request.contextPath}/getEmployeeList">
    <input type="submit" value="back to list of employees">
    <input hidden="true" name="departmentId" value="${department.departmentId}">
</form>
</body>
</html>
