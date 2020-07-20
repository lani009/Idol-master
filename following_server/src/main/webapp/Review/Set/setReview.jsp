<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%
boolean bool= Rest_Dao.getInstance().setReview((request.getParameter("id"),request.getParameter("content"),request.getParameter("place")));
if(bool) {
		out.print("{[True]}");
	} else {
		out.print("{[False]}");
	


%>