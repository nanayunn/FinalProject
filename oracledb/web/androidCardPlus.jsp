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
 		System.out.println("��ϵ� ī�尡 �����ϴ�.");

 		out.println("0") ;
 		
 	}else {
 		System.out.println("��ϵ� ī�尡 �ֽ��ϴ�!!");
 		
 		out.println("1");
 	}

   
%>