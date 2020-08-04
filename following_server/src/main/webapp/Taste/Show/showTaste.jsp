<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%
String taste = Rest_Dao.getInstance().showTaste();
out.print(taste);
%>