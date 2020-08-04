<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%
String place = Rest_Dao.getInstance().recommend((request.getParameter("id")));
out.print(place);
%>