<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%
boolean bool= Rest_Dao.getInstance().setReview(request.getParameter("place"),request.getParameter("id"),request.getParameter("content"));
if(bool) {
		out.print("{[True]}");
	} else {
		out.print("{[False]}");
	}
%>