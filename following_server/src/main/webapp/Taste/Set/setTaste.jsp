<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%
boolean bool = Rest_Dao.getInstance().setTaste(request.getParameter("id"),request.getParameter("tagcontent"));
if(bool) {
		out.print("{[True]}");
	} else {
		out.print("{[False]}");
	}
	


%>