<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%
String taste = Rest_Dao.getInstance().getTaste((request.getParameter("id")));
out.print(taste);
%>