<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%

	String id=request.getParameter("id");
	String content=request.getParameter("tagcontent");
	if(id == null || id.isEmpty()||content == null || content.isEmpty()){
		response.sendError(400,"get파라미터 입력안됨");
	}
	else{
		boolean bool = Rest_Dao.getInstance().setTaste(id,content);
		if(bool==true) {
			response.setStatus(200);
		}else{
			response.sendError(400,"데이터베이스에 존재하지 않음");
		}
	}


%>