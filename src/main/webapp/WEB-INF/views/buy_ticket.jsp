<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>buy_ticket.jsp</title>
</head>
<body>
	<h1>카드 결제 화면</h1>
	
	<form action="buy_ticket_card">
		고객아이디 : <input type="text" name="consumerId"><br>
		티켓구매수 : <input type="text" name="amount"><br>
		<input type="submit" value="구매">
	
	</form>
</body>
</html>