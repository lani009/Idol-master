<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%
String review = Rest_Dao.getInstance().getReview((request.getParameter("place")));
out.print(review);
%>