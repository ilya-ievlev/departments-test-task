<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of employees</title>
</head>
<body>
<h3>employees from ${department.departmentName}</h3>
<c:forEach var="employee" items="${employeeList}">
    <table>
        <fmt:formatDate value="${employee.birthday}" pattern="yyyy-MM-dd" var="formattedBirthday"/>
        <tr><c:out value="${employee.employeeName} || ${employee.email} || ${employee.position} || ${formattedBirthday}"/>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/gotoEditEmployee">
                    <input type="submit" value="edit employee">
                    <input hidden="true" value="${employee.employeeId}" name="employeeId">
                    <input hidden="true" value="${department.departmentId}" name="departmentId">
                </form>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/deleteEmployee">
                    <input type="submit" value="delete employee">
                    <input hidden="true" value="${employee.employeeId}" name="employeeId">
                    <input hidden="true" value="${department.departmentId}" name="departmentId">
                </form>
            </td>
        </tr>
    </table>
</c:forEach>
<form method="get" action ="${pageContext.request.contextPath}/gotoAddEmployee">
    <input type="submit" value="add employee">
    <input hidden="true" value="${department.departmentId}" name="departmentId">
</form>
<form method="get" action="${pageContext.request.contextPath}/getListOfDepartments">
    <input type="submit" value="get list of departments">
</form>
</body>
</html>
