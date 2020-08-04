<%@page import="following_server.Rest_Dao"%>
<%@ page language="java" contentType="application/json; charset=utf-8"
    pageEncoding="utf-8"%>

  
<%
	String send=request.getParameter("id");

	if(send == null || send.isEmpty()){
		response.sendError(400,"get파라미터 입력안됨");
	}
	else{
		boolean bool = Rest_Dao.getInstance().isLogin(send);
		if(bool==true) {
			response.setStatus(200);
		}else{
			response.sendError(400,"데이터베이스에 존재하지 않음");
		}
	}
	
	
%>