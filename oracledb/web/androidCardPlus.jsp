<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="com.db.CardPlusDB"%>

<%
CardPlusDB cardplusDB = CardPlusDB.getInstance();
	
   String id = request.getParameter("id");
   
   System.out.println(id);
	
   
	String result= cardplusDB.CardPlussDB(id);
 	System.out.println("---------------------");
 	System.out.println(id);
 	System.out.println(result);
 

// 	if(id.equals("id") && pwd.equals("pwd")) {
 	if(result.equals("0")) {
 		System.out.println("등록된 카드가 없습니다.");

 		out.println("0") ;
 		
 	}else {
 		System.out.println("등록된 카드가 있습니다!!");
 		
 		out.println("1");
 	}

   
%>