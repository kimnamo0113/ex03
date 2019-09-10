<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
	#dropBox{
		min-width:400px;
		min-height: 300px;
		height:auto;
		width:400px;
		border: 1px dotted gray;
	}
	
	#dropBox img{
		width:100px;
		height: 100px;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>
<body>
	<form id="f1" action="dragUpload" method="post" enctype="multipart/form-data">
		<input type="text" name="test">
		<input type="submit">
	</form>
	<div id="dropBox"></div>
	
	<script type="text/javascript">
		var formData = new FormData();//서버로 보낼 데이터를 담을 공간
		
		$("#f1").submit(function(e){
			e.preventDefault(); //ajax로 처리하므로, submit 안되게 막음
			formData.append("test",$("input[name='test']").val());
			$.ajax({
				url:"dragUpload",
				type:"post",
				data:formData,
				processData:false, //FormData 를 보낼 경우 processData:false, contentType:false처리 필요
				contentType:false,
				dataType:"text",
				success:function(res){
					console.log(res);
					
				}
			})
		})
		
		$("#dropBox").on("dragenter dragover",function(event){
			event.preventDefault();
		})
	
		$("#dropBox").on("drop",function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			var file=files[0];
			console.log(file);
			
			var reader = new FileReader();
			reader.addEventListener("load", function(e) {
				var $img = $("<img>").attr("src",reader.result);
				$("#dropBox").append($img);
			}, false)
			
			if(file){
				reader.readAsDataURL(file);
			}
			
			formData.append("files",file);//files=[file,file,file]
			console.log(formData.getAll)
		})
		
		
		
	</script>
	
</body>
</html>