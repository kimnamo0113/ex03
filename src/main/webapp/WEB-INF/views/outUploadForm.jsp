<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="outUpload" method="post" enctype="multipart/form-data">
		<input type="text" name="test" placeholder="작성자이름">
		<input type="file" name="file">
		<input type="submit">
	</form>
</body>
</html>