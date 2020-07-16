<%@page import="following_server.Rest_Dao"%>
<%@ page language="java" contentType="application/json; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	boolean bool = Rest_Dao.getInstance().isLogin(request.getParameter("id"));
	if(bool) {
		out.print("{[True]}");
	} else {
		out.print("{[False]}");
	}
%>