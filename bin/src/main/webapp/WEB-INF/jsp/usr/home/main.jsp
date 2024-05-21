<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer="defer"></script>
</head>
<body>
	<h1>도시 검색</h1>
	<form action="/getWeather" method="post">
		<label for="city">지역이름:</label> <input type="text" name="city" required>
		<button type="submit">검색</button>
	</form>

	<c:if test="${weather != null}">
		<h2>Weather Details</h2>
		<p>Temperature: ${weather.temperature} °C</p>
		<p>Humidity: ${weather.humidity} %</p>
		<p>Wind Speed: ${weather.windSpeed} m/s</p>
	</c:if>


</body>
</html>

