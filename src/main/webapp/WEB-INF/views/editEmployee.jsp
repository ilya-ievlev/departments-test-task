<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>edit employee</title>
</head>
<body>

<c:if test="${originalEmployee != null}">
    <h3>Edit ${originalEmployee.employeeName}, ${originalEmployee.email}</h3>
</c:if>
<c:if test="${originalEmployee == null}">
    <h3>Edit ${employee.employeeName}, ${employee.email}</h3><br>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/editEmployee">
    enter employee name:
    <input type="text" name="employeeNewName" value="${employee.employeeName}"
           placeholder="enter new employee name" size="40">

    <c:forEach var="constraintViolation" items="${constraintViolationList}">
        <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'employeeName'}"><font
                color="#dc143c">error</font></c:if>
    </c:forEach>
    <br><br>

    chose position (previous is ${employee.position}):
    <select name="employeePosition">

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

    email: <input type="text" name="employeeEmail" value="${employee.email}" placeholder="enter new employee email"
                  size="40">
    <c:forEach var="constraintViolation" items="${constraintViolationList}">
        <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'email'}"><font
                color="#dc143c">error</font></c:if>
    </c:forEach>
    <br><br>

<%--    enter new department (old department is ${department.departmentName})--%>
<%--    <select name="department">--%>

<%--        <c:forEach var="department" items="${departmentList}">--%>
<%--            <option--%>
<%--                    <c:if test="${employee.departmentId eq department.departmentId}">selected</c:if>--%>
<%--                    value="${department.departmentName}">${department.departmentName}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
<%--    <c:forEach var="constraintViolation" items="${constraintViolationList}">--%>
<%--        <c:if test="${constraintViolation.checkDeclaringContext.field.name eq 'departmentId'}"><font--%>
<%--                color="#dc143c">error</font></c:if>--%>
<%--    </c:forEach>--%>
<%--    <br><br>--%>

    <input type="submit" value="save">
    <input hidden="true" value="${employee.employeeId}" name="employeeId">
    <input hidden="true" value="${employee.departmentId}" name="departmentId">
</form>

<form method="get" action="${pageContext.request.contextPath}/getEmployeeList">
    <input type="submit" value="back to list of employees">
    <input hidden="true" value="${employee.departmentId}" name="departmentId">
</form>
</body>
</html>
