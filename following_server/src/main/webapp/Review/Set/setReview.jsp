<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%

	String place = request.getParameter("place");
	String id=request.getParameter("id");
	String content=request.getParameter("content");
	String summery= request.getParameter("summery");
	if(place == null || place.isEmpty()||id == null || id.isEmpty()||content == null || content.isEmpty()||summery==null||summery.isEmpty()){
		response.sendError(400,"get파라미터 입력안됨");

	}
	else{
		boolean bool = Rest_Dao.getInstance().setReview(place,id,content,summery);
		if(bool==true) {
			response.setStatus(200);
		}else{
			response.sendError(400,"데이터베이스에 존재하지 않음");
		}
	}
	
%>