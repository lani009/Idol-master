<%@ page language="java" contentType="application/json; charset=utf-8"%>
<%@page import="following_server.Rest_Dao"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.List"%>
<%
List<JSONObject> tag = Rest_Dao.getInstance().getPlaceTag((request.getParameter("id")));
for(JSONObject obj : tag) {
    out.print(obj.get("placeName"));
}
%>