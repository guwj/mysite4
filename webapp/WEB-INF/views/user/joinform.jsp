<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(function() {
		$("#join-form").submit(function() {
			return true;
			// 1. 이름 체크
			if ($("#name").val() == "") {
				alert("이름은 필수 입력 항목입니다.");
				$("#name").focus();
				return false;
			}

			// 2-1. 이메일 비어있는 지 체크
			if ($("#email").val() == "") {
				alert("이메일은 필수 입력 항목입니다");
				$("#email").focus();
				return false;
			}

			// 2-2. 이메일 중복 체크 유무
			if ($("#img-chkemail").is(":visible") == false) {
				alert("이메일 중복 체크를 해야 합니다");
				return false;
			}

			// 3. 비밀번호 체크
			if ($("input[type='password']").val() == "") {
				alert("비밀번호는 필수 입력 항목입니다");
				$("input[type='password']").focus();
				return false
			}

			// 4. 약관동의
			if ($("#agree-prov").is(":checked") == false) {
				alert("약관 동의를 해야합니다");
				return false;
			}

			return true;
		});

		$("#btn-chkemail")
				.click(
						function() {
							var email = $("#email").val();
							if (email == "") {
								return;
							}

							$
									.ajax({
										url : "${pageContext.request.contextPath }/api/user?a=chkemail&email="
												+ email,
										type : "get",
										dataType : "json",
										data : "",
										//contentType: "application/json",
										success : function(response) {
											if (response.result == "fail") {
												console.log(response.message);
												return;
											}

											//success
											if (response.date == "exist") {
												alert("이미 존재하는 이메일입니다. 다른 이메일을 사용해 주세요");
												$("#email").val("").focus();
												return;
											}

											//존재하지 않는 이메일

										},
										error : function(jqXHR, status, e) {
											console.error(status + ":" + e);
										}
									});
						});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post"
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value=""> <label
						class="block-label" for="email">이메일</label> <input id="email"
						name="email" type="text" value=""> <input
						id="btn-chkemail" type="button" value="중복체크"> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>