<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>List of departments</title>
</head>
<body>
<c:forEach var="department" items="${departmentList}">
    <table>
        <tr><c:out value="${department.departmentName}"/>

            <td>
                <form method="get" action="${pageContext.request.contextPath}/getEmployeeList">
                    <input type="submit" value="view employees">
                    <input hidden="true" value="${department.departmentId}" name="departmentId">
                </form>
            </td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}/gotoEditDepartment">
                    <input type="submit" value="edit department">
                    <input hidden="true" value="${department.departmentId}" name="departmentId">
                </form>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/deleteDepartment">
                    <input type="submit" value="delete department">
                    <input hidden="true" value="${department.departmentId}" name="departmentId">
                </form>
            </td>
        </tr>
    </table>
</c:forEach>

<form method="get" action="${pageContext.request.contextPath}/gotoAddDepartment">
    <button type="submit">add department</button>
</form>
</body>
</html>
