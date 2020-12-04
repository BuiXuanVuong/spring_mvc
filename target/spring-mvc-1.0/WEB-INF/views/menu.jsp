<%--
  Created by IntelliJ IDEA.
  User: Media
  Date: 23/11/2020
  Time: 8:08 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
    <c:forEach var="item" items="${menu}">
        <li>${item}</li>
    </c:forEach>
</ul>