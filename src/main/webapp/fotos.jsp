<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="i" begin="1" end="5">
    item <c:out value="${i}"/>
    <%--<img src="uploads..."--%>
</c:forEach>
<c:forEach var="foto" items="${files}">
    ${foto.getname()}
</c:forEach>
<%
    File[] files = (File[]) request.getAttribute("files");

    for (File image : files) {
        String s = image.getName();
        String t = s.substring(0, s.lastIndexOf("."));

        out.println("<img src=\"uploads/" + image.getName() + "\">");
    }
%>
</body>
</html>
